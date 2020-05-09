/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.dao;

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
public class EmailDaoImp implements EmailDaoInterface {
    
    @PersistenceContext
    private transient EntityManager em;

    @Override
    public Email getEmailById(Integer idEmail) throws Exception {
        try{
            return em.find(Email.class, idEmail);
        }catch(NoResultException ex){
            return null;
        } catch(Exception ex){
            throw ex;
        }
    }
    
    @Override
    public Email getEmailByAdress(String adress) throws Exception {
        try{
            return (Email) em.createQuery("SELECT e FROM Email e WHERE e.emailAddress = :adress")
                    .setParameter("adress", adress).getSingleResult();
        } catch(NoResultException ex){
            return null;
        }  catch(Exception ex){
            throw ex;
        }
    }

    @Override
    public List<Email> getAllEmail() throws Exception {
        try{
            return em.createQuery("SELECT e FROM Email e").getResultList();
        } catch(NoResultException ex){
            return null;
        }  catch(Exception ex){
            throw ex;
        }
    }

    @Override
    public void insertEmail(Email email) throws Exception {
        try {
            em.persist(email);
            em.flush();
        } catch (Exception ex) {
            throw ex;
        }
    }
    
}
