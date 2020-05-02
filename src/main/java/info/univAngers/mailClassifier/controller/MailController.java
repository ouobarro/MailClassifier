/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.controller;

import info.univAngers.mailClassifier.dto.MailDto;
import info.univAngers.mailClassifier.mailFileReader.CustomMessage;
import info.univAngers.mailClassifier.model.Mail;
import info.univAngers.mailClassifier.service.MailServiceInterface;
import java.io.File;
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

    @GetMapping("/test-mail-read")
    public CustomMessage testMailFileRead() {
        System.out.println("================== ECHO FILE READING!!! ==================");
        try {
            CustomMessage message = new CustomMessage("president_2010-06" + File.separator + "10");
            mailService.insertMail(message);
            return message;
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
    
    @GetMapping("/mails/person/{id}")
    public List<MailDto> getMailByPerson(@PathVariable(value = "id") Integer id) throws Exception {
         try{
            return mailService.getMailByPerson(id);
        }catch(Exception ex){
            return null;
        }
    }


}
