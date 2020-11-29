package com.europeandynamics.controllersTest;

import com.europeandynamics.controllers.CommentsController;
import com.europeandynamics.dto.CommentsDTO;
import com.europeandynamics.exceptions.CustomExceptionDetails;
import com.europeandynamics.models.Comment;
import com.europeandynamics.models.Post;
import com.europeandynamics.models.User;
import com.europeandynamics.services.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
@WebMvcTest(controllers = CommentsController.class)
 class CommentsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Test
    void testGetComments() throws Exception {
        int userId = 1;
        int postId = 1;

        User user = new User(userId, "alkis", "alkis@hotmail", "/myimage");

        Post mypost = new Post(postId, user, "first post");

        Comment mycomment = new Comment(1, "first posts firs comment", user, mypost);

        List<Comment> commentList = new ArrayList();

        commentList.add(mycomment);

        mypost.setPostComments(commentList);

        given(postService.getUserPost(userId, postId)).willReturn(mypost);

        List<CommentsDTO> expectedCommentList = mypost.getPostComments().stream().map(comment -> new CommentsDTO(comment)).collect(Collectors.toList());

        ObjectMapper objectMapper = new ObjectMapper();

        String expectedJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(expectedCommentList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{userId}/posts/{postId}/comments", 1, 1);

        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    void testGetCommentsWhenPostOrUserIsNull() throws Exception {

        int userId = 0;
        int postId = 1;
        Date myDate = new Date();

        CustomExceptionDetails expectedDetails = new CustomExceptionDetails(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(myDate), "Cannot find user's post with user id :" + userId + " and post id :" + postId);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm"));

        String expectedJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(expectedDetails);

        given(postService.getUserPost(userId, postId)).willReturn(null);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{userId}/posts/{postId}/comments", userId, postId);

        mockMvc.perform(requestBuilder).andExpect(content().json(expectedJson));

    }

}
