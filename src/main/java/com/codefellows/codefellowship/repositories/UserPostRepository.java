package com.codefellows.codefellowship.repositories;

import com.codefellows.codefellowship.models.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPostRepository extends JpaRepository<UserPost, Long> {

}
