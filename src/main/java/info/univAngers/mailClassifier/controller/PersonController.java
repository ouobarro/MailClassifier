/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.controller;

import info.univAngers.mailClassifier.model.Person;
import info.univAngers.mailClassifier.service.PersonServiceInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PersonController {
    
    @Autowired
    private PersonServiceInterface personService;
    
    @GetMapping("/personnes")
    public List<Person> getAllPerson() throws Exception{
        return personService.getAllPerson();
    }
    
}
