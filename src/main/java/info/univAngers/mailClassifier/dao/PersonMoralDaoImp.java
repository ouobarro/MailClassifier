/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.dao;

import info.univAngers.mailClassifier.model.PersonMoral;
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
public class PersonMoralDaoImp implements PersonMoralDaoInterface {

    @PersistenceContext
    private transient EntityManager em;

    @Override
    public List<PersonMoral> getAllPersonMoral() {
        try {
            return em.createQuery("SELECT p FROM PersonMoral p order by p.name").getResultList();
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public PersonMoral getPersonMoralById(Integer idPersonM) {
        try {
            return em.find(PersonMoral.class, idPersonM);
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    @Override
    public PersonMoral getPersonMoralByName(String name) throws Exception {
        try {
            return (PersonMoral) em.createQuery("SELECT p FROM PersonMoral p where p.name = :name")
                    .setParameter("name", name).getSingleResult();
        } catch (NoResultException nrex) {
            return null;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void insertPersonMoral(PersonMoral personM) {
        try {
            em.persist(personM);
            em.flush();
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public Integer countPersonMoral() throws Exception {
        Integer count = 0;
        try{
            Object o = em.createQuery("SELECT COUNT(p) FROM PersonMoral p").getSingleResult();
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
