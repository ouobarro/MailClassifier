/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.controller;

import info.univAngers.mailClassifier.mailFileReader.MailFileReader;
import org.apache.commons.mail.util.MimeMessageParser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
    
    @GetMapping("/test-mail-read")
    public String testMailFileRead() throws Exception{
        System.out.println("================== ECHO FILE READING!!! ==================");
        return MailFileReader.readMailFile("fichier-test").getSubject();
    }
    
}
