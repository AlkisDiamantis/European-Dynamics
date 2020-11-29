package com.europeandynamics.controllersTest;

import com.europeandynamics.controllers.UserController;
import com.europeandynamics.dto.UserDTO;
import com.europeandynamics.models.User;
import com.europeandynamics.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
 class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void TestGetUserById() throws Exception {

        User alex = new User();
        alex.setName("alex");

        given(userService.findUserByid(11)).willReturn(alex);

        ObjectMapper objectMapper = new ObjectMapper();

        String expectedJson = objectMapper.writeValueAsString(alex);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{userId}", 11);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String response = result.getResponse().getContentAsString();
        
        
        Assertions.assertEquals(expectedJson,response);
    }

    @Test
    void testUserPaging() throws Exception {

        User user = new User(1, "alkis", "alkis@email", "/myimage");
        User user2 = new User(2, "alkis2", "alkis@email2", "/myimage2");

        List<User> usersList = new ArrayList();

        usersList.add(user);
        usersList.add(user2);

        Page<User> pagedResponse = new PageImpl(usersList);

        Mockito.when(userService.findUsersPage(0, 1)).thenReturn(pagedResponse);

        long total = pagedResponse.getTotalElements();

        UserDTO usersDTO = new UserDTO(usersList, total);

        ObjectMapper objectMapper = new ObjectMapper();

        String expectedJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(usersDTO);

        
        mockMvc.perform(MockMvcRequestBuilders.get("/users").param("page", "0").param("limit", "1")).andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

}
