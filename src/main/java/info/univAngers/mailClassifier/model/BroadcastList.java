/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author barro
 */
@Entity
@Table(name="broadcastList")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BroadcastList.findAll", query = "SELECT b FROM BroadcastList b"),
    @NamedQuery(name = "BroadcastList.findByIdbroadcastList", query = "SELECT b FROM BroadcastList b WHERE b.idBroadcastList = :idBroadcastList"),
    @NamedQuery(name = "BroadcastList.findByLibelle", query = "SELECT b FROM BroadcastList b WHERE b.libelle = :libelle")})
public class BroadcastList implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_broadcast_list", nullable = false)
    private Integer idBroadcastList;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(nullable = false, length = 45)
    private String libelle;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "broadcastList", fetch = FetchType.LAZY)
    private List<Mail> mailList;

    public BroadcastList() {
    }

    public BroadcastList(Integer idBroadcastList) {
        this.idBroadcastList = idBroadcastList;
    }

    public BroadcastList(Integer idBroadcastList, String libelle) {
        this.idBroadcastList = idBroadcastList;
        this.libelle = libelle;
    }

    public Integer getIdbroadcastList() {
        return idBroadcastList;
    }

    public void setIdbroadcastList(Integer idBroadcastList) {
        this.idBroadcastList = idBroadcastList;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @XmlTransient
    public List<Mail> getMailList() {
        return mailList;
    }

    public void setMailList(List<Mail> mailList) {
        this.mailList = mailList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBroadcastList != null ? idBroadcastList.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BroadcastList)) {
            return false;
        }
        BroadcastList other = (BroadcastList) object;
        if ((this.idBroadcastList == null && other.idBroadcastList != null) || (this.idBroadcastList != null && !this.idBroadcastList.equals(other.idBroadcastList))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "info.univAngers.mailClassifier.model.BroadcastList[ idBroadcastList=" + idBroadcastList + " ]";
    }
    
}
