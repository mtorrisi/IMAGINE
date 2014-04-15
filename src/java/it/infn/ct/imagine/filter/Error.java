/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.infn.ct.imagine.filter;

import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mario
 */
@XmlRootElement(name = "error")
public class Error {

    private int status;
    @XmlAttribute(name = "error-code")
    private int errorCode;
    private String message;

    public Error(Response.Status status, ErrorType type, ErrorMessage errorMessage) {
    
        this.status = status.getStatusCode();
        this.errorCode = type.getValue();
        this.message = errorMessage.getMessage();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public enum ErrorMessage {

        TIMESTAMP_MESSAGE   ("Timestamp provided is out of permitted bound."), 
        INVALID_API_KEY     ("Provided API KEY was not found."), 
        WRONG_SIGNATURE     ("Provided a wrong message digest."), 
        DISABLED_API_KEY    ("Your API KEY was temporarily disabled.");
        private final String message;

        ErrorMessage(final String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
