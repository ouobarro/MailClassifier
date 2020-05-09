/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.mailFileReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.mail.util.MimeMessageParser;

/**
 *
 * @author barro
 */
public class CustomMessage implements Serializable {
    
    private final String fileName;
    private final Properties props;
    private final Session session;
    private InputStream mailFileInputStream;
    private MimeMessage mimeMessage;
    private MimeMessageParser mimeMessageParser;
    
    
    public CustomMessage(String fileName) {
        this.fileName = fileName;
        this.props = new Properties();
        this.session = Session.getDefaultInstance(props, null);
        this.readMailFile();
    }
    
    final void readMailFile() {
        try {
            this.mailFileInputStream = new FileInputStream(this.fileName);
            this.mimeMessage = new MimeMessage(session, mailFileInputStream);
            this.mimeMessageParser = new MimeMessageParser(mimeMessage);
            this.mimeMessageParser.parse();
        } catch (FileNotFoundException | MessagingException ex) {
            Logger.getLogger(MimeMessageUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch(Exception ex){
            Logger.getLogger(MimeMessageUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public InternetAddress getFrom(){
        InternetAddress internetAddress = null;
        try {
            Address[] fromAddress = this.mimeMessage.getFrom();
            internetAddress = InternetAddress.parse(fromAddress[0].toString())[0];
        } catch (MessagingException ex) {
            System.out.println(">> Erreur: FROM");
            Logger.getLogger(CustomMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return internetAddress;
    }
    
    public InternetAddress[] getTo(){
        String toList = "";
        InternetAddress[] internetAddressList = null;
        try {
            List<Address> toAddress = this.mimeMessageParser.getTo();
            if(toAddress != null && (!toAddress.isEmpty())){
                for(Address address : toAddress){
                    toList += address.toString()+",";
                }
                internetAddressList = InternetAddress.parse(toList);
            }
        } catch (Exception ex) {
            System.out.println(">> Erreur: To");
            Logger.getLogger(CustomMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return MimeMessageUtil.removeDuplicateAddress(internetAddressList);
    }
    
    public InternetAddress[] getCc(){
        String toList = "";
        InternetAddress[] internetAddressList = null;
        try {
            List<Address> toAddress = this.mimeMessageParser.getCc();
            if(toAddress != null && (!toAddress.isEmpty())){
                for(Address address : toAddress){
                    toList += address.toString()+",";
                }
                internetAddressList = InternetAddress.parse(toList);
            }
        } catch (Exception ex) {
            System.out.println(">> Erreur: Cc");
            Logger.getLogger(CustomMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return MimeMessageUtil.removeDuplicateAddress(internetAddressList);
    }
    
    public String getSubject(){
        String subject = "";
        try {
            subject = this.mimeMessageParser.getSubject();
        } catch (Exception ex) {
            System.out.println(">> Erreur: SUBJECT");
            Logger.getLogger(CustomMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subject;
    }
    
    public String getContent(){
        String content = "";
        try {
            content = MimeMessageUtil.getBodyTextFromMessage(mimeMessage);
        } catch (IOException | MessagingException ex) {
            System.out.println(">> Erreur: MESSAGE CONTENT");
            Logger.getLogger(CustomMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return content;
    }
    
    public Date getSendDate(){
        Date date = null;
        try {
            date = this.mimeMessage.getSentDate();
        } catch (MessagingException ex) {
            System.out.println(">> Erreur: SEND DATE");
            Logger.getLogger(CustomMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }
    
    public List<Attach> getFileAttach(){
        List<Attach> attachList = new ArrayList<>();
        try {
            attachList = MimeMessageUtil.getMessageAttach(mimeMessage);
        } catch (MessagingException | IOException ex) {
            System.out.println(">> Erreur: ATTACHMENT");
        }
        return attachList;
    }
    
    public List<String> getMailLinks(){
        try{
            return MimeMessageUtil.getMailLinks(this.mimeMessage);
        } catch(Exception ex){
            System.out.println(">> Erreur: LINKS");
        }
        
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "CustmMessage{"
                + "\nFrom: " +getFrom()
                + "\nSubject: "+getSubject()
                + "\nTo: "+Arrays.toString(getTo())
                + "\nCc: "+Arrays.toString(getCc())
                + "\nOn: "+getSendDate().toString()
                + "\nContent:\n "+getContent()
                + "\nAttach: \n"+getFileAttach()
                +'}';
    }
    
    

}
