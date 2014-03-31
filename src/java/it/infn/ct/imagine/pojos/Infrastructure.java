/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.infn.ct.imagine.pojos;

/**
 *
 * @author mario
 */
public class Infrastructure {

    String name;
    String resourceManagers;
    String informationSystem;
    String jobQueue;
    String ceList;

    public Infrastructure() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceManagers() {
        return resourceManagers;
    }

    public void setResourceManagers(String resourceManagers) {
        this.resourceManagers = resourceManagers;
    }

    public String getInformationSystem() {
        return informationSystem;
    }

    public void setInformationSystem(String informationSystem) {
        this.informationSystem = informationSystem;
    }

    public String getJobQueue() {
        return jobQueue;
    }

    public void setJobQueue(String jobQueue) {
        this.jobQueue = jobQueue;
    }

    public String getCeList() {
        return ceList;
    }

    public void setCeList(String ceList) {
        this.ceList = ceList;
    }

    @Override
    public String toString() {
        return "Infrastructure{" + "resourceManagers=" + resourceManagers + ", informationSystem=" + informationSystem + ", jobQueue=" + jobQueue + ", ceList=" + ceList + '}';
    }
    
}
