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
import it.infn.ct.imagine.pojos.JobCollection;
import it.infn.ct.imagine.pojos.JobDescription;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;

/**
 * REST Web Service
 *
 * @author mario
 */
@Path("collection/submit")
public class SubmitCollection {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Submitjob
     */
    public SubmitCollection() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response submitCollection(JobCollection p) throws IOException {
        
        System.out.println("CIAO");
        for (JobDescription desc: p.getSubJobs()) {
            System.out.println(desc);
        }
        ObjectMapper obj = new ObjectMapper();
        System.err.println(obj.writerWithDefaultPrettyPrinter().writeValueAsString(p));
        return Response.status(Response.Status.ACCEPTED).entity(p).build();
    }
}
