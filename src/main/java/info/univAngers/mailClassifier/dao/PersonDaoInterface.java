/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.dao;

import info.univAngers.mailClassifier.model.Person;
import java.util.List;

/**
 *
 * @author barro
 */
public interface PersonDaoInterface {
    
    public List<Person> getAllPerson() throws Exception;
    
    public Person getPersonById(Integer idPerson) throws Exception;
    
    public Person getPersonByName(String name) throws Exception;
    
    public void insertPerson(Person person) throws Exception;
    
}
