/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.infn.ct.imagine;

import it.infn.ct.GridEngine.UsersTracking.UsersTrackingDBInterface;
import it.infn.ct.imagine.pojos.Portal;
import it.infn.ct.imagine.pojos.Portals;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;


/**
 * REST Web Service
 *
 * @author mario
 */
@Path("/portals")
public class PortalResource {

    UsersTrackingDBInterface dbInterface = new UsersTrackingDBInterface();
//    UsersTrackingDBInterface dbInterface = new UsersTrackingDBInterface("jdbc:mysql://localhost:3306/userstracking", "tracking_user", "usertracking");
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PoratalResource
     */
    public PortalResource() {
    }

    /**
     * Retrieves representation of an instance of
     * it.infn.ct.imagine.PoratalResource
     *
     * @param request
     * @return an instance of java.lang.String
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ArrayList<Portal> getPortals(@Context HttpServletRequest request) {

        ArrayList<Portal> portals = new ArrayList<Portal>();
        String[] portalsNames = dbInterface.getPortals();
        if (portalsNames != null) {
            for (int i = 0; i < portalsNames.length; i++) {
                Portal p = new Portal();
                p.setId(i);
                p.setName(portalsNames[i]);
                portals.add(p);
            }
        }
        Portals p = new Portals();
        p.setList(portals);
//        return Response.status(Response.Status.OK).entity(p).build();
        return portals;
    }

}
