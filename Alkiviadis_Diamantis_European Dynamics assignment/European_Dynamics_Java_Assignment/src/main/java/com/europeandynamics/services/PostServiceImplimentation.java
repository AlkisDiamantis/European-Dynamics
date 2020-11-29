
package com.europeandynamics.services;

import com.europeandynamics.exceptions.ApiException;
import com.europeandynamics.models.Post;
import com.europeandynamics.models.User;
import com.europeandynamics.repositories.PostRepository;
import com.europeandynamics.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImplimentation implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    
    @Override
    public Post getUserPost(int postId, int userId) throws ApiException {

        User user = userRepository.findById(userId).orElseThrow(() -> new ApiException("User not found with id :" + userId));
        
        if( postRepository.findByIdAndUser(postId, user)==null){
        throw new ApiException("Post not found with id : "+ postId);
        }
       
        return postRepository.findByIdAndUser(postId, user);
    }

}
