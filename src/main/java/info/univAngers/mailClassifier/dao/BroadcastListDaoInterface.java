/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.dao;

import info.univAngers.mailClassifier.dto.BroadcastListDto;
import info.univAngers.mailClassifier.model.BroadcastList;
import java.util.List;

/**
 *
 * @author barro
 */
public interface BroadcastListDaoInterface {
    
    public BroadcastList getBroadcastListById(Integer idBroadcastList) throws Exception;
    
    public BroadcastList getBroadcastListByLibelle(String broadcastListLibelle) throws Exception;
    
    public List <BroadcastList> getAllBroadcastList() throws Exception;
    
}
