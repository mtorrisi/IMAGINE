/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.infn.ct.imagine.filter;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author mario
 */
public class AuthenticationFilterException extends WebApplicationException{
    
    public AuthenticationFilterException(Error e){
        super(Response.status(e.getStatus()).entity(e).type(MediaType.APPLICATION_JSON).build());
    }
    
    
}
