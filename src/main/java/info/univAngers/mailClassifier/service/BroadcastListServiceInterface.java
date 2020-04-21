/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.service;

import info.univAngers.mailClassifier.model.BroadcastList;

/**
 *
 * @author barro
 */
public interface BroadcastListServiceInterface {
    
    public BroadcastList getBroadcastListById(Integer idBroadcastList) throws Exception;
    
    public BroadcastList getBroadcastListByLibelle(Integer broadcastListLibelle) throws Exception;
    
}
