/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.service;

import info.univAngers.mailClassifier.dao.AttachTypeDaoInterface;
import info.univAngers.mailClassifier.dao.BroadcastListDaoInterface;
import info.univAngers.mailClassifier.dao.EmailDaoInterface;
import info.univAngers.mailClassifier.dao.LinkDaoInterface;
import info.univAngers.mailClassifier.dao.MailDaoInterface;
import info.univAngers.mailClassifier.dao.PersonDaoInterface;
import info.univAngers.mailClassifier.dto.MailDto;
import info.univAngers.mailClassifier.mailFileReader.Attach;
import info.univAngers.mailClassifier.mailFileReader.CustomMessage;
import info.univAngers.mailClassifier.model.AttachType;
import info.univAngers.mailClassifier.model.Attachment;
import info.univAngers.mailClassifier.model.BroadcastList;
import info.univAngers.mailClassifier.model.Email;
import info.univAngers.mailClassifier.model.Link;
import info.univAngers.mailClassifier.model.Mail;
import info.univAngers.mailClassifier.model.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    @Autowired
    private EmailDaoInterface emailDao;

    @Autowired
    private AttachTypeDaoInterface attachTypeDao;

    @Autowired
    private LinkDaoInterface linkDao;

    @Override
    public List<MailDto> getAllMail() throws Exception {
        try {
            List<Mail> mailList = mailDao.getAllMail();
            List<MailDto> mailDtoList = new ArrayList<>();
            if (mailList != null) {
                for (Mail mail : mailList) {
                    mailDtoList.add(EntityDtoConverter.convertToDto(mail));
                }
            }
            return mailDtoList;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public List<MailDto> getMailByEmail(Integer idEmail) throws Exception {
        try {
            List<Mail> mailList = mailDao.getMailByEmail(idEmail);
            List<MailDto> mailDtoList = new ArrayList<>();
            if (mailList != null) {
                for (Mail mail : mailList) {
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

            // Set Mail Sender
            Email sender;
            InternetAddress fromAddress = customMessage.getFrom();
            sender = emailDao.getEmailByAdress(fromAddress.getAddress());
            if (sender == null) {
                sender = new Email();
                sender.setEmailAddress(fromAddress.getAddress());
                if (fromAddress.getPersonal() != null && (!fromAddress.getPersonal().isEmpty())) {
                    Person person = personDao.getPersonByName(getClearName(fromAddress.getPersonal()));
                    if (person == null) {
                        person = new Person();
                        person.setName(getClearName(fromAddress.getPersonal()));
                    }
                    sender.setPerson(person);
                }
                
                // Set Mail BroadcastList
                String bclName = this.getBcListFromMail(fromAddress.getAddress());
                if (bclName != null && (!bclName.isEmpty())) {
                    BroadcastList broadcastList = broadcastListDao.getBroadcastListByLibelle(bclName);
                    if (broadcastList == null) {
                        broadcastList = new BroadcastList();
                        broadcastList.setLibelle(bclName);

                    }
                    sender.setBroadcastList(broadcastList);
                }
                
            }

            mail.setEmail(sender);

            // Set Mail Receivers (PersonIncomeMail => List)
            List<Email> toList = new ArrayList<>();
            InternetAddress[] toAddressList = customMessage.getTo();
            if (toAddressList.length > 0) {
                for (InternetAddress iAddr : toAddressList) {
                    if (iAddr.getAddress().equalsIgnoreCase(sender.getEmailAddress())) {
                        toList.add(sender);
                    } else {
                        Email email = emailDao.getEmailByAdress(iAddr.getAddress());
                        if (email == null) {
                            email = new Email();
                            email.setEmailAddress(iAddr.getAddress());

                            if (iAddr.getPersonal() != null && (!iAddr.getPersonal().isEmpty())) {
                                Person person = personDao.getPersonByName(getClearName(iAddr.getPersonal())); // Attention si deux personnes ont le même name
                                if (person == null) {
                                    person = new Person();
                                    person.setName(getClearName(iAddr.getPersonal()));
                                }
                                email.setPerson(person);
                            }

                            // Set Mail BroadcastList
                            String bclName = this.getBcListFromMail(iAddr.getAddress());
                            if (bclName != null && (!bclName.isEmpty())) {
                                BroadcastList broadcastList = broadcastListDao.getBroadcastListByLibelle(bclName);
                                if (broadcastList == null) {
                                    broadcastList = new BroadcastList();
                                    broadcastList.setLibelle(bclName);

                                }
                                email.setBroadcastList(broadcastList);
                            }
                        }
                        toList.add(email);
                    }
                }
                mail.setReceiverList(toList);
            }

            // Set Mail Receivers copy 
            List<Email> ccList = new ArrayList<>();
            InternetAddress[] ccAddressList = customMessage.getCc();
            if (toAddressList.length > 0) {

                for (InternetAddress iAddr : ccAddressList) {
                    if (iAddr.getAddress().equalsIgnoreCase(sender.getEmailAddress())) {
                        ccList.add(sender);
                    } else {
                        Email email = emailDao.getEmailByAdress(iAddr.getAddress());
                        if (email == null) {
                            email = new Email();
                            email.setEmailAddress(iAddr.getAddress());

                            if (iAddr.getPersonal() != null && (!iAddr.getPersonal().isEmpty())) {
                                Person person = personDao.getPersonByName(getClearName(iAddr.getPersonal())); // Attention si deux personnes ont le même name
                                if (person == null) {
                                    person = new Person();
                                    person.setName(getClearName(iAddr.getPersonal()));
                                }
                                email.setPerson(person);
                            }
                            
                            // Set Mail BroadcastList
                            String bclName = this.getBcListFromMail(iAddr.getAddress());
                            if (bclName != null && (!bclName.isEmpty())) {
                                BroadcastList broadcastList = broadcastListDao.getBroadcastListByLibelle(bclName);
                                if (broadcastList == null) {
                                    broadcastList = new BroadcastList();
                                    broadcastList.setLibelle(bclName);
                                }
                                email.setBroadcastList(broadcastList);
                            }
                        }
                        ccList.add(email);
                    }
                }
                mail.setReceiverCcList(ccList);
            }

            //Mail attach
            if (customMessage.getFileAttach() != null) {
                List<Attachment> attachList = new ArrayList<>();
                for (Attach attach : customMessage.getFileAttach()) {
                    Attachment att = new Attachment();
                    att.setName(attach.getFileName());
                    att.setAttachmentPath(attach.getAttPath());
                    AttachType attachType;
                    if (attach.getExtension() != null && (!attach.getExtension().isEmpty()) && (attach.getExtension().length() <= 12)) {
                        attachType = attachTypeDao.getAttachTypeByName(attach.getExtension().toUpperCase());
                        if (attachType == null) {
                            //Verifier aussi que "attach.getExtension()" nest pas vide ou NULL
                            attachType = new AttachType();
                            attachType.setAttachTypeName(attach.getExtension().toUpperCase());
                        }
                        att.setAttachType(attachType);
                    } else {
                        System.out.println("\t>> Problème avec extension: " + attach.getExtension());
                    }

                    att.setMail(mail);
                    attachList.add(att);
                }
                mail.setAttachmentList(attachList);
            }

            //Mail links
            if (customMessage.getMailLinks() != null) {
                List<Link> mLinkList = new ArrayList<>();
                for (String link : customMessage.getMailLinks()) {
                    Link mLink = new Link();
                    mLink.setUrlLink(link);

                    mLink.setMail(mail);
                    mLinkList.add(mLink);
                }
                mail.setLinkList(mLinkList);
            }

            mailDao.insertMail(mail);

        } catch (Exception ex) {
            throw ex;
        }
    }
    
    protected String getClearName(String name){
        String cName = name.replaceAll("'", "");
        return cName;
    }

    protected String getBcListFromMail(String email) {
        String bcl = null;

        Pattern pattern = Pattern.compile("([\\w\\p{Punct}]+)(@listes[\\.]|@groupes[\\.])");
        Matcher matcher = pattern.matcher(email);
        if (matcher.find()) {
            bcl = matcher.group(1);
        }

        return bcl;
    }

}
