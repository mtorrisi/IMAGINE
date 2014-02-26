/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.infn.ct.imagine.pojos;

/**
 *
 * @author mario
 */
class KeystoreCedential extends Credential {

    String keystorePath;
    String keystorePassword;

    public KeystoreCedential() {
        super(Type.keystore);
    }

    @Override
    public String getKeystorePath() {
        return keystorePath;
    }

    public void setKeystorePath(String keystorePath) {
        this.keystorePath = keystorePath;
    }

    @Override
    public String getKeystorePassword() {
        return keystorePassword;
    }

    public void setKeystorePassword(String keystorePassword) {
        this.keystorePassword = keystorePassword;
    }

    @Override
    public String toString() {
        return "KeystoreCedential{" + "keystorePath=" + keystorePath + ", keystorePassword=" + keystorePassword + '}';
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
    public String getProxyPath() {
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
