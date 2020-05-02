/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.dao;

import info.univAngers.mailClassifier.model.Attachment;
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
public class AttachmentDaoImp implements AttachmentDaoInterface {
    
    @PersistenceContext
    private transient EntityManager em;

    @Override
    public Attachment getAttachmentByIdAttachment(Integer idAttachment) throws Exception {
        try{
            return em.find(Attachment.class, idAttachment);
        } catch(Exception ex){
            throw ex;
        }
    }

    @Override
    public List<Attachment> getAttachmentByMail(Integer idMail) throws Exception {
        try{
            //SELECT l FROM Link l WHERE l.mail.idMail = :idMail
            return em.createQuery("SELECT a FROM Attachment a WHERE a.mail.idMail = :idMail")
                    .setParameter("idMail", idMail).getResultList();
        } catch(Exception ex){
            throw ex;
        }
    }
    
}
