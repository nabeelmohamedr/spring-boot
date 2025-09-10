package com.optimus.omnitrix.excptiom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class omn_excp extends RuntimeException{
    public omn_excp(String message) {
        super(message  );
    }
}
