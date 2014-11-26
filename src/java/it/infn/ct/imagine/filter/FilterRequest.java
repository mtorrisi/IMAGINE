/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.infn.ct.imagine.filter;

import com.sun.jersey.core.util.Base64;
import it.infn.ct.imagine.filter.Error.ErrorMessage;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;

/**
 *
 * @author mario
 */
enum AuthType {

    Basic, ApiKey
}

public class FilterRequest {
//    private FilterRequest(){
//        
//    }
//    
//    public static synchronized FilterRequest getInstance(){
//        if(_instance==null)
//            _instance = new FilterRequest();
//        return _instance;
//    }

    // Enable Multi-Read for PUT and POST requests
    private static final Set<String> METHOD_HAS_CONTENT = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER) {
        private static final long serialVersionUID = 1L;

        {
            add("PUT");
            add("POST");
        }
    };

    public String doFilter(AuthenticationRequestWrapper request, ClientDao clientDao, EntityManager em) {

        String authorizationHeader = request.getHeader("Authorization");
        String DN = "";
        if (authorizationHeader != null) {

            String authType = authorizationHeader.split(" ")[0];
            String authValue = authorizationHeader.split(" ")[1];

            AuthType type = AuthType.valueOf(authType);
            switch (type) {
                case Basic:
                    if (!request.getRequestURI().contains("secure")) {
                        String tmp = Base64.base64Decode(authValue);
                        String[] decriptedValues = tmp.split(":");

                        DN = decriptedValues[0];
                    }

                    break;
                case ApiKey:
                    String date = request.getHeader("Timestamp");
                    if (date != null && checkTimestamp(date)) {
                        //AuthenticationRequestWrapper authWrapper = new AuthenticationRequestWrapper(request);

                        Client client = clientDao.findByApiKey(em, authValue.split(":")[0]);
                        if (client != null) {
                            if (client.isEnabled()) {
                                String signature = computeSignature(request, client);
                                if (!signature.equals("") && signature.equals(authValue.split(":")[1])) {
                                    DN = client.getUuid();
                                } else {
                                    throw new AuthenticationFilterException(new Error(Response.Status.UNAUTHORIZED, ErrorType.WRONG_SIGNATURE, ErrorMessage.WRONG_SIGNATURE));
                                }
                            } else {
                                throw new AuthenticationFilterException(new Error(Response.Status.UNAUTHORIZED, ErrorType.DISABLED_API_KEY, ErrorMessage.DISABLED_API_KEY));
                            }
                        } else {
                            throw new AuthenticationFilterException(new Error(Response.Status.UNAUTHORIZED, ErrorType.INVALID_API_KEY, ErrorMessage.INVALID_API_KEY));
                        }
                    } else {
                        throw new AuthenticationFilterException(new Error(Response.Status.FORBIDDEN, ErrorType.OUT_OF_BOUND_TIMESTAMP, ErrorMessage.TIMESTAMP_MESSAGE));
                    }

                    break;
            }

        } else {
            throw new AuthenticationFilterException(new Error(Response.Status.UNAUTHORIZED, ErrorType.PROVIDE_AUTHZ, ErrorMessage.PROVIDE_AUTHZ));
        }
        return DN;

    }

    private String computeSignature(AuthenticationRequestWrapper request, Client client) {

        boolean hasContent = METHOD_HAS_CONTENT.contains(request.getMethod());
        String contentMd5 = hasContent ? computeMD5Sum(request.getPayload()) : "";
        String contentType = hasContent ? computeMD5Sum(request.getContentType()) : "";

        StringBuilder toSign = new StringBuilder();
        toSign.append(request.getMethod()).append("\n")
                .append(new String(Base64.encode(contentMd5))).append("\n")
                .append(new String(Base64.encode(contentType))).append("\n")
                .append(request.getHeader("Timestamp")).append("\n")
                .append(request.getRequestURI());

        try {
            String tmp = "";

            SecretKeySpec signingKey = new SecretKeySpec(client.getSecret().getBytes(), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(toSign.toString().getBytes());

            tmp = "";
            for (byte b : rawHmac) {
                tmp += String.format("%02x", b);

            }
            String result = new String(Base64.encode(tmp));
            return result;
        } catch (GeneralSecurityException e) {
            throw new IllegalArgumentException();
        }

    }

    private boolean checkTimestamp(String dateHeader) {
        long t = System.currentTimeMillis();
        long time = Long.parseLong(dateHeader) * 1000L;
        return (t - time) < 300000L;
    }

    private String computeMD5Sum(String payload) {
        String result = "";
        try {
            byte[] bytesOfMessage = payload.getBytes();

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(bytesOfMessage);
            result = new String(thedigest);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FilterRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
