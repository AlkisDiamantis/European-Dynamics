
package com.europeandynamics.services;

import com.europeandynamics.exceptions.ApiException;
import com.europeandynamics.models.User;
import org.springframework.data.domain.Page;

public interface UserService {
    
    
    Page<User> findUsersPage(int page,int limit) throws ApiException;
    
   
    User findUserByid(int userid) throws ApiException;
    
    
}
