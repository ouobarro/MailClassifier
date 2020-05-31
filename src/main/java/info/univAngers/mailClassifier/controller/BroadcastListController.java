/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.controller;

import info.univAngers.mailClassifier.dto.EmailDto;
import info.univAngers.mailClassifier.service.BroadcastListServiceInterface;
import java.util.List;
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
public class BroadcastListController {
    
    @Autowired
    private BroadcastListServiceInterface bclService;
    
    @GetMapping("/emails/broadcastlist/{idBcl}")
    public List<EmailDto> getBclEmailList(@PathVariable(value = "idBcl") Integer idBcl) throws Exception {
        try{
            return bclService.getBclEmailList(idBcl);
        }catch(Exception ex){
            return null;
        }
    }
    
}
