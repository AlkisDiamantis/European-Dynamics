
package com.europeandynamics.services;

import com.europeandynamics.exceptions.ApiException;
import com.europeandynamics.models.Post;

public interface PostService {
    
    
    
    public Post getUserPost(int postid,int userId)  throws ApiException;
    
    
}
