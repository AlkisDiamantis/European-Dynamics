package com.europeandynamics.controllers;

import com.europeandynamics.dto.CommentsDTO;
import com.europeandynamics.exceptions.ApiException;
import com.europeandynamics.services.PostService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CommentsController {

    @Autowired
    PostService postService;


    @GetMapping("/users/{userId}/posts/{postId}/comments")
    public List<CommentsDTO> getComments(@PathVariable("userId") int userId, @PathVariable("postId") int postId) throws ApiException {
        
        if(postService.getUserPost(postId, userId)==null){
        throw new ApiException("Cannot find user's post with user id :"+userId+" and post id :"+postId);
        }

        return postService.getUserPost(postId, userId).getPostComments().stream().map(comment -> new CommentsDTO(comment)).collect(Collectors.toList());
    }
}
