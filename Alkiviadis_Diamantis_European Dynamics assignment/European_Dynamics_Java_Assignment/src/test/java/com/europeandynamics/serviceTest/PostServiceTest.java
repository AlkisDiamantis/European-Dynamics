package com.europeandynamics.serviceTest;

import com.europeandynamics.exceptions.ApiException;
import com.europeandynamics.models.Post;
import com.europeandynamics.models.User;
import com.europeandynamics.repositories.PostRepository;
import com.europeandynamics.repositories.UserRepository;
import com.europeandynamics.services.PostServiceImplimentation;
import java.util.Optional;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
 class PostServiceTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PostRepository postRepository;

    @InjectMocks
    private PostServiceImplimentation postService;

    @Test
    void testGetUserPost() throws ApiException {

        int postId = 1;
        int userId = 1;

        User user = new User(1, "alkis", "email@email", "/myImages");

        Post expectedPost = new Post(1, user, "first post");

        Optional<User> optionalUser = Optional.of(user);

        Mockito.when(userRepository.findById(userId)).thenReturn(optionalUser);

        Mockito.when(postRepository.findByIdAndUser(postId, user)).thenReturn(expectedPost);

        
        
        
        Post mypost = postService.getUserPost(postId, userId);

        Assert.assertEquals(expectedPost, mypost);

    }

    @Test
    void testGetUserPost_UserNotFound() {

        int postId = 1;
        int userId = 1;

        Exception exception = assertThrows(ApiException.class, () -> {

            postService.getUserPost(postId, userId);

        });

        Assertions.assertEquals("User not found with id :" + userId, exception.getMessage());
    }

    
    @Test
    void testGetUserPost_PostNotFound() {

        int postId = 1;
        int userId = 1;

        User user = new User(1, "alkis", "email@email", "/myImages");

        Optional<User> optionalUser = Optional.of(user);

        Mockito.when(userRepository.findById(userId)).thenReturn(optionalUser);

        Exception exception = assertThrows(ApiException.class, () -> {

            postService.getUserPost(postId, userId);

        });

        Assertions.assertEquals("Post not found with id : "+ postId, exception.getMessage());
    }

}
