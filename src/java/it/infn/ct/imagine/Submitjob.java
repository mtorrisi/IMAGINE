/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.infn.ct.imagine;

import it.infn.ct.imagine.pojos.SubmissionObject;
import it.infn.ct.GridEngine.Job.InfrastructureInfo;
import it.infn.ct.GridEngine.Job.MultiInfrastructureJobSubmission;
import it.infn.ct.GridEngine.JobResubmission.GEJobDescription;
import it.infn.ct.imagine.filter.AuthenticationRequestWrapper;
import it.infn.ct.imagine.filter.ClientDao;
import it.infn.ct.imagine.filter.FilterRequest;
import it.infn.ct.imagine.pojos.Credential;
import it.infn.ct.imagine.pojos.Infrastructure;
import it.infn.ct.imagine.pojos.JobDescription;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
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
    
    @PersistenceContext(unitName = "IMAGINEPU")
    private final EntityManagerFactory factory=Persistence.createEntityManagerFactory("IMAGINEPU");
    
    private EntityManager em;
    
    @EJB
    ClientDao clientDao = new ClientDao();
    
    private final FilterRequest filter = new FilterRequest();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response submitJob(@Context HttpServletRequest request, SubmissionObject p) {
        AuthenticationRequestWrapper requestWrapper = new AuthenticationRequestWrapper(request);
        String DN = filter.doFilter(requestWrapper, clientDao, factory.createEntityManager());
        JobDescription jobDescription = p.getJobDescription();

        Infrastructure infrastructure = p.getInfrastructure();
        GEJobDescription description = new GEJobDescription();
        description.setExecutable(jobDescription.getExecutable());
        description.setOutputPath("/tmp");
        description.setOutput(jobDescription.getOutput());
        description.setError(jobDescription.getError());
        description.setInputFiles(jobDescription.getInputFiles());
        description.setOutputFiles(jobDescription.getOutputFiles());
        description.setArguments(jobDescription.getArguments());
        InfrastructureInfo infrastructures[] = new InfrastructureInfo[1];

        if (infrastructure != null) {
            String wmsList[] = infrastructure.getResourceManagers().split(",");
            String infrastructureName= (infrastructure.getName() != null) ? infrastructure.getName() : "";
            String middleware = wmsList[0].toString().substring(0, wmsList[0].toString().indexOf(":"));
            Credential credential = p.getCredential();
            switch (credential.getType()) {
                case proxy:
                    infrastructures[0] = new InfrastructureInfo("gridit", infrastructure.getInformationSystem(), wmsList, credential.getProxyPath());
                    break;
                case eTokenServer:
                    infrastructures[0] = new InfrastructureInfo(infrastructureName, infrastructure.getInformationSystem(), wmsList, credential.getServerName(), credential.getServerPort(), credential.getProxyId(), credential.getVo(), credential.getFqan());
                    break;
                case keystore:
                    infrastructures[0] = new InfrastructureInfo("unicore", middleware, credential.getKeystorePath(), credential.getKeystorePassword(), wmsList);
                    break;
                case UserPass:
                    infrastructures[0] = new InfrastructureInfo("SSH infrastructure", middleware, credential.getUsername(), credential.getPassword(), wmsList);
                    break;
            }
        } else {
            //TODO get infrastructure from somewhere
            System.err.println("You have to specify infrastructure");
            return Response.status(Response.Status.BAD_REQUEST).entity("You have to specify infrastructure").build();
        }

        String portalIPAddress = "";
        try {
            portalIPAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            System.err.println("Unable to get the portal IP address");
        }

        MultiInfrastructureJobSubmission multiInfrastructureJobSubmission = new MultiInfrastructureJobSubmission(description);
//        MultiInfrastructureJobSubmission multiInfrastructureJobSubmission = new MultiInfrastructureJobSubmission("jdbc:mysql://localhost/userstracking", "tracking_user",
//                "usertracking", description);
        if (p.getUserEmail() != null) {
            multiInfrastructureJobSubmission.setUserEmail(p.getUserEmail());
        }
        multiInfrastructureJobSubmission.submitJobAsync(infrastructures[0], p.getCommonName() + ":" + DN, portalIPAddress, p.getApplication(), p.getIdentifier());
        
        return Response.status(Response.Status.ACCEPTED).entity(p).build();
    }
}
