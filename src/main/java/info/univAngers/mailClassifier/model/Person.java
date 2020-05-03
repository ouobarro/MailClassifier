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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author barro
 */
@Entity
@Table(name = "person")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "Person.findByIdperson", query = "SELECT p FROM Person p WHERE p.idPerson = :idPerson"),
    @NamedQuery(name = "Person.findByName", query = "SELECT p FROM Person p WHERE p.name = :name"),
    @NamedQuery(name = "Person.findByPersonEmailAddress", query = "SELECT p FROM Person p WHERE p.personEmailAddress = :personEmailAddress")})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_person", nullable = false)
    @Getter
    private Integer idPerson;
    
    @Size(max = 80)
    @Column(name = "name", length = 80)
    @Getter @Setter
    private String name;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "person_email_address", length = 45)
    private String personEmailAddress;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender", fetch = FetchType.LAZY)
    @Getter @Setter
    private List<Mail> mailList;
    
    @ManyToMany(mappedBy = "receiverList")
    @Getter @Setter
    private List<Mail> receivedMailList;

    @ManyToMany(mappedBy = "receiverCcList")
    @Getter @Setter
    private List<Mail> receivedCcMailList;
    
    public Person() {
    }

    public Person(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public Person(Integer idPerson, String personEmailAddress) {
        this.idPerson = idPerson;
        this.personEmailAddress = personEmailAddress;
    }

    public String getPersonEmailAddress() {
        return personEmailAddress;
    }

    public void setPersonEmailAddress(String personEmailAddress) {
        this.personEmailAddress = personEmailAddress;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerson != null ? idPerson.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.idPerson == null && other.idPerson != null) || (this.idPerson != null && !this.idPerson.equals(other.idPerson))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "info.univAngers.mailClassifier.model.Person[ idPerson=" + idPerson + " ]";
    }
    
}
