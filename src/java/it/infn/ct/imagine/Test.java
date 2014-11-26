/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.infn.ct.imagine;

import com.sun.jersey.multipart.FormDataParam;
import it.infn.ct.imagine.pojos.SubmissionObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author mario
 */
@Path("/test")
public class Test {

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void execute(@FormDataParam("pippo") String param, @FormDataParam("json") File inputfile) throws FileNotFoundException, IOException {
        System.out.println(param);

        ObjectMapper mapper = new ObjectMapper();
        SubmissionObject user = mapper.readValue(inputfile, SubmissionObject.class);
        System.out.println(user.toString());
//        BufferedReader br = new BufferedReader(new FileReader(inputfile));
//        String line = null;
//        String JsonString = "";
//        while ((line = br.readLine()) != null) {
//            JsonString += line;
//        }
//        SubmissionObject p = new SubmissionObject();
    }

}
