/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.service;

import info.univAngers.mailClassifier.dao.MailDaoInterface;
import info.univAngers.mailClassifier.model.Mail;
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
public class MailServiceImp implements MailServiceInterface {
    
    @Autowired
    private MailDaoInterface mailDao;

    @Override
    public List<Mail> getMailByPerson(Integer idPerson) throws Exception {
        try{
            return mailDao.getMailByPerson(idPerson);
        } catch(Exception ex){
            throw ex;
        }
    }

    @Override
    public void insertMail(Mail mail) throws Exception {
        try{
            mailDao.insertMail(mail);
        } catch(Exception ex){
            throw ex;
        }
    }
    
}
