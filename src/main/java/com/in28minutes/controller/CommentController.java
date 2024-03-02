package com.in28minutes.controller;

import com.in28minutes.entity.Comment;
import com.in28minutes.entity.Tutorial;
import com.in28minutes.exception.ResourceNotFoundException;
import com.in28minutes.repository.CommentRepository;
import com.in28minutes.repository.TutorialRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentRepository commentRepository;
    private final TutorialRepository tutorialRepository;
    private List<Comment> comments = new ArrayList<>();

    public CommentController(CommentRepository commentRepository, TutorialRepository tutorialRepository) {
        this.commentRepository = commentRepository;
        this.tutorialRepository = tutorialRepository;
    }

    @GetMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<Object> getAllCommentsOfTutorial(@PathVariable(name = "tutorialId") int id) {
        tutorialRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("id %d is not available", id)));
        comments = commentRepository.findByTutorialId(id);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Object> getComment(@PathVariable int id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("id %d is not available", id)));
        return new ResponseEntity<Object>(comment, HttpStatus.OK);
    }

    @PostMapping("tutorials/{tutorialId}/comments")//to be shown in ChatGPT
    public ResponseEntity<Object> postComment(@PathVariable int tutorialId, @RequestBody Comment comment) {
        Tutorial tutorial = tutorialRepository.findById(tutorialId).
                orElseThrow(() -> new ResourceNotFoundException(String.format("id %d is not available", tutorialId)));
        comment.setTutorial(tutorial);
        comment = commentRepository.save(comment);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<Object> editComment(@PathVariable int id, @RequestBody Comment comment) {
        Comment exComment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("id %d is not available", id)));
        exComment.setContent(comment.getContent());
        exComment = commentRepository.save(exComment);
        return new ResponseEntity<>(exComment, HttpStatus.OK);
    }

    @DeleteMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<Object> deleteAllCommentsOfTutorial(@PathVariable int tutorialId) {
        commentRepository.deleteByTutorial_Id(tutorialId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }







































    /*@GetMapping("/tutorials/{id}/comments")
    public ResponseEntity<List<Comment>> getCommentsByTutorialId(@PathVariable int id) {
        boolean exist = commentRepository.existsById(id);
        if (!exist) {
            throw new ResourceNotFoundException("id: " + id);
        }
        List<Comment> comments = commentRepository.findByTutorialId(id);
        return new ResponseEntity<>(comments, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Object> getCommentById(@PathVariable int id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id: " + id));
        return new ResponseEntity<>(comment, HttpStatusCode.valueOf(200));
    }

    @PostMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<Object> createCommentForTutorial(@PathVariable ("tutorialId") int id,@RequestBody Comment comment){

        Tutorial b = tutorialRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("id: %d is not found" + id));
        comment.setTutorial(b);
        commentRepository.save(comment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/comments/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") int id, @RequestBody Comment commentRequest) {
        Comment comment = commentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format("id: %d is not found",id)));
        comment.setContent(comment.getContent());
        commentRepository.save(comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Object> deleteComment(@PathVariable int id){
        commentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(String.format("id %d is not found",id)));
        commentRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }*/
}
