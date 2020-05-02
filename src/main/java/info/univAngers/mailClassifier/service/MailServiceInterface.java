/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.service;

import info.univAngers.mailClassifier.dto.MailDto;
import info.univAngers.mailClassifier.mailFileReader.CustomMessage;
import info.univAngers.mailClassifier.model.Mail;
import java.util.List;

/**
 *
 * @author barro
 */
public interface MailServiceInterface {
    
    public List<MailDto> getAllMail() throws Exception;
    
    public List<MailDto> getMailByPerson(Integer idPerson) throws Exception;
    
    public void insertMail(Mail mail) throws Exception;
    
    public void insertMail(CustomMessage custmMessage) throws Exception;
    
}
