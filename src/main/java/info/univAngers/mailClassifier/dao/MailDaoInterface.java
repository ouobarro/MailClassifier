/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.dao;

import info.univAngers.mailClassifier.model.Mail;
import info.univAngers.mailClassifier.model.Person;
import java.util.List;

/**
 *
 * @author barro
 */
public interface MailDaoInterface {
    
    public Mail getMailById(Integer idMail) throws Exception;
    
    public List<Mail> getMailByPerson(Integer idPerson) throws Exception;
    
    public void insertMail(Mail mail) throws Exception;
    
    
}
