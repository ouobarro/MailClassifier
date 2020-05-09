/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.service;

import info.univAngers.mailClassifier.dao.AttachTypeDaoInterface;
import info.univAngers.mailClassifier.dto.AttachTypeDto;
import info.univAngers.mailClassifier.model.AttachType;
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
public class AttachTypeServiceImp implements AttachTypeServiceInterface {
    
    @Autowired
    private AttachTypeDaoInterface attachTypeDao;

    @Override
    public List<AttachTypeDto> getAllAttachType() throws Exception {
        try{
            List<AttachType> attachTypeList = attachTypeDao.getAllAttachType();
            List<AttachTypeDto> attachTypeDtoList = new ArrayList<>();
            if(attachTypeList != null){
                for(AttachType attachType: attachTypeList){
                    attachTypeDtoList.add(EntityDtoConverter.convertToDto(attachType));
                }
            }
            return attachTypeDtoList;
        } catch(Exception ex){
           throw ex;    
        }
    }
    
}
