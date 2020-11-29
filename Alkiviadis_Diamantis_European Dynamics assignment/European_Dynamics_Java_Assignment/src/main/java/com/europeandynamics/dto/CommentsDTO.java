
package com.europeandynamics.dto;

import com.europeandynamics.models.Comment;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @NoArgsConstructor
public class CommentsDTO {

    private int id;

    private int postId;

    private Date createdAt;

    private String email;

    private String avatar;

    private String name;

    private String body;

    public CommentsDTO(Comment comment) {
        this.id = comment.getId();
        this.postId =  comment.getPost().getId();
        this.createdAt = comment.getDatetimeEstablished();
        this.email =  comment.getUser().getEmail();      
        this.avatar = comment.getUser().getAvatar();
        this.name = comment.getUser().getUsername();
        this.body = comment.getBody();
    }
    

}
