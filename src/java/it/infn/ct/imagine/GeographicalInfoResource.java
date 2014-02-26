/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.infn.ct.imagine;

import it.infn.ct.GridEngine.UsersTracking.UsersTrackingDBInterface;
import it.infn.ct.imagine.pojos.GeographicalInfo;
import java.util.ArrayList;
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
@Path("geoinfos")
public class GeographicalInfoResource {

//    UsersTrackingDBInterface dbInterface = new UsersTrackingDBInterface("jdbc:mysql://localhost:3306/userstracking", "tracking_user", "usertracking");
    private final UsersTrackingDBInterface dbInterface;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GeographicalInfoResource
     */
    public GeographicalInfoResource() {
        dbInterface = new UsersTrackingDBInterface();
    }

    /**
     * Retrieves representation of an instance of
     * it.infn.ct.imagine.GeographicalInfoResource
     *
     * @param portalId
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/{portalId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ArrayList<GeographicalInfo> getCEsGeographicDistributionForAll(@PathParam("portalId") int portalId) {

        ArrayList<GeographicalInfo> result = new ArrayList<GeographicalInfo>();

        String[] portalsNames = getPortalsNames();
        if (portalsNames != null && portalsNames.length != 0) {
            result = retrieveGeoInfos(portalsNames[portalId], "");
        }

        return result;
    }
//    @GET
//    @Path("/{portal}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public ArrayList<GeographicalInfos> getCEsGeographicDistributionForAll(@PathParam("portal") String portal) {
//        ArrayList<GeographicalInfos> result=new ArrayList<GeographicalInfos>();
//        try {
//            result = retrieveGeoInfos(URLDecoder.decode(portal, "UTF-8"),"");
//            
//        } catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(GeographicalInfoResource.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return result;
//    }

    /**
     * Retrieves representation of an instance of
     * it.infn.ct.imagine.GeographicalInfoResource
     *
     * @param commonName
     * @param portalId
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/{commonName}/{portalId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ArrayList<GeographicalInfo> getCEsGeographicDistribution(@PathParam("commonName") String commonName,
            @PathParam("portalId") int portalId) {

        ArrayList<GeographicalInfo> result = new ArrayList<GeographicalInfo>();

        String[] portalNames = getPortalsNames();
        if (portalNames != null && portalNames.length != 0) {
            result = retrieveGeoInfos(portalNames[portalId], commonName);
        }
        return result;
    }
//    @GET
//    @Path("/{portal}/{commonName}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public ArrayList<GeographicalInfos> getCEsGeographicDistribution(@PathParam("portal") String portal,
//            @PathParam("commonName") String commonName) {
//        ArrayList<GeographicalInfos> result = retrieveGeoInfos(portal, commonName);
//        return result;
//    }

    private ArrayList<GeographicalInfo> retrieveGeoInfos(String portal, String commonName) {
        ArrayList<GeographicalInfo> result = new ArrayList<GeographicalInfo>();
        ArrayList<String[]> tmp;

        if (commonName.equals("")) {
            tmp = new ArrayList<String[]>(dbInterface.getCEsGeographicDistributionForAll(portal));
        } else {
            tmp = new ArrayList<String[]>(dbInterface.getCEsGeographicDistribution(portal, commonName));
        }

        for (String[] strings : tmp) {
            GeographicalInfo infos = new GeographicalInfo(strings[0],
                    Integer.parseInt(strings[1]),
                    Integer.parseInt(strings[2]),
                    strings[3],
                    Float.parseFloat(strings[4]),
                    Float.parseFloat(strings[5]));
            result.add(infos);
        }
        return result;
    }

    private String[] getPortalsNames() {
        return dbInterface.getPortals();
    }
}
