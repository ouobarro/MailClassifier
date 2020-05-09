/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.service;

import info.univAngers.mailClassifier.dao.EmailDaoInterface;
import info.univAngers.mailClassifier.dto.EmailDto;
import info.univAngers.mailClassifier.model.Email;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author barro
 */
@Service
@Transactional
public class EmailServiceImp implements EmailServiceInterface{
    
    @Autowired
    private EmailDaoInterface emailDao;

    @Override
    public EmailDto getEmailById(Integer idEmail) throws Exception {
        try{
            Email email = emailDao.getEmailById(idEmail);
            if(email != null){
                return EntityDtoConverter.convertToDto(email);
            }else{
                return null;
            }
        }catch(Exception ex){
           throw ex;    
        }
    }
    
    @Override
    public EmailDto getEmailByAdress(String adress) throws Exception {
        try{
            Email email = emailDao.getEmailByAdress(adress);
            if(email != null){
                return EntityDtoConverter.convertToDto(email);
            }else{
                return null;
            }
        }catch(Exception ex){
           throw ex;    
        }
    }

    @Override
    public List<EmailDto> getAllEmail() throws Exception {
        try{
            List<Email> emailList = emailDao.getAllEmail();
            List<EmailDto> emailDtoList = new ArrayList<>();
            if(emailList != null){
                for(Email email: emailList){
                    emailDtoList.add(EntityDtoConverter.convertToDto(email));
                }
            }
            return emailDtoList;
        } catch(Exception ex){
           throw ex;    
        }
    }

    @Override
    public void insertEmail(Email email) throws Exception {
        try{
            emailDao.insertEmail(email);
        }catch(Exception ex){
           throw ex;    
        }
    }
    
}
