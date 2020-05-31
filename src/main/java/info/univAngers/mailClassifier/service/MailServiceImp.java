/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.service;

import static ch.qos.logback.core.util.OptionHelper.isEmpty;
import info.univAngers.mailClassifier.dao.AttachTypeDaoInterface;
import info.univAngers.mailClassifier.dao.AttachmentDaoInterface;
import info.univAngers.mailClassifier.dao.BroadcastListDaoInterface;
import info.univAngers.mailClassifier.dao.EmailDaoInterface;
import info.univAngers.mailClassifier.dao.LinkDaoInterface;
import info.univAngers.mailClassifier.dao.MailDaoInterface;
import info.univAngers.mailClassifier.dao.PersonDaoInterface;
import info.univAngers.mailClassifier.dao.PersonMoralDaoInterface;
import info.univAngers.mailClassifier.dto.DataCountDto;
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
import info.univAngers.mailClassifier.model.PersonMoral;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.internet.AddressException;
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
    private PersonMoralDaoInterface personMoralDao;

    @Autowired
    private EmailDaoInterface emailDao;

    @Autowired
    private AttachTypeDaoInterface attachTypeDao;

    @Autowired
    private AttachmentDaoInterface attachDao;

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
    public MailDto getMailById(Integer id) throws Exception {
        try{
            Mail mail = mailDao.getMailById(id);
            if(mail != null){
                return EntityDtoConverter.convertToDto(mail);
            }else{
                return null;
            }
        }catch(Exception ex){
           throw ex;    
        }
    }

    @Override
    public List<MailDto> getAllSendedMailByEmailId(Integer idEmail) throws Exception {
        try {
            List<Mail> mailList = mailDao.getAllSendedMailByEmailId(idEmail);
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
    public List<MailDto> getAllReceivedMailByEmailId(Integer idEmail) throws Exception {
        try {
            Email email = emailDao.getEmailById(idEmail);
            List<Mail> mailList = null;
            if (email != null) {
                mailList = email.getReceivedMailList();
            }
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
    public List<MailDto> getAllReceivedCcMailByEmailId(Integer idEmail) throws Exception {
        try {
            Email email = emailDao.getEmailById(idEmail);
            List<Mail> mailList = null;
            if (email != null) {
                mailList = email.getReceivedCcMailList();
            }
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
    public DataCountDto countData() throws Exception {
        DataCountDto dataCount = new DataCountDto();
        try {
            Integer attachCount = this.attachDao.countAttachment();
            Integer bclCount = this.broadcastListDao.countBroadcastList();
            Integer emailCount = this.emailDao.countEmail();
            Integer personCount = this.personDao.countPerson();
            Integer mailCount = this.mailDao.countMail();
            Integer linkCount = this.linkDao.countLink();
            Integer personMoralCount = this.personMoralDao.countPersonMoral();

            dataCount.setAttachCount(attachCount);
            dataCount.setBclCount(bclCount);
            dataCount.setEmailCount(emailCount);
            dataCount.setPersonCount(personCount);
            dataCount.setMailCount(mailCount);
            dataCount.setLinkCount(linkCount);
            dataCount.setPersonMoralCount(personMoralCount);
        } catch (Exception ex) {
            throw ex;
        }
        return dataCount;
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

            mail.setSubject(getClearSubject(customMessage.getSubject()));
            mail.setContent(customMessage.getContent());
            mail.setSendDate(customMessage.getSendDate());

            // Set Mail Sender
            Email sender;
            InternetAddress fromAddress = customMessage.getFrom();
            sender = emailDao.getEmailByAdress(fromAddress.getAddress());
            if (sender == null) {
                sender = new Email();
                sender.setEmailAddress(fromAddress.getAddress());
                if (isValidName(fromAddress.getPersonal())) {
                    String clearName = getClearName(fromAddress.getPersonal());
                    Person person = personDao.getPersonByName(clearName);
                    if (person == null) {
                        person = new Person();
                        person.setName(clearName);
                    }
                    sender.setPerson(person);
                } else {    //***********************************************Modifications apportées
                    String clearName = getClearName(fromAddress.getPersonal());
                    PersonMoral personM = personMoralDao.getPersonMoralByName(clearName);

                    String clearAddress = getClearName(fromAddress.getAddress());
                    PersonMoral personMo = personMoralDao.getPersonMoralByName(clearAddress);

                    if (personM == null && clearName != null && !isEmpty(clearName) && !isQuestionMark(clearName)) {
                        personM = new PersonMoral();
                        personM.setName(clearName);
                        sender.setPersonM(personM);
                    } else if (personM != null) {
                        sender.setPersonM(personM);
                    } else if (personMo == null) {
                        personMo = new PersonMoral();
                        personMo.setName(clearAddress);
                        sender.setPersonM(personMo);
                    } else {
                        sender.setPersonM(personMo);
                    }

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

                            if (isValidName(iAddr.getPersonal())) {
                                String clearName = getClearName(iAddr.getPersonal());
                                Person person = personDao.getPersonByName(clearName); // Attention si deux personnes ont le même name
                                if (person == null) {
                                    person = new Person();
                                    person.setName(clearName);
                                }
                                email.setPerson(person);
                            } else {  //*******************************Modifications apportées
                                String clearName = getClearName(iAddr.getPersonal());
                                PersonMoral personM = personMoralDao.getPersonMoralByName(clearName);

                                String clearAddress = getClearName(iAddr.getAddress());
                                PersonMoral personMo = personMoralDao.getPersonMoralByName(clearAddress);

                                if (personM == null && clearName != null && !isEmpty(clearName) && !isQuestionMark(clearName)) {
                                    personM = new PersonMoral();
                                    personM.setName(clearName);
                                    email.setPersonM(personM);
                                } else if (personM != null) {
                                    email.setPersonM(personM);
                                } else if (personMo == null) {
                                    personMo = new PersonMoral();
                                    personMo.setName(clearAddress);
                                    email.setPersonM(personMo);
                                } else {
                                    email.setPersonM(personMo);
                                }
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

                            if (isValidName(iAddr.getPersonal())) {
                                String clearName = getClearName(iAddr.getPersonal());
                                Person person = personDao.getPersonByName(clearName); // Attention si deux personnes ont le même name
                                if (person == null) {
                                    person = new Person();
                                    person.setName(clearName);
                                }
                                email.setPerson(person);
                            } else {  //*********************************Modifications apportées 
                                String clearName = getClearName(iAddr.getPersonal());
                                PersonMoral personM = personMoralDao.getPersonMoralByName(clearName);

                                String clearAddress = getClearName(iAddr.getAddress());
                                PersonMoral personMo = personMoralDao.getPersonMoralByName(clearAddress);

                                if (personM == null && clearName != null && !isEmpty(clearName) && !isQuestionMark(clearName)) {
                                    personM = new PersonMoral();
                                    personM.setName(clearName);
                                    email.setPersonM(personM);
                                } else if (personM != null) {
                                    email.setPersonM(personM);
                                } else if (personMo == null) {
                                    personMo = new PersonMoral();
                                    personMo.setName(clearAddress);
                                    email.setPersonM(personMo);
                                } else {
                                    email.setPersonM(personMo);
                                }
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

    protected String getClearName(String name) {
        System.out.println("\t >>> NAME: " + name);
        if(name != null){
            String cName = name.replaceAll("'", "");
        cName = cName.replace("\"", "");
        return cName;
        }
        return "";
        
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

    protected String getClearSubject(String subject) {

        String sjt = null;
        final Matcher matcher = Pattern.compile("\\[president\\]").matcher(subject);
        if (matcher.find()) {
            sjt = (subject.substring(matcher.end()).trim());
        }
        return sjt;
    }

    protected boolean isQuestionMark(String name) {
        
        if(name == null){
            return true;
        }else{
            return name.contains("?");
        }
        
        /*
        final Pattern pattern = Pattern.compile("[\\?+]");

        if (name != null) {
            if (name.isEmpty()) {
                return true;
            } else {
                Matcher matcher = pattern.matcher(name);
                if (matcher.find()) {
                    return true;
                }
                return false;
            }

        } else {
            return true;
        }
    */
    }

    protected boolean isContainExpression(String name) {

        if (name != null) {
            if (name.isEmpty()) {
                return true;
            } else {
                List<Pattern> list = new ArrayList<>();
                list.add(Pattern.compile("[0123456789]"));
                list.add(Pattern.compile("^.*[\\(\\)].*$"));
                list.add(Pattern.compile("^.*[\\[\\]].*$"));
                list.add(Pattern.compile("[\\.]"));
                list.add(Pattern.compile("[\\/]"));
                list.add(Pattern.compile("[\\_]"));
                list.add(Pattern.compile("vp|maison|WEBMAIL|SERVICE|webmaster|Cité|Paris|Europe|Service|Secretariat|salondesmaires|MANS|Lettre|presidence|Rendez|Ouest|président|Solution|Directeur|CP|Rédaction|BMS|CIO|directrice|POUILLE|SUIO|Directrice|Professionals|contact|learning|Infos|Meetings|Pôle|OPPE|Direction|BESANCON|PAU|POITIERS|POLYNESIE|REIMS|ROUEN|SAINT|TOULON|TOURS|UT|MULHOUSE|NANTES|NICE|NIMES|ORLEANS|METZ|VALLEE|BREST|LITTORAL|BRETAGNE|CHAMBERY|CORTE|DIJON|EVRY|REUNION|ROCHELLE|HAVRE|LIMOGES|CAEN|Directeur|AMIENS|UT|RECHERCHE|CERGY|NOUVELLE|MULHOUSE|CHAMBERY|Ticket|AVIGNON|ANTILLES|MESR|Régionale|smbh|DELL|hdg|Mouvement|admission|OUEST|ANGERS|Ecole|ESA|DAEP|Scientifique|Technologies|Liste|Web|Dii|Lycees|bureau|Chercheurs|legoutdessciences|Apec|LOTTERY|General|DSA|System|AMV|MAIF|MACSF|Méd|Habitat|Anjou|Systeme|REV|USA|Direction|Contract|Route|Maison|IUT|Pfizer|Figaro|welcome|Dr|Contact|IBS|IHEST|AEDD|VP|VPCA|Présidence|etudiant|dircom|CDLA|WEBMASTER|CONTACT|Président|AGENCE|FRANCE|Institut|Secrétariat|directeur|INTELLIGENT|Conférences|Candidatures|iperu|Sphinx|RuraliTIC|France|president|Plante|Presidente|rev|Espace|Cabinet|University|publics|SEMINAIRES|DMS|Conseil|DELL|Université|ipde|SG|Secrétaire|Direction,Ile|SECRETARIAT|MEDEF|Exclusive|Restaurants|president|FINANCE"));
                for (Pattern pattern : list) {
                    Matcher matcher = pattern.matcher(name);
                    if (matcher.find()) {
                        return true;
                    }
                }
                return false;
            }

        } else {
            return true;
        }
    }

    protected boolean isValidName(String name) {

        if (isQuestionMark(name)) {
            return false;
        } else if (isContainExpression(name)) {
            return false;
        } else {
            final Pattern pattern = Pattern.compile("([\\w\\p{Punct}]*)(@[\\w\\p{Punct}]*)");
            if (name != null) {
                if (name.isEmpty()) {
                    return false;
                } else {
                    System.out.println("\t >> NAME: " + name);
                    Matcher matcher = pattern.matcher(name);
                    if (matcher.find()) {
                        return false;
                    }
                    return true;
                }

            } else {
                return false;
            }
        }

    }

}
