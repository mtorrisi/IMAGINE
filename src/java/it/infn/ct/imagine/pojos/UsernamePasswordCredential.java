/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.infn.ct.imagine.pojos;

/**
 *
 * @author mario
 */
class UsernamePasswordCredential extends Credential {

    String username;
    String password;

    public UsernamePasswordCredential() {
        super(Type.UserPass);
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UsernamePasswordCredential{" + "username=" + username + ", password=" + password + '}';
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

    @Override
    public boolean getProxyRenewal() {
        return false;
    }

    @Override
    public boolean isRfc() {
        return false;
    }
}
