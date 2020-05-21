/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.controller;

import info.univAngers.mailClassifier.dto.EmailDto;
import info.univAngers.mailClassifier.dto.PersonMoralDto;
import info.univAngers.mailClassifier.model.PersonMoral;
import info.univAngers.mailClassifier.service.PersonMoralServiceInterface;
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
public class PersonMoralController {

    @Autowired
    private PersonMoralServiceInterface personMoralService;

    @GetMapping("/personnes-morales")
    public List<PersonMoralDto> getAllPersonMoral() throws Exception {

        return personMoralService.getAllPersonMoral();

    }

    @PostMapping("/personnes-morales")
    public void insertperson(@Valid @RequestBody PersonMoral personM) throws Exception {

        personMoralService.insertPersonMoral(personM);

    }

    // Get a Single person by id
    @GetMapping("/personnes-morales/{id}")
    public PersonMoralDto getPersonMoralById(@PathVariable(value = "id") Integer idPerson) throws Exception {
        try{
            return personMoralService.getPersonMoralById(idPerson);
        }catch(Exception ex){
            return null;
        }
        
    }

    /*
    // Get a single person by name
    @GetMapping("/personnes-morales/{name}")
    public PersonMoralDto getPersonByName(@PathVariable(value = "name") String name) throws Exception {
        return personMoralService.getPersonMoralByName(name);
    }
*/
    
    // Get a single person by name
    @GetMapping("/emails/personne-morale/{idPersonM}")
    public List<EmailDto> getPersonMoralEmailList(@PathVariable(value = "idPersonM") Integer idPerson) throws Exception {
        try{
            return personMoralService.getPersonMoralEmailList(idPerson);
        }catch(Exception ex){
            return null;
        }
    }

}
