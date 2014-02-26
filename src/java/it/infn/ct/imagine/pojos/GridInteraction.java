/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.infn.ct.imagine.pojos;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mario
 */
@XmlRootElement
public class GridInteraction {

    private int id;
    private String portal;
    private String application;
    private String userDescription;
    private String submissionTimestamp;
    private String status;

    @XmlElementWrapper
    @XmlElement(name = "gridInteraction")
    private List<GridInteraction> subjobsInfos;

    public GridInteraction() {
    }

    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPortal() {
        return portal;
    }

    public void setPortal(String portal) {
        this.portal = portal;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public String getSubmissionTimestamp() {
        return submissionTimestamp;
    }

    public void setSubmissionTimestamp(String submissionTimestamp) {
        this.submissionTimestamp = submissionTimestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GridInteraction> getSubjobsInfos() {
        return subjobsInfos;
    }

    public void setSubjobsInfos(List<String[]> subjobsInfos) {
        List<GridInteraction> values = new ArrayList<GridInteraction>();
        if (subjobsInfos != null) {
            for (String[] strings : subjobsInfos) {
                GridInteraction a = new GridInteraction();
                a.setId(Integer.parseInt(strings[0]));
                a.setPortal(strings[1]);
                a.setApplication(strings[2]);
                a.setUserDescription(strings[3]);
                a.setSubmissionTimestamp(strings[4]);
                a.setStatus(strings[5]);
                a.setSubjobsInfos(null);
                values.add(a);
            }
        } else {
            values = null;
        }
        this.subjobsInfos = values;
    }

}
