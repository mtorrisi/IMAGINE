/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.infn.ct.imagine.pojos;

import java.util.ArrayList;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author mario
 */
public class JobCollection {

    String commonName;
    int application;
    String identifier;
    int taskCounter;
    String userEmail;
    @JsonProperty("subJobs")
    ArrayList<JobDescription> subJobs;

    public JobCollection() {
    }

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

    public int getTaskCounter() {
        return taskCounter;
    }

    public void setTaskCounter(int taskCounter) {
        this.taskCounter = taskCounter;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public ArrayList<JobDescription> getSubJobs() {
        return subJobs;
    }

    public void setSubJobs(ArrayList<JobDescription> subJobs) {
        this.subJobs = subJobs;
    }

    @Override
    public String toString() {
        return "JobCollection{" + "commonName=" + commonName + ", application=" + application + ", identifier=" + identifier + ", taskCounter=" + taskCounter + ", userEmail=" + userEmail + '}';
    }

}
