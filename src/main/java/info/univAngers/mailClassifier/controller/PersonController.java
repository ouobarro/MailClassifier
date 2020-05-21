/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.controller;

import info.univAngers.mailClassifier.dto.EmailDto;
import info.univAngers.mailClassifier.dto.PersonDto;
import info.univAngers.mailClassifier.model.Person;
import info.univAngers.mailClassifier.service.PersonServiceInterface;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public List<PersonDto> getAllPerson() throws Exception {

        return personService.getAllPerson();

    }

    @PostMapping("/personnes")
    public void insertperson(@Valid @RequestBody Person person) throws Exception {

        personService.insertPerson(person);

    }

    // Get a Single person by id
    @GetMapping("/personnes/{id}")
    public PersonDto getPersonById(@PathVariable(value = "id") Integer idPerson) throws Exception {
        try{
            return personService.getPersonById(idPerson);
        }catch(Exception ex){
            return null;
        }
        
    }

    // Get a single person by name
    /*
    @GetMapping("/personnes/{name}")
    public PersonDto getPersonByName(@PathVariable(value = "name") String name) throws Exception {
       try{
            return personService.getPersonByName(name);
        }catch(Exception ex){
            return null;
        }  
    }
    */
    
    // Get a single person by name
    @GetMapping("/emails/personne/{idPerson}")
    public List<EmailDto> getPersonEmailList(@PathVariable(value = "idPerson") Integer idPerson) throws Exception {
        try{
            return personService.getPersonEmailList(idPerson);
        }catch(Exception ex){
            return null;
        }
    }

}
