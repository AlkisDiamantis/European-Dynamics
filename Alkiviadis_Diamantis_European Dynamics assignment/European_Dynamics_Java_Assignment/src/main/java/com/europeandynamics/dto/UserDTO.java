
package com.europeandynamics.dto;

import com.europeandynamics.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@JsonPropertyOrder({"items","total"})
public class UserDTO {
    
    @JsonProperty("items")
    private List<User> users;
    
    private long  total;
    
}
