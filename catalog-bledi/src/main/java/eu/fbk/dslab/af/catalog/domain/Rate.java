package eu.fbk.dslab.af.catalog.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;
     

    
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

    

}


