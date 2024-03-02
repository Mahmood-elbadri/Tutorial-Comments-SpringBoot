package com.in28minutes.repository;

import com.in28minutes.entity.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutorialRepository extends JpaRepository<Tutorial,Integer> {
    public List<Tutorial> findByPublished(boolean published);
    public List<Tutorial> findByTitle(String word);
    public List<Tutorial> findByTitleContaining(String word);
}
