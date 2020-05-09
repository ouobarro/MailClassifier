/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.dao;

import info.univAngers.mailClassifier.model.AttachType;
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
public class AttachTypeDaoImp implements AttachTypeDaoInterface{
    
    @PersistenceContext
    private transient EntityManager em;

    @Override
    public AttachType getAttachTypeById(Integer idAttachType) throws Exception {
        try{
            return em.find(AttachType.class, idAttachType);
        }catch(NoResultException ex){
            return null;
        } catch(Exception ex){
            throw ex;
        }
    }
    
    @Override
    public AttachType getAttachTypeByName(String typeName) throws Exception {
        try{
            return (AttachType) em.createQuery("SELECT a FROM AttachType a WHERE a.attachTypeName = :typeName")
                    .setParameter("typeName", typeName).getSingleResult();
        }catch(NoResultException ex){
            return null;
        } catch(Exception ex){
            throw ex;
        }
    }

    @Override
    public List<AttachType> getAllAttachType() throws Exception {
        try{
            return em.createQuery("SELECT a FROM AttachType a").getResultList();
        } catch(NoResultException ex){
            return null;
        }  catch(Exception ex){
            throw ex;
        }
    }
    @Override
    public void insertAttachType(AttachType attachType) throws Exception {
        try {
            em.persist(attachType);
            em.flush();
        } catch (Exception ex) {
            throw ex;
        }
    }
    
}
