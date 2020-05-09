/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.service;

import info.univAngers.mailClassifier.dao.LinkDaoInterface;
import info.univAngers.mailClassifier.dto.LinkDto;
import info.univAngers.mailClassifier.model.Link;
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
public class LinkServiceImp implements LinkServiceInterface{
    
    @Autowired
    private LinkDaoInterface linkDao;
    
    @Override
    public List<LinkDto> getAllLink() throws Exception {
        try{
            List<Link> linkList = linkDao.getAllLink();
            List<LinkDto> linkDtoList = new ArrayList<>();
            if(null != linkList){
                for(Link link: linkList){
                    linkDtoList.add(EntityDtoConverter.convertToDto(link));
                }
            }
            return linkDtoList;
        } catch(Exception ex){
           throw ex;    
        }
    }
    
}
