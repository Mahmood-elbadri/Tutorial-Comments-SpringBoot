package com.in28minutes.repository;

import com.in28minutes.entity.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Transactional
    void deleteByTutorial_Id(int id);

    List<Comment> findByTutorialId(int id);
}
