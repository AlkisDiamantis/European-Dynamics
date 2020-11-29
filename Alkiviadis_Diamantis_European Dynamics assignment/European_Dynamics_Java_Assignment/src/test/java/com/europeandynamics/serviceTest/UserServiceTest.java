package com.europeandynamics.serviceTest;

import com.europeandynamics.exceptions.ApiException;
import com.europeandynamics.models.User;
import com.europeandynamics.repositories.UserRepository;
import com.europeandynamics.services.UserServiceImplimentation;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
 class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImplimentation userService;

    @Test
    void testFindUserByid() throws Exception {
        int id = 1;

        User user = new User(1, "alkis", "email@email", "/myImages");

        Optional<User> optionalUser = Optional.of(user);

        Mockito.when(userRepository.findById(id)).thenReturn(optionalUser);

        User myUser = userService.findUserByid(1);

        Assert.assertEquals(user, myUser);

    }

    
    @Test
    void testFindUserByidUserNotFound() throws Exception {
        int id = 30;

        Exception exception = assertThrows(ApiException.class, () -> {

            userService.findUserByid(id);

        });
        Assert.assertEquals("User not found with id :"+ id, exception.getMessage());

    }

    
    @Test
    void testFindUsersPage() throws ApiException {
        int page = 1;
        int limit = 2;

        User user1 = new User(1, "alis", "alis@email", "/myimage");
        User user2 = new User(2, "alkkis", "alkkis@email", "/myimage");

        List<User> userList = new ArrayList();

        userList.add(user1);
        userList.add(user2);
        Page<User> expectedPage = new PageImpl(userList);

        Pageable expectedPagable = PageRequest.of(0, 2);

        Mockito.when(userRepository.findAll(expectedPagable)).thenReturn(expectedPage);

        Page<User> actualUserPage = userService.findUsersPage(page, limit);

        Assert.assertEquals(expectedPage, actualUserPage);
    }

    
    @Test
    void testFindUsersPageWithZeroLimit() {
        int page = 1;
        int limit = 0;

        Exception exception = assertThrows(ApiException.class, () -> {

            userService.findUsersPage(page, limit);

        });
        Assert.assertEquals("limit and page must be greater than zero", exception.getMessage());
    }

}
