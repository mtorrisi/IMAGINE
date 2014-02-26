/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.infn.ct.imagine.pojos;

/**
 *
 * @author mario
 */
public class ProxyCredential extends Credential {

    String proxyPath;

    public ProxyCredential() {
        super(Type.proxy);
    }

    @Override
    public String getProxyPath() {
        return proxyPath;
    }

    public void setProxyPath(String proxyPath) {
        this.proxyPath = proxyPath;
    }

    @Override
    public String toString() {
        return "ProxyCredential{" + "proxyPath=" + proxyPath + '}';
    }

    @Override
    public String getServerName() {
        return "";
    }

    @Override
    public String getServerPort() {
        return "";
    }

    @Override
    public String getProxyId() {
        return "";
    }

    @Override
    public String getVo() {
        return "";
    }

    @Override
    public String getFqan() {
        return "";
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
    public String getKeystorePath() {
        return "";
    }

    @Override
    public String getKeystorePassword() {
        return "";
    }

    @Override
    public boolean getProxyRenewal() {
        return false;
    }

    @Override
    public boolean isRfc() {
        return false;
    }
}
