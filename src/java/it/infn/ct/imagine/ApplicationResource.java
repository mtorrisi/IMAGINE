/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.infn.ct.imagine;

import it.infn.ct.GridEngine.UsersTracking.Applications;
import it.infn.ct.GridEngine.UsersTracking.UsersTrackingDBInterface;
import it.infn.ct.imagine.pojos.ApplicationList;
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
@Path("applications")
public class ApplicationResource {

    UsersTrackingDBInterface dbInterface = new UsersTrackingDBInterface();
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ApplicationResource
     */
    public ApplicationResource() {
    }

    /**
     * Retrieves representation of an instance of
     * it.infn.ct.imagine.ApplicationResource
     *
     * @param portalId
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/{portalId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ApplicationList getApplications(@PathParam("portalId") int portalId) {
        String[] portalsNames = dbInterface.getPortals();
        ApplicationList applications = new ApplicationList();
        if (portalsNames != null && portalsNames.length > 0) {
            List<Applications> apps = dbInterface.getApplications(portalsNames[portalId]);
            applications.setList(apps);
        }
        return applications;
//        return Response.status(Response.Status.OK).entity(applications).build();
    }

//    @GET
//    @Path("/{portalId}")
//    @Produces("application/json")
//    public Response getApplications(@PathParam("portalId") int portalId) {
//        String[] portalsNames = dbInterface.getPortals();
//        List<Applications> applications = new ArrayList<Applications>();
//        if(portalsNames != null && portalsNames.length > 0){
//            applications = dbInterface.getApplications(portalsNames[portalId]);
//        }
//        
//        return Response.status(Response.Status.OK).entity(applications).build();
//    }
//    @GET
//    @Path("/{portal}")
//    @Produces("application/json")
//    public Response getApplications(@PathParam("portal") String portal) {
//        List<Applications> applications = dbInterface.getApplications(portal);
//        return Response.status(Response.Status.OK).entity(applications).build();
//    }
}
