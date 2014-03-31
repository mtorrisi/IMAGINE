/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.infn.ct.imagine;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import it.infn.ct.imagine.pojos.UploadedFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author mario
 */
@Path("upload")
public class UploadResource {

    @Context
    private UriInfo context;
    
    /**
     * Creates a new instance of UploadResource
     */
    public UploadResource() {
    }

    /**
     * Uploads files to the server
     * it.infn.ct.imagine.UploadResource
     *
     * @param formParams
     * @return an instance of java.lang.String
     */
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/files")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<UploadedFile> uploadFile(FormDataMultiPart formParams) {
        String uploadedFileLocation = "/tmp/uploaded/";
        Map<String, List<FormDataBodyPart>> m = formParams.getFields();

        List<UploadedFile> uploadedFiles = new ArrayList<UploadedFile>();
        for (Map.Entry<String, List<FormDataBodyPart>> entry : m.entrySet()) {
            String string = entry.getKey();
            System.out.println("key: " + string);
            List<FormDataBodyPart> list = entry.getValue();

            int i = 0;

            for (FormDataBodyPart formDataBodyPart : list) {
                System.out.println("formDataBodyPart[" + i + "]: " + formDataBodyPart.getName() + " " + formDataBodyPart.getMediaType());

                if (formDataBodyPart.getName().equals("file")) {
                    InputStream is = formDataBodyPart.getValueAs(InputStream.class);
                    FormDataContentDisposition fileDetails = formDataBodyPart.getFormDataContentDisposition();
                    String name = uploadedFileLocation.concat(writeToFile(is, uploadedFileLocation, fileDetails.getFileName()));
                    uploadedFiles.add(new UploadedFile(i, name));
                }

                i++;
            }
        }
        
        return uploadedFiles;
    }

    private String writeToFile(InputStream uploadedInputStream, String uploadedFileLocation, String fileName) {
        OutputStream os;
        try {

            int read = 0;
            byte[] bytes = new byte[1024];

            File dir = new File(uploadedFileLocation);
            if (!dir.exists()) {
                dir.mkdir();
            }

            String tmp[] = fileName.split("\\.(?=[^\\.]+$)");
            Date d = new Date();

            if (tmp.length == 2) {

                tmp[0] += "_" + d.getTime();
                fileName = tmp[0] + "." + tmp[1];
            } else {
                fileName += "_" + d.getTime();
            }

            os = new FileOutputStream(new File(uploadedFileLocation + fileName));
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
        return fileName;
    }
}
