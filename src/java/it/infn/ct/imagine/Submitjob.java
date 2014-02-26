/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.infn.ct.imagine;

import it.infn.ct.imagine.pojos.SubmissionObject;
import it.infn.ct.GridEngine.Job.InfrastructureInfo;
import it.infn.ct.GridEngine.Job.MultiInfrastructureJobSubmission;
import it.infn.ct.GridEngine.JobResubmission.GEJobDescription;
import it.infn.ct.imagine.pojos.Credential;
import it.infn.ct.imagine.pojos.Infrastructure;
import it.infn.ct.imagine.pojos.JobDescription;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author mario
 */
@Path("job/submit")
public class Submitjob {

    @Context
    private UriInfo context;

    /**api
     * Creates a new instance of Submitjob
     */
    public Submitjob() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response submitJob(SubmissionObject p) {
        JobDescription jobDescription = p.getJobDescription();
        Credential credential = p.getCredential();
        Infrastructure infrastructure = p.getInfrastructure();
        GEJobDescription description = new GEJobDescription();
        description.setExecutable(jobDescription.getExecutable());
        description.setOutputPath("/tmp");
        description.setOutput(jobDescription.getOutput());
        description.setError(jobDescription.getError());

        InfrastructureInfo infrastructures[] = new InfrastructureInfo[1];

        String wmsList[] = infrastructure.getResourceManagers().split(",");
        String middleware = wmsList[0].toString().substring(0, wmsList[0].toString().indexOf(":"));
        switch (credential.getType()) {
            case proxy:
                infrastructures[0] = new InfrastructureInfo("gridit", infrastructure.getInformationSystem(), wmsList, credential.getProxyPath());
                break;
            case eTokenServer:
                infrastructures[0] = new InfrastructureInfo("gridit", infrastructure.getInformationSystem(), wmsList, credential.getServerName(), credential.getServerPort(), credential.getProxyId(), credential.getVo(), credential.getFqan());
                break;
            case keystore:
                infrastructures[0] = new InfrastructureInfo("unicore", middleware, credential.getKeystorePath(), credential.getKeystorePassword(), wmsList);
                break;
            case UserPass:
                infrastructures[0] = new InfrastructureInfo("SSH infrastructure", middleware, credential.getUsername(), credential.getPassword(), wmsList);
                break;
        }

        String portalIPAddress = "";
        try {
            portalIPAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            System.err.println("Unable to get the portal IP address");
        }

        MultiInfrastructureJobSubmission multiInfrastructureJobSubmission = new MultiInfrastructureJobSubmission("jdbc:mysql://localhost/userstracking", "tracking_user",
                "usertracking", description);
        if(p.getUserEmail() != null){
            multiInfrastructureJobSubmission.setUserEmail(p.getUserEmail());
        }
        multiInfrastructureJobSubmission.submitJobAsync(infrastructures[0], p.getCommonName(), portalIPAddress, p.getApplication(), p.getIdentifier());

        return Response.status(Response.Status.ACCEPTED).entity(p).build();
    }
}
