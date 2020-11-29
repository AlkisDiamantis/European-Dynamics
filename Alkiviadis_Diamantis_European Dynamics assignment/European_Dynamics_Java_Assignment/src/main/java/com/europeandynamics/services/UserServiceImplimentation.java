package com.europeandynamics.services;

import com.europeandynamics.exceptions.ApiException;
import com.europeandynamics.models.User;
import com.europeandynamics.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplimentation implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Page<User> findUsersPage(int page, int limit) throws ApiException{
        
        if(limit<1 || page<1 ){
        throw new ApiException("limit and page must be greater than zero"); 
        }

        
        Pageable pageable = PageRequest.of(page - 1, limit);
        
        
        return userRepository.findAll(pageable);
        
    }
/**
 * This method will call the user repository to find a user by his id
 * it will return  a User Object if found or else it will throw an ApiException
 * @param userid
 * @return User
 * @throws ApiException 
 */
    @Override
    public User findUserByid(int userid) throws ApiException{
      
        
        return userRepository.findById(userid).orElseThrow(() -> new ApiException("User not found with id :" + userid));
    }

}
