
package com.europeandynamics.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CustomExceptionDetails {
    
    private String timeStamp;
    private String message;

    
}
