package com.inven.controller;

import com.inven.service.CommentService;
import com.inven.service.dto.CommentDto;
import com.inven.service.dto.UserDto;
import com.inven.service.security.auth.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST API Controller
 */
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CommentApiController {

    private final CommentService commentService;

    /* CREATE */
    @PostMapping("/posts/{id}/comments")
    public ResponseEntity save(@PathVariable Long id, @RequestBody CommentDto.Request dto,
                               @LoginUser UserDto.Response userSessionDto) {
        return ResponseEntity.ok(commentService.save(id, userSessionDto.getNickname(), dto));
    }

    /* READ */
    @GetMapping("/posts/{id}/comments")
    public List<CommentDto.Response> read(@PathVariable Long id) {
        return commentService.findAll(id);
    }

    /* UPDATE */
    @PutMapping({"/posts/{id}/comments/{id}"})
    public ResponseEntity update(@PathVariable Long id, @RequestBody CommentDto.Request dto) {
        commentService.update(id, dto);
        return ResponseEntity.ok(id);
    }

    /* DELETE */
    @DeleteMapping("/posts/{id}/comments/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.ok(id);
    }
}
