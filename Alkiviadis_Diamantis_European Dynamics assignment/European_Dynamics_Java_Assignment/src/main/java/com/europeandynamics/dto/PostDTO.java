
package com.europeandynamics.dto;

import com.europeandynamics.models.Post;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter @Setter @NoArgsConstructor
public class PostDTO {
    
    private int id;
    
    private int userId;
    
    private Date createdAt;
    
    private String title;
    
    private String body;

    public PostDTO( Post post) {
        this.id = post.getId();
        this.userId = post.getUser().getId();
        this.createdAt = post.getDatetimeEstablished();
        this.title = post.getTitle();
        this.body = post.getBody();
    }
           
}
