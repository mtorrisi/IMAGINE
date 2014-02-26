/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.infn.ct.imagine.pojos;

/**
 *
 * @author mario
 */
public class ETokenServerCredential extends Credential {

    String serverName;
    String serverPort;
    String proxyId;
    String vo;
    String fqan;
    boolean proxyRenewal;
    boolean rfc;
    
    public ETokenServerCredential() {
        super(Type.eTokenServer);
    }

    @Override
    public String getServerName() {
        return serverName;
    }

    public void setName(String name) {
        this.serverName = name;
    }

    @Override
    public String getServerPort() {
        return serverPort;
    }

    public void setPort(String port) {
        this.serverPort = port;
    }

    @Override
    public String getProxyId() {
        return proxyId;
    }

    public void setProxyId(String proxyId) {
        this.proxyId = proxyId;
    }

    @Override
    public String getVo() {
        return vo;
    }

    public void setVo(String vo) {
        this.vo = vo;
    }

    @Override
    public String getFqan() {
        return fqan;
    }

    public void setFqan(String fqan) {
        this.fqan = fqan;
    }

    @Override
    public boolean getProxyRenewal() {
        return proxyRenewal;
    }

    public void setProxyRenewal(boolean proxyRenewal) {
        this.proxyRenewal = proxyRenewal;
    }

    @Override
    public boolean isRfc() {
        return rfc;
    }

    public void setRfc(boolean rfc) {
        this.rfc = rfc;
    }

    @Override
    public String toString() {
        return "ETokenServerCredential{" + "name=" + serverName + ", port=" + serverPort + ", proxyId=" + proxyId + ", vo=" + vo + ", fqan=" + fqan + ", proxyRenewal=" + proxyRenewal + ", RFC=" + rfc + '}';
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getProxyPath() {
        return "";
    }

    @Override
    public String getKeystorePath() {
        return "";
    }

    @Override
    public String getKeystorePassword() {
        return "";
    }
    
}
