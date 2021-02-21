package com.edu.lucas.falacz.service;

import com.edu.lucas.falacz.model.Post;
import com.edu.lucas.falacz.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    public Page<Post> findPage(Pageable page){
        return postRepository.findAll(page);
    }
    public Post save(Post post){
        return postRepository.save(post);
    }
}
