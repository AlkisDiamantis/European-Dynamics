package com.europeandynamics.controllersTest;

import com.europeandynamics.controllers.PostController;
import com.europeandynamics.dto.PostDTO;
import com.europeandynamics.models.Post;
import com.europeandynamics.models.User;
import com.europeandynamics.services.PostService;
import com.europeandynamics.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PostController.class)
 class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private PostService postService;

    @Test
    void testGetUserPost() throws Exception {

        User user = new User(1, "alkis", "alkis@hotmail", "/myimages");

        Post post = new Post(1, user, "alkis first post");
        

        given(postService.getUserPost(1, 1)).willReturn(post);

        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(post);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{userId}/posts/{postId}", 1, 1);

        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(content().json(json));

    }

    @Test

    void testGetUsersPosts() throws Exception {

        User user = new User(1, "alkis", "alkis@hotmail", "/myImage");

        Post post1 = new Post(1, user, "first post");
        Post post2 = new Post(2, user, "Second post");

        List<Post> postlist = new ArrayList<>();

        postlist.add(post1);
        postlist.add(post2);

        user.setUserPosts(postlist);

        given(userService.findUserByid(1)).willReturn(user);

        List<PostDTO> postResponse = postlist.stream().map(post -> new PostDTO(post)).collect(Collectors.toList());

        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(postResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{userId}/posts", 1);

        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(content().json(json));
    }

}
