/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.dao;

import info.univAngers.mailClassifier.model.Email;
import java.util.List;

/**
 *
 * @author barro
 */
public interface EmailDaoInterface {
    
    public Email getEmailById(Integer idEmail) throws Exception;
    
    public Email getEmailByAdress(String adress) throws Exception;
    
    public List<Email> getAllEmail() throws Exception;
        
    public void insertEmail(Email email) throws Exception;
    
}
