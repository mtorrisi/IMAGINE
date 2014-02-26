/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.infn.ct.imagine;

import it.infn.ct.GridEngine.UsersTracking.ActiveInteractions;
import it.infn.ct.GridEngine.UsersTracking.UsersTrackingDBInterface;
import it.infn.ct.imagine.pojos.GridInteraction;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author mario
 */
@Path("jobs/status")
public class JobsStatus {

    UsersTrackingDBInterface dbInterface = new UsersTrackingDBInterface();
//    UsersTrackingDBInterface dbInterface = new UsersTrackingDBInterface("jdbc:mysql://localhost:3306/userstracking", "tracking_user", "usertracking");
    @Context
    private UriInfo context;

    /**
     * Ritorna le informazioni dei jobs per l'utente specificato.
     *
     * @param commonName
     * @return
     */
    @GET
    @Path("/{commonName}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<GridInteraction> getJobStatus(@PathParam("commonName") String commonName) {
            return getInteractions(commonName, false);         
    }

    /**
     * Ritorna le informazioni dei jobs per l'utente specificato relative al
     * portale specificato.
     *
     * @param commonName
     * @param portal
     * @return
     */
    @GET
    @Path("/{commonName}/{portal}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<GridInteraction> getJobStatus(@PathParam("commonName") String commonName,
            @PathParam("portal") String portal) {

        List<GridInteraction> result = new ArrayList<GridInteraction>();
        List<GridInteraction> tmp = getInteractions(commonName, false);

        try {
            int dbId = Integer.parseInt(portal);
            for (GridInteraction a : tmp) {
                if (dbId == a.getId()) {
                    result.add(a);
                }
            }
        } catch (NumberFormatException ex) {
            for (GridInteraction a : tmp) {
                if (a.getPortal().equals(portal)) {
                    result.add(a);
                }
            }
        }

//        if (result.size() > 0) {
//            return Response.status(Response.Status.OK).entity(result).build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
        return result;
    }

    /**
     * Ritorna le informazioni dei jobs per l'utente specificato relative al
     * portale e all'applicazione specificati.
     *
     * @param commonName
     * @param portal
     * @param application
     * @return
     */
    @GET
    @Path("/{commonName}/{portal}/{application}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<GridInteraction> getJobStatus(@PathParam("commonName") String commonName,
            @PathParam("portal") String portal, @PathParam("application") String application) {
        List<GridInteraction> result = new ArrayList<GridInteraction>();
        List<GridInteraction> tmp = getInteractions(commonName, false);

        for (GridInteraction a : tmp) {
            if (a.getPortal().equals(portal) && a.getApplication().equals(application)) {
                result.add(a);
            }
        }

        return result;
    }

    /**
     * Ritorna le informazioni delle interazioni completate per l'utente
     * specificato.
     *
     * @param commonName
     * @return
     */
    @GET
    @Path("/archive/{commonName}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<GridInteraction> getJobArchive(@PathParam("commonName") String commonName) {
//        try {
//            int dbId = Integer.parseInt(commonName);
//            GridInteraction archivedInteraction = getInteractionBDbId(dbId, true);
//            return Response.status(Response.Status.OK).entity(archivedInteraction).build();
//        } catch (NumberFormatException ex) {
       return getInteractions(commonName, true);
//        return Response.status(Response.Status.OK).entity(jobs).build();
//        }

    }

    /**
     * Ritorna le informazioni delle interazioni completate per l'utente
     * specificato relative al portale specificato.
     *
     * @param commonName
     * @param portal
     * @return
     */
    @GET
    @Path("/archive/{commonName}/{portal}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<GridInteraction> getJobArchive(@PathParam("commonName") String commonName,
            @PathParam("portal") String portal) {
        List<GridInteraction> result = new ArrayList<GridInteraction>();
        List<GridInteraction> tmp = getInteractions(commonName, true);

        try {
            int dbId = Integer.parseInt(portal);
            for (GridInteraction a : tmp) {
                if (dbId == a.getId()) {
                    result.add(a);
                }
            }
        } catch (NumberFormatException ex) {
            for (GridInteraction a : tmp) {
                if (a.getPortal().equals(portal)) {
                    result.add(a);
                }
            }
        }

        return result;
//        if (result.size() > 0) {
//            return Response.status(Response.Status.OK).entity(result).build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
    }

    /**
     * Ritorna le informazioni delle interazioni archiviate per l'utente
     * specificato relative al portale e all'applicazione specificati.
     *
     * @param commonName
     * @param portal
     * @param application
     * @return
     */
    @GET
    @Path("/archive/{commonName}/{portal}/{application}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<GridInteraction> getJobArchive(@PathParam("commonName") String commonName,
            @PathParam("portal") String portal, @PathParam("application") String application) {
        List<GridInteraction> result = new ArrayList<GridInteraction>();
        List<GridInteraction> tmp = getInteractions(commonName, true);

        for (GridInteraction a : tmp) {
            if (a.getPortal().equals(portal) && a.getApplication().equals(application)) {
                result.add(a);
            }
        }

        return result;
    }

//    @GET
//    @Path("/download/{dbId}")
//    @Produces(MediaType.TEXT_HTML)
//    public String Download(@PathParam("dbId") int dbId, @DefaultValue("false") @QueryParam("isCollection") boolean isCollection) {
//
//        GridInteraction a = getInteractionBDbId(dbId, false);
//        if (a.getStatus().equals("DONE")) {
//            JSagaJobSubmission jSagaJobSubmission = new JSagaJobSubmission(dbInterface);
//
//            String ip = "file://";
//            try {
//                InetAddress thisIp = null;
//                thisIp = InetAddress.getLocalHost();
//                ip += thisIp.getHostAddress();
//                if (!isCollection) {
//                    ip += "/tmp" + jSagaJobSubmission.getJobOutput(dbId);
//                } else {
//                    ip += "/tmp" + jSagaJobSubmission.getCollectionOutput(dbId);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            return "<a href=" + ip + ">Link</a>";
//        } else {
//            return "KO";
//        }
//
//    }
    private List<GridInteraction> getInteractions(String commonName, boolean archived) {
        List<ActiveInteractions> interactionses;
        List<GridInteraction> result = new ArrayList<GridInteraction>();
        if (archived) {
            interactionses = dbInterface.getDoneInteractionsByName(commonName);
        } else {
            interactionses = dbInterface.getActiveInteractionsByName(commonName);
        }

        for (ActiveInteractions interactions : interactionses) {
            GridInteraction interaction = new GridInteraction();
            interaction.setId(Integer.parseInt(interactions.getInteractionInfos()[0]));
            interaction.setPortal(interactions.getInteractionInfos()[1]);
            interaction.setApplication(interactions.getInteractionInfos()[2]);
            interaction.setUserDescription(interactions.getInteractionInfos()[3]);
            interaction.setSubmissionTimestamp(interactions.getInteractionInfos()[4]);
            interaction.setStatus(interactions.getInteractionInfos()[5]);
            if (interactions.getSubJobs() != null) { //Collection
                interaction.setSubjobsInfos(interactions.getSubJobs());
            }

            result.add(interaction);
        }
        return result;
    }

}