/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author barro
 */
@Entity
@Table(name = "email")
public class Email  implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_email", nullable = false)
    @Getter
    private Integer idEmail;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "email_address", length = 200)
    @Getter
    @Setter
    private String emailAddress;
       
    @Size(max = 500)
    @Column(name = "email_signature", length = 500)
    @Getter
    @Setter
    private String emailSignature;
    
    @JoinColumn(name = "person_id", referencedColumnName = "id_person", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Getter
    @Setter
    private Person person;
    
    @JoinColumn(name = "broadcast_list_id", referencedColumnName = "id_broadcast_list", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @Getter
    @Setter
    private BroadcastList broadcastList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "email", fetch = FetchType.LAZY)
    @Getter @Setter
    private List<Mail> mailList;
    
     @ManyToMany(mappedBy = "receiverList")
    @Getter @Setter
    private List<Mail> receivedMailList;

    @ManyToMany(mappedBy = "receiverCcList")
    @Getter @Setter
    private List<Mail> receivedCcMailList;

    public Email() {
    }

    public Email(String emailAddrtess, String emailSignature) {
        this.emailAddress = emailAddress;
        this.emailSignature = emailSignature;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.idEmail);
        hash = 43 * hash + Objects.hashCode(this.emailAddress);
        hash = 43 * hash + Objects.hashCode(this.emailSignature);
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
        final Email other = (Email) obj;
        if (!Objects.equals(this.emailAddress, other.emailAddress)) {
            return false;
        }
        if (!Objects.equals(this.emailSignature, other.emailSignature)) {
            return false;
        }
        if (!Objects.equals(this.idEmail, other.idEmail)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Email{" + "idEmail=" + idEmail + ", emailAddress=" + emailAddress + ", emailSignature=" + emailSignature + ", person=" + person + '}';
    }
    
    
    
}
