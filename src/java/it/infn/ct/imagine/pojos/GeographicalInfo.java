/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.infn.ct.imagine.pojos;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mario
 */
@XmlRootElement(name = "geoinfo")
public class GeographicalInfo {

    String ce;
    int runningJobs;
    int doneJobs;
    String middleware;
    float lat;
    float lng;

    public GeographicalInfo() {
    }

    public GeographicalInfo(String ce, int runningJobs, int doneJobs, String m, float lat, float lng) {
        this.ce = ce;
        this.runningJobs = runningJobs;
        this.doneJobs = doneJobs;
        this.middleware = m;
        this.lat = lat;
        this.lng = lng;
    }

    public String getCe() {
        return ce;
    }

    public void setCe(String ce) {
        this.ce = ce;
    }

    public int getRunningJobs() {
        return runningJobs;
    }

    public void setRunningJobs(int runningJobs) {
        this.runningJobs = runningJobs;
    }

    public int getDoneJobs() {
        return doneJobs;
    }

    public void setDoneJobs(int doneJobs) {
        this.doneJobs = doneJobs;
    }

    public String getMiddleware() {
        return middleware;
    }

    public void setMiddlware(String m) {
        this.middleware = m;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }
}
