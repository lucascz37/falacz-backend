package com.edu.lucas.falacz.repository;

import com.edu.lucas.falacz.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
