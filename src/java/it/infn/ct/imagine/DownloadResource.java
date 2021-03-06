/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.infn.ct.imagine;

import it.infn.ct.GridEngine.Job.JSagaJobSubmission;
import it.infn.ct.GridEngine.UsersTracking.ActiveInteractions;
import it.infn.ct.GridEngine.UsersTracking.UsersTrackingDBInterface;
import it.infn.ct.imagine.filter.AuthenticationRequestWrapper;
import it.infn.ct.imagine.filter.ClientDao;
import it.infn.ct.imagine.filter.FilterRequest;
import it.infn.ct.imagine.pojos.GridInteraction;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.Vector;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author mario
 */
@Path("jobs/download")
public class DownloadResource {

    private static final Logger _log = Logger.getLogger(DownloadResource.class.getName());
//    UsersTrackingDBInterface dbInterface = new UsersTrackingDBInterface("jdbc:mysql://localhost:3306/userstracking", "tracking_user", "usertracking");
    UsersTrackingDBInterface dbInterface = new UsersTrackingDBInterface();

    private final String DN;

    @PersistenceContext(unitName = "IMAGINEPU")
    private final EntityManagerFactory factory = Persistence.createEntityManagerFactory("IMAGINEPU");

    private EntityManager em;

    @EJB
    ClientDao clientDao = new ClientDao();

    private final FilterRequest filter = new FilterRequest();

    public DownloadResource(@Context HttpServletRequest request) throws NoSuchAlgorithmException {
        AuthenticationRequestWrapper requestWrapper = new AuthenticationRequestWrapper(request);
        String tmpDN = filter.doFilter(requestWrapper, clientDao, factory.createEntityManager());
        _log.info(tmpDN);
        this.DN = Utility.getDNDigest(tmpDN);
    }

    /**
     * Retrieves the output archive for the specified completed job
     *
     * @param commonName
     * @param dbId
     * @param isCollection
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/{commonName}/{id}")
    @Produces({"applicaztion/zip"})
    public Response Download(@PathParam("commonName") String commonName, @PathParam("id") int dbId, @DefaultValue("false") @QueryParam("isCollection") boolean isCollection) {

        GridInteraction a = getInteraction(commonName + ":" + this.DN, dbId, false);
        String path = "/tmp";
        if (a != null) {
            if (a.getStatus().equals("DONE")) {
                JSagaJobSubmission jSagaJobSubmission = new JSagaJobSubmission(dbInterface);
                if (!isCollection) {
                    path += jSagaJobSubmission.getJobOutput(dbId);
                } else {
                    path += jSagaJobSubmission.getCollectionOutput(dbId);
                }
                File fileToSend = new File(path);
                return Response.ok(fileToSend).header("content-disposition", "attachment; filename = " + fileToSend.getName()).build();
            }
        }

        return Response.status(Response.Status.NOT_FOUND).build();

    }

    private GridInteraction getInteraction(String commonName, int dbId, boolean archived) {

        GridInteraction interaction = new GridInteraction();
        Vector<ActiveInteractions> activeInteractions = dbInterface.getActiveInteractionsByName(commonName);
//        ActiveInteractions interactionInfos = dbInterface.getActiveInteractionByDbId(dbId, archived);
        for (int i = 0; i < activeInteractions.size(); i++) {
            ActiveInteractions a = activeInteractions.elementAt(i);
            if (Integer.parseInt(a.getInteractionInfos()[0]) == dbId) {
                interaction.setId(Integer.parseInt(a.getInteractionInfos()[0]));
                interaction.setPortal(a.getInteractionInfos()[1]);
                interaction.setApplication(a.getInteractionInfos()[2]);
                interaction.setUserDescription(a.getInteractionInfos()[3]);
                interaction.setSubmissionTimestamp(a.getInteractionInfos()[4]);
                interaction.setStatus(a.getInteractionInfos()[5]);
                return interaction;
            }
        }
//        if (interactionInfos.getInteractionInfos() != null) {
//            interaction.setId(Integer.parseInt(interactionInfos.getInteractionInfos()[0]));
//            interaction.setPortal(interactionInfos.getInteractionInfos()[1]);
//            interaction.setApplication(interactionInfos.getInteractionInfos()[2]);
//            interaction.setUserDescription(interactionInfos.getInteractionInfos()[3]);
//            interaction.setSubmissionTimestamp(interactionInfos.getInteractionInfos()[4]);
//            interaction.setStatus(interactionInfos.getInteractionInfos()[5]);
//            return interaction;
//        } else {
//            return null;
//        }
        return null;

    }
}
