/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.service;

import info.univAngers.mailClassifier.dto.EmailDto;
import info.univAngers.mailClassifier.dto.PersonMoralDto;
import info.univAngers.mailClassifier.model.PersonMoral;
import java.util.List;

/**
 *
 * @author barro
 */
public interface PersonMoralServiceInterface {
    
    public List<PersonMoralDto> getAllPersonMoral() throws Exception;
    
    public List<EmailDto> getPersonMoralEmailList(Integer idPERSONM) throws Exception;
    
    public PersonMoralDto getPersonMoralById(Integer id) throws Exception;
    
    public PersonMoralDto getPersonMoralByName(String name) throws Exception;
    
    public void insertPersonMoral(PersonMoral person) throws Exception;
    
}
