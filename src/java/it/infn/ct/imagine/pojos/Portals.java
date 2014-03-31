/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.infn.ct.imagine.pojos;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mario
 */
@XmlRootElement(name = "portals")
public class Portals {

    private List<Portal> list;

    //@XmlElement(name = "portal")
    public List<Portal> getList() {
        return list;
    }

    public void setList(List<Portal> list) {
        this.list = list;
    }
}
