/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.infn.ct.imagine.pojos;

import it.infn.ct.GridEngine.UsersTracking.Applications;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mario
 */
@XmlRootElement(name = "applicationList")
public class ApplicationList {

    private List<Applications> list;

    public List<Applications> getList() {
        return list;
    }

    public void setList(List<Applications> list) {
        this.list = list;
    }
}
