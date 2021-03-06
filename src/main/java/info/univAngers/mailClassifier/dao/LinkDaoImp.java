/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.dao;

import info.univAngers.mailClassifier.model.Link;
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
public class LinkDaoImp implements LinkDaoInterface{
    
    @PersistenceContext
    private transient EntityManager em;

    @Override
    public List<Link> getLinkByMail(Integer idMail) throws Exception {
        try{
            return em.createQuery("SELECT l FROM Link l WHERE l.mail.idMail = :idMail")
                    .setParameter("idMail", idMail).getResultList();
        } catch(NoResultException ex){
            return null;
        }catch(Exception ex){
            throw ex;
        }
    }

    @Override
    public Link getLinkByIdLink(Integer idLink) throws Exception {
        try{
            return em.find(Link.class, idLink);
        } catch(NoResultException ex){
            return null;
        }catch(Exception ex){
            throw ex;
        }
    }
    
    
     @Override
    public Link getLinkByUrlLink(String urlLink) throws Exception {
        try{
            return (Link) em.createQuery("SELECT l FROM Link l WHERE l.urlLink = :urlLink")
                    .setParameter("urlLink", urlLink).getSingleResult();
        }catch(NoResultException ex){
            return null;
        } catch(Exception ex){
            throw ex;
        }
    }

    @Override
    public List<Link> getAllLink() throws Exception {
        try{
            return em.createQuery("SELECT l FROM Link l").getResultList();
        } catch(NoResultException ex){
            return null;
        }catch(Exception ex){
            throw ex;
        }
    }

    @Override
    public Integer countLink() throws Exception {
        Integer count = 0;
        try{
            Object o = em.createQuery("SELECT COUNT(distinct l.urlLink) FROM Link l").getSingleResult();
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
