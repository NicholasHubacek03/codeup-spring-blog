package com.codeup.codeupspringblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Post extends JpaRepository<Post, Long> {
    Post findByName(String name);
}
