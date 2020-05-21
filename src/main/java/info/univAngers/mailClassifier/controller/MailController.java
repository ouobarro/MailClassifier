/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package info.univAngers.mailClassifier.controller;

import info.univAngers.mailClassifier.dto.AttachTypeDto;
import info.univAngers.mailClassifier.dto.AttachmentDto;
import info.univAngers.mailClassifier.dto.BroadcastListDto;
import info.univAngers.mailClassifier.dto.DataCountDto;
import info.univAngers.mailClassifier.dto.EmailDto;
import info.univAngers.mailClassifier.dto.LinkDto;
import info.univAngers.mailClassifier.dto.MailDto;
import info.univAngers.mailClassifier.mailFileReader.CustomMessage;
import info.univAngers.mailClassifier.model.Mail;
import info.univAngers.mailClassifier.service.AttachTypeServiceInterface;
import info.univAngers.mailClassifier.service.AttachmentServiceInterface;
import info.univAngers.mailClassifier.service.BroadcastListServiceInterface;
import info.univAngers.mailClassifier.service.EmailServiceInterface;
import info.univAngers.mailClassifier.service.LinkServiceInterface;
import info.univAngers.mailClassifier.service.MailServiceInterface;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author barro
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class MailController {

    @Autowired
    private MailServiceInterface mailService;
    
    @Autowired 
    private EmailServiceInterface emailService;
    
    @Autowired 
    private LinkServiceInterface linkService;
    
    @Autowired 
    private AttachmentServiceInterface attachService;
    
    @Autowired 
    private AttachTypeServiceInterface attachTypeService;
    
    @Autowired 
    private BroadcastListServiceInterface broadcastListService;

    
    @GetMapping("/mail-read/{dirPath}")
    public List<String> testMailFileRead(@PathVariable("dirPath") String dirPath) {
        System.out.println("================== ECHO FILE READING!!! ==================");
        String absolutePath = "/home/barro/NetBeansProjects/president_2010/"+dirPath;
        
        try {
            List<String> fileListName = new ArrayList<>();
            List<File> fileList = Util.getDirectoryFiles(absolutePath);
            if(fileList != null){
                for(File file: fileList){
                    CustomMessage message = new CustomMessage(file.getAbsolutePath());
                    //CustomMessage message = new CustomMessage("375");
                    //mailService.insertMail(message);
                    fileListName.add(file.getAbsolutePath());
                }
            }
            return fileListName;
        } catch (Exception ex) {
            Logger.getLogger(MailController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }


    @GetMapping("/mails")
    public List<MailDto> getAllMail() throws Exception {
        
        try{
            return this.mailService.getAllMail();
        }catch(Exception ex){
            return null;
        }
    }
    
    // Get a Single person by id
    @GetMapping("/mails/{id}")
    public MailDto getMailById(@PathVariable(value = "id") Integer idMail) throws Exception {
        return mailService.getMailById(idMail);
    }
    
    @GetMapping("/data-count")
    public DataCountDto countData() throws Exception {
        
        try{
            return this.mailService.countData();
        }catch(Exception ex){
            return null;
        }
    }
    
    @GetMapping("/mails/email/{idEmail}")
    public List<MailDto> getMailByEmail(@PathVariable("idEmail") Integer idEmail) throws Exception {
        
        try{
            return this.mailService.getAllSendedMailByEmailId(idEmail);
        }catch(Exception ex){
            return null;
        }
    }
    
    @GetMapping("/mails/received-mail/{idEmail}")
    public List<MailDto> getReceivedMailByEmailId(
            @PathVariable("idEmail")
            Integer idEmail) throws Exception {
        
        try{
            return this.mailService.getAllReceivedMailByEmailId(idEmail);
        }catch(Exception ex){
            return null;
        }
    }
    
    @GetMapping("/mails/received-cc-mail/{idEmail}")
    public List<MailDto> getReceivedCcMailByEmailId(@PathVariable("idEmail") Integer idEmail) throws Exception {
        
        try{
            return this.mailService.getAllReceivedCcMailByEmailId(idEmail);
        }catch(Exception ex){
            return null;
        }
    }
    
    //Les adresses email
    
    @GetMapping("/emails")
    public List<EmailDto> getAllEmail() throws Exception {
        
        try{
            return this.emailService.getAllEmail();
        }catch(Exception ex){
            return null;
        }
    }
    
    //Les liens
    
    @GetMapping("/links")
    public List<LinkDto> getAllLink() throws Exception {
        
        try{
            return this.linkService.getAllLink();
        }catch(Exception ex){
            return null;
        }
    }
    
    
    //Les Pièces jointes
    
    @GetMapping("/attachments")
    public List<AttachmentDto> getAllAttachment() throws Exception {
        
        try{
            return this.attachService.getAllAttachment();
        }catch(Exception ex){
            return null;
        }
    }
    
    //Les types des pièce jointe
    
    @GetMapping("/attachTypes")
    public List<AttachTypeDto> getAllAttachType() throws Exception {
        
        try{
            return this.attachTypeService.getAllAttachType();
        }catch(Exception ex){
            return null;
        }
    }
    
    //Les listes de diffusion
    
    @GetMapping("/broadcastList")
    public List<BroadcastListDto> getAllBroadcastList() throws Exception {
        
        try{
            return this.broadcastListService.getAllBroadcastList();
        }catch(Exception ex){
            return null;
        }
    }
}
