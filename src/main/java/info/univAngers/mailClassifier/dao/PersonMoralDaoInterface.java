/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.dao;

import info.univAngers.mailClassifier.model.PersonMoral;
import java.util.List;

/**
 *
 * @author barro
 */
public interface PersonMoralDaoInterface {
    
    public List<PersonMoral> getAllPersonMoral() throws Exception;
    
    public PersonMoral getPersonMoralById(Integer idPersonM) throws Exception;
    
    public PersonMoral getPersonMoralByName(String name) throws Exception;
    
    public void insertPersonMoral(PersonMoral personM) throws Exception;
    
    public Integer countPersonMoral() throws Exception;
}
