package com.kikinep.forum.controller;

import com.kikinep.forum.domain.post.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostRegistrationService service;

    @PostMapping
    @Transactional
    public ResponseEntity<PostResponseData> registerPost(@RequestBody @Valid PostRegistrationData data, UriComponentsBuilder uriComponentsBuilder) {
        PostResponseData postResponseData = service.register(data);
        URI url = uriComponentsBuilder.path("/posts/{id}").buildAndExpand(postResponseData.id()).toUri();
        return ResponseEntity.created(url).body(postResponseData);
    }
}
