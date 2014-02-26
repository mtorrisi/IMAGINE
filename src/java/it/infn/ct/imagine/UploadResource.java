/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.infn.ct.imagine;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author mario
 */
@Path("files")
public class UploadResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UploadResource
     */
    public UploadResource() {
    }

    /**
     * Retrieves representation of an instance of
     * it.infn.ct.imagine.UploadResource
     *
     * @param uploadedInputStream
     * @param fileDetail
     * @return an instance of java.lang.String
     */
    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) {

        String uploadedFileLocation = "/tmp/uploaded/" + fileDetail.getFileName();
        writeToFile(uploadedInputStream, uploadedFileLocation);

        String output = "File uploaded to: " + uploadedFileLocation;

        return Response.status(Response.Status.OK).entity(output).build();

    }

    private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {
        OutputStream os;
        try {

            int read = 0;
            byte[] bytes = new byte[1024];

            os = new FileOutputStream(new File(uploadedFileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                os.write(bytes, 0, read);
            }
            os.flush();
            os.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UploadResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UploadResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
