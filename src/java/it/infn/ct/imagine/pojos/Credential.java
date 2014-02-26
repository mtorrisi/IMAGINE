/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.infn.ct.imagine.pojos;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

/**
 *
 * @author mario
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = ProxyCredential.class, name = "proxy"),
    @JsonSubTypes.Type(value = ETokenServerCredential.class, name = "eTokenServer"),
    @JsonSubTypes.Type(value = KeystoreCedential.class, name = "keystore"),
    @JsonSubTypes.Type(value = UsernamePasswordCredential.class, name = "UserPass")
})

public abstract class Credential {

    public enum Type {

        proxy, eTokenServer, keystore, UserPass
    }

    Type type;

    protected Credential(Type t) {
        this.type = t;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public abstract String getProxyPath();

    public abstract String getServerName();

    public abstract String getServerPort();

    public abstract String getProxyId();

    public abstract String getVo();

    public abstract String getFqan();

    public abstract boolean getProxyRenewal();

    public abstract boolean isRfc();

    public abstract String getUsername();

    public abstract String getPassword();

    public abstract String getKeystorePath();

    public abstract String getKeystorePassword();
}
