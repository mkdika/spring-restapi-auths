package com.mkdika.springrestapiauths.basicauth.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicAuthController {

    @RequestMapping(value = "/echo/{message}", method = RequestMethod.GET)
    public ResponseEntity testEcho(@PathVariable String message) {
        return new ResponseEntity(message, HttpStatus.OK);
    }
}
