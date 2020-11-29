package com.europeandynamics.controllers;

import com.europeandynamics.exceptions.ApiException;
import com.europeandynamics.dto.UserDTO;
import com.europeandynamics.models.User;
import com.europeandynamics.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    
    @Autowired
    UserService userService;



    @GetMapping("/users")
    public UserDTO usersPaging(@RequestParam int page, @RequestParam int limit) throws ApiException{

        Page<User> usersPage = userService.findUsersPage(page, limit);
        
        List<User> users = usersPage.getContent();
        
        long total = usersPage.getTotalElements();

        UserDTO usersDTO = new UserDTO(users, total);

        return usersDTO;
        
    }

    

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("userId") int userId) throws ApiException {

        
        return userService.findUserByid(userId);
    }
    
    
}
