/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.service;

import info.univAngers.mailClassifier.dao.PersonDaoInterface;
import info.univAngers.mailClassifier.dto.EmailDto;
import info.univAngers.mailClassifier.dto.PersonDto;
import info.univAngers.mailClassifier.model.Email;
import info.univAngers.mailClassifier.model.Person;
import java.util.ArrayList;
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
    public List<PersonDto> getAllPerson() throws Exception {
        try{
            List<Person> personList = personDao.getAllPerson();
            List<PersonDto> personDtoList = new ArrayList<>();
            if(personList != null){
                for(Person person: personList){
                    personDtoList.add(EntityDtoConverter.convertToDto(person));
                }
            }
            return personDtoList;
        } catch(Exception ex){
           throw ex;    
        }
    }
    
    @Override
    public List<EmailDto> getPersonEmailList(Integer idPERSON) throws Exception {
        try{
            Person person = personDao.getPersonById(idPERSON);
            List<Email> emailList = person.getEmailList();
            List<EmailDto> emailDtoList = new ArrayList<>();
            if(emailList != null){
                for(Email email: emailList){
                    emailDtoList.add(EntityDtoConverter.convertToDto(email));
                }
            }
            return emailDtoList;
        } catch(Exception ex){
           throw ex;    
        }
    }

    @Override
    public PersonDto getPersonById(Integer id) throws Exception {
        try{
            Person person = personDao.getPersonById(id);
            if(person != null){
                return EntityDtoConverter.convertToDto(person);
            }else{
                return null;
            }
        }catch(Exception ex){
           throw ex;    
        }
    }
    
    @Override
    public PersonDto getPersonByName(String name) throws Exception {
        try{
            Person person = personDao.getPersonByName(name);
            if(person != null){
                return EntityDtoConverter.convertToDto(person);
            }else{
                return null;
            }
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
