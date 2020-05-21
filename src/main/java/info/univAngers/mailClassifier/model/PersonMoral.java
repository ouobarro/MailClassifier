/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.model;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author barro
 */
@Entity
@Table(name = "person_moral")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonMoral.findAll", query = "SELECT p FROM PersonMoral p"),
    @NamedQuery(name = "PersonMoral.findByIdPersonMoral", query = "SELECT p FROM PersonMoral p WHERE p.idPersonM = :idPersonM"),
    @NamedQuery(name = "PersonMoral.findByName", query = "SELECT p FROM PersonMoral p WHERE p.name = :name"),
    })
public class PersonMoral implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_person_moral", nullable = false)
    @Getter
    private Integer idPersonM;
    
    @Size(max = 80)
    @Column(name = "name", length = 80)
    @Getter @Setter
    private String name;
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personM", fetch = FetchType.LAZY)
    @Getter @Setter
    private List<Email> emailList;
    
    public PersonMoral() {
    }

    public PersonMoral(Integer idPerson) {
        this.idPersonM = idPerson;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersonM != null ? idPersonM.hashCode() : 0);
        return hash;
    }
    
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonMoral)) {
            return false;
        }
        PersonMoral other = (PersonMoral) object;
        if ((this.idPersonM == null && other.idPersonM != null) || (this.idPersonM != null && !this.idPersonM.equals(other.idPersonM))) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "info.univAngers.mailClassifier.model.PersonMoral[ idPersonMoral=" + idPersonM + " ]";
    }
    
}
