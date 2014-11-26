/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.infn.ct.imagine.pojos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author mario
 */
public class SubmissionObject {
    
    String commonName;
    int application;
    String identifier;
    @JsonProperty("jobDescription")
    JobDescription jobDescription;
    @JsonProperty("credential")
    Credential credential;
    @JsonProperty("infrastructure")
    Infrastructure infrastructure;
    String userEmail;

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public int getApplication() {
        return application;
    }

    public void setApplication(int application) {
        this.application = application;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential c) {
        this.credential = c;
    }

    public JobDescription getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(JobDescription jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Infrastructure getInfrastructure() {
        return infrastructure;
    }

    public void setInfrastructure(Infrastructure infrastructure) {
        this.infrastructure = infrastructure;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
   
    @Override
    public String toString() {
        return "SubmissionObject{" + "commonName=" + commonName + ", application=" + application + ", identifier=" + identifier + ", userEmail=" + userEmail + '}';
    }
}
