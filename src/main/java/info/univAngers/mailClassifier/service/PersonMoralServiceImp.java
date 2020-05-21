/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.service;

import info.univAngers.mailClassifier.dao.PersonMoralDaoInterface;
import info.univAngers.mailClassifier.dto.EmailDto;
import info.univAngers.mailClassifier.dto.PersonMoralDto;
import info.univAngers.mailClassifier.model.Email;
import info.univAngers.mailClassifier.model.PersonMoral;
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
public class PersonMoralServiceImp implements PersonMoralServiceInterface {
    
    @Autowired
    private PersonMoralDaoInterface personMoralDao;
    
    @Override
    public List<PersonMoralDto> getAllPersonMoral() throws Exception {
        try{
            List<PersonMoral> personList = personMoralDao.getAllPersonMoral();
            List<PersonMoralDto> personDtoList = new ArrayList<>();
            if(personList != null){
                for(PersonMoral personM: personList){
                    personDtoList.add(EntityDtoConverter.convertToDto(personM));
                }
            }
            return personDtoList;
        } catch(Exception ex){
           throw ex;    
        }
    }
    
    @Override
    public List<EmailDto> getPersonMoralEmailList(Integer idPERSONM) throws Exception {
        try{
            PersonMoral person = personMoralDao.getPersonMoralById(idPERSONM);
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
    public PersonMoralDto getPersonMoralById(Integer id) throws Exception {
        try{
            PersonMoral person = personMoralDao.getPersonMoralById(id);
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
    public PersonMoralDto getPersonMoralByName(String name) throws Exception {
        try{
            PersonMoral person = personMoralDao.getPersonMoralByName(name);
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
    public void insertPersonMoral(PersonMoral person) throws Exception {
        try{
            personMoralDao.insertPersonMoral(person);
        }catch(Exception ex){
           throw ex;    
        }
    }   
}
