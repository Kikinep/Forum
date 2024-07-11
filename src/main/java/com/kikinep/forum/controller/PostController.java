package com.kikinep.forum.controller;

import com.kikinep.forum.domain.post.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/posts")
@SecurityRequirement(name = "bearer-key")
public class PostController {
    @Autowired
    private PostRepository repository;

    @Autowired
    private PostRegistrationService service;

    @PostMapping
    @Transactional
    public ResponseEntity<PostResponseData> registerPost(@RequestBody @Valid PostRegistrationData data, UriComponentsBuilder uriComponentsBuilder) {
        PostResponseData postResponseData = service.register(data);
        URI url = uriComponentsBuilder.path("/posts/{id}").buildAndExpand(postResponseData.id()).toUri();
        return ResponseEntity.created(url).body(postResponseData);
    }

    @GetMapping
    public ResponseEntity<Page<PostListData>> getPostList(@PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(repository.findByStatus(Status.ACTIVE, pageable).map(PostListData::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PostResponseData> updatePost(@RequestBody @Valid PostUpdateData data) {
        Post post = repository.getReferenceById(data.id());
        post.updatePost(data);
        return ResponseEntity.ok(new PostResponseData(post));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Post> deletePost(@PathVariable Long id) {
        boolean postExists = repository.existsById(id);
        if (postExists) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Unable to find Post with id provided");
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<PostResponseData> getPost(@PathVariable Long id) {
        Post post = repository.getReferenceById(id);
        return ResponseEntity.ok(new PostResponseData(post));
    }
}
