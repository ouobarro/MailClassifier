/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.dao;

import info.univAngers.mailClassifier.model.Mail;
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
public class MailDaoImp implements MailDaoInterface {
    
    @PersistenceContext
    private transient EntityManager em;
    
    @Override
    public Mail getMailById(Integer idMail) throws Exception {
        try{
            return em.find(Mail.class, idMail);
        }catch(NoResultException ex){
            return null;
        } catch(Exception ex){
            throw ex;
        }
    }

    @Override
    public List<Mail> getMailByPerson(Integer idPerson) throws Exception {
        try{
            return em.createQuery("SELECT m FROM Mail m where m.sender.idPerson = :idPerson")
                    .setParameter("idPerson", idPerson).getResultList();
            
        }catch(NoResultException ex){
            return null;
        } catch(Exception ex){
            throw ex;
        }
    }
    
    @Override
    public List<Mail> getAllMail() throws Exception {
        try{
            return em.createQuery("SELECT m FROM Mail m").getResultList();
        } catch(NoResultException ex){
            return null;
        }  catch(Exception ex){
            throw ex;
        }
    }

    @Override
    public void insertMail(Mail mail) throws Exception {
        try {
            em.persist(mail);
            em.flush();
        } catch (Exception ex) {
            throw ex;
        }
    }
    
}
