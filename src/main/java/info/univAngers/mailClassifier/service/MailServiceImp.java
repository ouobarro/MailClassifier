/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.service;

import info.univAngers.mailClassifier.dao.BroadcastListDaoInterface;
import info.univAngers.mailClassifier.dao.MailDaoInterface;
import info.univAngers.mailClassifier.dao.PersonDaoInterface;
import info.univAngers.mailClassifier.dto.MailDto;
import info.univAngers.mailClassifier.mailFileReader.CustomMessage;
import info.univAngers.mailClassifier.mailFileReader.MimeMessageUtil;
import info.univAngers.mailClassifier.model.BroadcastList;
import info.univAngers.mailClassifier.model.Mail;
import info.univAngers.mailClassifier.model.Person;
import java.util.ArrayList;
import java.util.List;
import javax.mail.internet.InternetAddress;
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

    @Autowired
    private BroadcastListDaoInterface broadcastListDao;

    @Autowired
    private PersonDaoInterface personDao;

    @Override
    public List<MailDto> getAllMail() throws Exception {
        try {
            List<Mail> mailList = mailDao.getAllMail();
            List<MailDto> mailDtoList = new ArrayList<>();
            if(mailList != null){
                for(Mail mail: mailList){
                    mailDtoList.add(EntityDtoConverter.convertToDto(mail));
                }
            }
            return mailDtoList;
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    @Override
    public List<MailDto> getMailByPerson(Integer idPerson) throws Exception {
        try {
            List<Mail> mailList = mailDao.getMailByPerson(idPerson);;
            List<MailDto> mailDtoList = new ArrayList<>();
            if(mailList != null){
                for(Mail mail: mailList){
                    mailDtoList.add(EntityDtoConverter.convertToDto(mail));
                }
            }
            return mailDtoList;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void insertMail(Mail mail) throws Exception {
        try {
            mailDao.insertMail(mail);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void insertMail(CustomMessage customMessage) throws Exception {
        try {

            Mail mail = new Mail();

            mail.setSubject(customMessage.getSubject());
            mail.setContent(customMessage.getContent());
            mail.setSendDate(customMessage.getSendDate());

            // Set Mail BroadcastList
            BroadcastList broadcastList = broadcastListDao.getBroadcastListById(1);
            if (broadcastList != null) {
                mail.setBroadcastList(broadcastList);
            }

            // Set Mail Sender
            Person sender;
            InternetAddress fromAddress = customMessage.getFrom();
            if (MimeMessageUtil.isValidEmailAddress(fromAddress.getAddress())) {
                sender = personDao.getPersonByEmailAddress(fromAddress.getAddress());
                if (sender == null) {
                    sender = new Person();
                    sender.setPersonEmailAddress(fromAddress.getAddress());
                    sender.setName(fromAddress.getPersonal());
                }
            } else {
                //Adresse email non valide (Faut-il continuer ???)
                System.out.println(">> INVALID EMAIL ADDRESS !!!");
                sender = new Person();
                sender.setPersonEmailAddress(fromAddress.getAddress());
                sender.setName(fromAddress.getPersonal());
            }
            mail.setSender(sender);

            // Set Mail Receivers (PersonIncomeMail => List)
            List<Person> toList = new ArrayList<>();
            InternetAddress[] toAddressList = customMessage.getTo();
            if (toAddressList.length > 0) {
                for (InternetAddress iAddr : toAddressList) {
                    if (iAddr.getAddress().equalsIgnoreCase(sender.getPersonEmailAddress())) {
                        toList.add(sender);
                    } else {
                        Person person = personDao.getPersonByEmailAddress(iAddr.getAddress());
                        if (person == null) {
                            person = new Person();
                            person.setName(iAddr.getPersonal());
                            person.setPersonEmailAddress(iAddr.getAddress());
                        }
                        toList.add(person);
                    }
                }
                mail.setReceiverList(toList);
            }
            
            // Set Mail Receivers copy 
            List<Person> ccList = new ArrayList<>();
            InternetAddress[] ccAddressList = customMessage.getCc();
            if (toAddressList.length > 0) {
                for (InternetAddress iAddr : ccAddressList) {
                    if (iAddr.getAddress().equalsIgnoreCase(sender.getPersonEmailAddress())) {
                        ccList.add(sender);
                    } else {
                        Person person = personDao.getPersonByEmailAddress(iAddr.getAddress());
                        if (person == null) {
                            person = new Person();
                            person.setName(iAddr.getPersonal());
                            person.setPersonEmailAddress(iAddr.getAddress());
                        }
                        ccList.add(person);
                    }
                }
                mail.setReceiverList(ccList);
            }

            mailDao.insertMail(mail);

        } catch (Exception ex) {
            throw ex;
        }
    }

}
