package com.zavrsnirad.CodeFlow.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class RedirectController {

    @Value("${frontend.link}")
    private String url;

    @GetMapping(value = "")
    public ResponseEntity<Void> redirect(){
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url)).build();
    }

}
