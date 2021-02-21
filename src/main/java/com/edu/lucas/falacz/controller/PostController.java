package com.edu.lucas.falacz.controller;

import com.edu.lucas.falacz.dto.impl.PostCreateDTO;
import com.edu.lucas.falacz.dto.impl.PostGetDTO;
import com.edu.lucas.falacz.dto.impl.PostResponseDTO;
import com.edu.lucas.falacz.model.Post;
import com.edu.lucas.falacz.service.PostService;
import com.edu.lucas.falacz.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/post")
@AllArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@Valid @RequestBody PostCreateDTO body, @RequestAttribute("id") long id){
        Post post = new Post();
        post.setText(body.getText());
        post.setUser(userService.findById(id));
        Post savedPost = postService.save(post);

        return new ResponseEntity<>(new PostResponseDTO(savedPost.getId(), savedPost.getText()), HttpStatus.ACCEPTED);

    }

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getPosts(@RequestBody PostGetDTO body){
        Page<Post> page = postService.findPage(PageRequest.of(body.getPage(), 10));
        List<PostResponseDTO> list = new ArrayList<>();
        page.forEach(e ->
                list.add(new PostResponseDTO(e.getId(), e.getText()))
                );

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
