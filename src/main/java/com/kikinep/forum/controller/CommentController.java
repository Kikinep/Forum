package com.kikinep.forum.controller;

import com.kikinep.forum.domain.comment.*;
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
@RequestMapping("/comments")
@SecurityRequirement(name = "bearer-key")
public class CommentController {
    @Autowired
    private CommentRepository repository;

    @Autowired
    private CommentRegistrationService service;

    @PostMapping
    @Transactional
    public ResponseEntity<CommentResponseData> registerComment(@RequestBody @Valid CommentRegistrationData data, UriComponentsBuilder uriComponentsBuilder) {
        CommentResponseData commentResponseData = service.register(data);
        URI url = uriComponentsBuilder.path("/comments/{id}").buildAndExpand(commentResponseData.id()).toUri();
        return ResponseEntity.created(url).body(commentResponseData);
    }

    @GetMapping
    public ResponseEntity<Page<CommentListData>> getCommentList(@PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(repository.findAll(pageable).map(CommentListData::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<CommentResponseData> updateComment(@RequestBody @Valid CommentUpdateData data) {
        Comment comment = repository.getReferenceById(data.id());
        comment.updateComment(data);
        return ResponseEntity.ok(new CommentResponseData(comment));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Comment> deleteComment(@PathVariable Long id) {
        boolean commentExists = repository.existsById(id);
        if (commentExists) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Unable to find Comment with id provided");
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseData> getComment(@PathVariable Long id) {
        Comment comment = repository.getReferenceById(id);
        return ResponseEntity.ok(new CommentResponseData(comment));
    }
}
