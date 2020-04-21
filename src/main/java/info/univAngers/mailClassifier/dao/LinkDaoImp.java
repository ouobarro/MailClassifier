/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.dao;

import info.univAngers.mailClassifier.model.Link;
import java.util.List;
import javax.persistence.EntityManager;
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
        } catch(Exception ex){
            throw ex;
        }
    }

    @Override
    public Link getLinkByIdLink(Integer idLink) throws Exception {
        try{
            return em.find(Link.class, idLink);
        } catch(Exception ex){
            throw ex;
        }
    }
    
}
