/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.dao;

import info.univAngers.mailClassifier.dto.BroadcastListDto;
import info.univAngers.mailClassifier.model.BroadcastList;
import info.univAngers.mailClassifier.model.Email;
import java.util.List;
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
        } catch(NoResultException nrex){
            return null;
        } catch(Exception ex){
            throw ex;
        }
    }

    @Override
    public List<BroadcastList> getAllBroadcastList() throws Exception {
        try {
            return em.createQuery("SELECT b FROM BroadcastList b").getResultList();
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public Integer countBroadcastList() throws Exception {
        Integer count = 0;
        try{
            Object o = em.createQuery("SELECT COUNT(b) FROM BroadcastList b").getSingleResult();
            if(o != null){
                count = ((Long) o).intValue();
            }
        } catch(NoResultException noEx){
            count = 0;
        }catch(Exception ex){
            throw ex;
        }
        return count;
    }
}
