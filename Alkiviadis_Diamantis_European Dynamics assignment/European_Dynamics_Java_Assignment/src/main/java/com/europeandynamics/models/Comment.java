
package com.europeandynamics.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comments")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Comment implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetimeEstablished;

    @Basic(optional = false)
    @Column(name = "body")
    private String body;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonManagedReference
    private User user;

    @JoinColumn(name = "post_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonManagedReference
    private Post post;


    public Comment(Integer id, String body, User user, Post post) {
        this.id = id;
        this.body = body;
        this.user = user;
        this.post = post;
    }
    
    
    public Comment(Date datetimeEstablished, String body, User user, Post post) {
        this.datetimeEstablished = datetimeEstablished;
        this.body = body;
        this.user = user;
        this.post = post;
    }

}
