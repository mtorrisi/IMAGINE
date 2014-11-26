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
    private String statusMessage;
    @XmlAttribute(name = "error-code")
    private int errorCode;
    private String message;

    public Error(Response.Status status, ErrorType type, ErrorMessage errorMessage) {
    
        this.status = status.getStatusCode();
        this.statusMessage = status.getReasonPhrase();
        this.errorCode = type.getValue();
        this.message = errorMessage.getMessage();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
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
        DISABLED_API_KEY    ("Your API KEY was temporarily disabled."),
        PROVIDE_AUTHZ       ("Specify the authorization header.");
        private final String message;

        ErrorMessage(final String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
