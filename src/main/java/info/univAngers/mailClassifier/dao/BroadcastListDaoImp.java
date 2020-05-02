/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.dao;

import info.univAngers.mailClassifier.model.BroadcastList;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author barro
 */
@Repository
@Transactional
public class BroadcastListDaoImp implements BroadcastListDaoInterface {
    
    @PersistenceContext
    private transient EntityManager em;

    @Override
    public BroadcastList getBroadcastListById(Integer idBroadcastList) throws Exception {
        try{
            return em.find(BroadcastList.class, idBroadcastList);
        }catch(NoResultException nrex){
            return null;
        }catch(Exception ex){
            throw ex;
        }
    }

    @Override
    public BroadcastList getBroadcastListByLibelle(String broadcastListLibelle) throws Exception {
        try{
            return (BroadcastList) em.createQuery("SELECT b FROM BroadcastList b WHERE b.libelle = :libelle")
                    .setParameter("libelle", broadcastListLibelle).getSingleResult();
        }catch(Exception ex){
            throw ex;
        }
    }
    
}
