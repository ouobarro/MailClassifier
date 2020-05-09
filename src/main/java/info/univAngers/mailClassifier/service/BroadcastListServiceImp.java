/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.service;

import info.univAngers.mailClassifier.dao.BroadcastListDaoInterface;
import info.univAngers.mailClassifier.dto.BroadcastListDto;
import info.univAngers.mailClassifier.model.BroadcastList;
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
public class BroadcastListServiceImp implements BroadcastListServiceInterface{
    
    @Autowired
    private BroadcastListDaoInterface  broadcastListDao;

    @Override
    public BroadcastListDto getBroadcastListById(Integer id) throws Exception {
        try{
            BroadcastList broadcastList = broadcastListDao.getBroadcastListById(id);
            if(broadcastList != null){
                return EntityDtoConverter.convertToDto(broadcastList);
            }else{
                return null;
            }
        }catch(Exception ex){
            throw ex;
        }
    }

    @Override
    public BroadcastListDto getBroadcastListByLibelle(String libelle) throws Exception {
        try{
            BroadcastList broadcastList = broadcastListDao.getBroadcastListByLibelle(libelle);
            if(broadcastList != null){
                return EntityDtoConverter.convertToDto(broadcastList);
            }else{
                return null;
            }
        }catch(Exception ex){
            throw ex;
        }
    }

    @Override
    public List<BroadcastListDto> getAllBroadcastList() throws Exception {
        try{
            List<BroadcastList> broadcastList = broadcastListDao.getAllBroadcastList();
            List<BroadcastListDto> broadcastListDto = new ArrayList<>();
            if(broadcastList != null){
                for(BroadcastList bcl: broadcastList){
                    broadcastListDto.add(EntityDtoConverter.convertToDto(bcl));
                }
            }
            return broadcastListDto;
        } catch(Exception ex){
           throw ex;    
        }
    }
    
}
