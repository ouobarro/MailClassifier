/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.service;

import info.univAngers.mailClassifier.dao.BroadcastListDaoInterface;
import info.univAngers.mailClassifier.model.BroadcastList;
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
    public BroadcastList getBroadcastListById(Integer idBroadcastList) throws Exception {
        try{
            return broadcastListDao.getBroadcastListById(idBroadcastList);
        }catch(Exception ex){
            throw ex;
        }
    }

    @Override
    public BroadcastList getBroadcastListByLibelle(Integer broadcastListLibelle) throws Exception {
        try{
            return broadcastListDao.getBroadcastListByLibelle(broadcastListLibelle);
        }catch(Exception ex){
            throw ex;
        }
    }
    
}
