/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.dao;

import info.univAngers.mailClassifier.model.Person;
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
public class PersonDaoImp implements PersonDaoInterface {

    @PersistenceContext
    private transient EntityManager em;

    @Override
    public List<Person> getAllPerson() {
        try {
            return em.createQuery("SELECT p FROM Person p").getResultList();
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public Person getPersonById(Integer idPerson) {
        try {
            return em.find(Person.class, idPerson);
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    @Override
    public Person getPersonByName(String name) throws Exception {
        try {
            return (Person) em.createQuery("SELECT p FROM Person p where p.name = :name")
                    .setParameter("name", name).getSingleResult();
        } catch (NoResultException nrex) {
            return null;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void insertPerson(Person person) {
        try {
            em.persist(person);
            em.flush();
        } catch (Exception ex) {
            throw ex;
        }
    }

    

}
