/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author barro
 */
@Entity
@Table(name = "person_income_mail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonIncomeMail.findAll", query = "SELECT p FROM PersonIncomeMail p"),
    @NamedQuery(name = "PersonIncomeMail.findByPersonId", query = "SELECT p FROM PersonIncomeMail p WHERE p.person.idPerson = :personId"),
    @NamedQuery(name = "PersonIncomeMail.findByMailId", query = "SELECT p FROM PersonIncomeMail p WHERE p.mail.idMail = :mailId"),
    @NamedQuery(name = "PersonIncomeMail.findByReceiveDate", query = "SELECT p FROM PersonIncomeMail p WHERE p.receiveDate = :receiveDate")})
public class PersonIncomeMail implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @JoinColumn(name = "mail_id", referencedColumnName = "id_mail", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Getter @Setter
    private Mail mail;
    
    @Id
    @JoinColumn(name = "person_id", referencedColumnName = "id_person", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Getter @Setter
    private Person person;
   
    @Temporal(TemporalType.DATE)
    @Getter @Setter
    private Date receiveDate;
    

    public PersonIncomeMail() {
    }

    public PersonIncomeMail(Mail mail, Person person) {
        this.mail = mail;
        this.person = person;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.mail);
        hash = 11 * hash + Objects.hashCode(this.person);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PersonIncomeMail other = (PersonIncomeMail) obj;
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        if (!Objects.equals(this.person, other.person)) {
            return false;
        }
        if (!Objects.equals(this.receiveDate, other.receiveDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PersonIncomeMail{" + "mail=" + mail + ", person=" + person + ", receiveDate=" + receiveDate + '}';
    }


}
