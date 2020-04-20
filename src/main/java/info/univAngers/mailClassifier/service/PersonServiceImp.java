/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.service;

import info.univAngers.mailClassifier.dao.PersonDaoInterface;
import info.univAngers.mailClassifier.model.Person;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author barro
 */
@Service
@Transactional
public class PersonServiceImp implements PersonServiceInterface {
    
    @Autowired
    private PersonDaoInterface personDao;
    
    @Override
    public List<Person> getAllPerson() throws Exception {
        try{
            return personDao.getAllPerson();
        } catch(Exception ex){
           throw ex;    
        }
    }

    @Override
    public Person getPersonById(int idPerson) throws Exception {
        try{
            return personDao.getPersonById(idPerson);  
        }catch(Exception ex){
           throw ex;    
        }
    }
    
    @Override
    public Person getPersonByEmailAddress(String emailAddress) throws Exception {
        try{
            return personDao.getPersonByEmailAddress(emailAddress);  
        }catch(Exception ex){
           throw ex;    
        }
    }

    @Override
    public void insertPerson(Person person) throws Exception {
        try{
            personDao.insertPerson(person);
        }catch(Exception ex){
           throw ex;    
        }
    }

    
    
}
