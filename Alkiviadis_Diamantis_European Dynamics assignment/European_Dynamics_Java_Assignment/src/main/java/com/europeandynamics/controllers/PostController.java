package com.europeandynamics.controllers;

import com.europeandynamics.dto.PostDTO;
import com.europeandynamics.exceptions.ApiException;
import com.europeandynamics.models.Post;
import com.europeandynamics.services.PostService;
import com.europeandynamics.services.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class PostController {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    
    

    @GetMapping("/users/{userId}/posts")
    public List<PostDTO> getUserPosts(@PathVariable("userId") int userId) throws ApiException {

        List<Post> posts = (List) userService.findUserByid(userId).getUserPosts();

        List<PostDTO> postResponse = posts.stream().map(post -> new PostDTO(post)).collect(Collectors.toList());

        return postResponse;
    }
    
    

    @GetMapping("/users/{userId}/posts/{postId}")
    public Post getUserPost(@PathVariable("userId") int userId, @PathVariable("postId") int postId) throws ApiException {

        
        return postService.getUserPost(postId, userId);
    }

}
