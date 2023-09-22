package eu.fbk.dslab.af.catalog.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.GeneratedValue;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Rate")
public class Rate {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer Vote;
    private String Message;
    private Integer foreignkey;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getTitle() {
        return Vote;
    }
    public void setTitle(Integer Vote) {
        this.Vote = Vote;
    }
    public String getCategory() {
        return Message;
    }
    public void setCategory(String Message) {
        this.Message = Message;
    }

    @OneToOne(mappedBy = "Product", fetch = FetchType.LAZY, orphanRemoval = false)
    public Integer getforeignkeyUser() {
        return foreignkey;
    }
    public void setforeignkeyUser(Integer foreignkey) {
        this.foreignkey = foreignkey;
    }

    @OneToOne(mappedBy = "User", fetch = FetchType.LAZY, orphanRemoval = false)
    public Integer getforeignkeyProd() {
        return foreignkey;
    }
    public void setforeignkeyProd(Integer foreignkey) {
        this.foreignkey = foreignkey;
    }
   

}


