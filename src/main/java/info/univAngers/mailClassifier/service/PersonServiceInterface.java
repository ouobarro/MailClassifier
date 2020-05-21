/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.service;

import info.univAngers.mailClassifier.dto.EmailDto;
import info.univAngers.mailClassifier.dto.PersonDto;
import info.univAngers.mailClassifier.model.Person;
import java.util.List;

/**
 *
 * @author barro
 */
public interface PersonServiceInterface {
    
    public List<PersonDto> getAllPerson() throws Exception;
    
    public List<EmailDto> getPersonEmailList(Integer idPERSON) throws Exception;
    
    public PersonDto getPersonById(Integer id) throws Exception;
    
    public PersonDto getPersonByName(String name) throws Exception;
    
    public void insertPerson(Person person) throws Exception;
    
}
