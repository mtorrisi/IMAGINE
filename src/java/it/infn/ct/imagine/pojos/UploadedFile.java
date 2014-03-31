/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.infn.ct.imagine.pojos;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mario
 */
@XmlRootElement(name = "file")
public class UploadedFile {

    
    private int id;
    private String filePath;

    public UploadedFile() {
    }

    public UploadedFile(int i, String name) {
        this.id = i;
        this.filePath = name;
    }

    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement(name = "path")
    public String getFileName() {
        return filePath;
    }

    public void setFileName(String fileName) {
        this.filePath = fileName;
    }

}
