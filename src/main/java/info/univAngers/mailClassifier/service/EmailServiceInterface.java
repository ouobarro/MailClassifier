/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.service;

import info.univAngers.mailClassifier.dto.EmailDto;
import info.univAngers.mailClassifier.model.Email;
import java.util.List;

/**
 *
 * @author barro
 */
public interface EmailServiceInterface {
    
    public EmailDto getEmailById(Integer idEmail) throws Exception;
    
    public EmailDto getEmailByAdress(String adress) throws Exception;
    
    public List<EmailDto> getAllEmail() throws Exception;
        
    public void insertEmail(Email email) throws Exception;
}
