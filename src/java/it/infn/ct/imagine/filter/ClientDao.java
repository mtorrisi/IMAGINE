/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.infn.ct.imagine.filter;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author mario
 */
@Stateless
public class ClientDao {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public Client findByApiKey(EntityManager em, String uuid) {
        try {
            return em.createNamedQuery("Client.findByUuid", Client.class).setParameter("uuid", uuid).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }
    
}
