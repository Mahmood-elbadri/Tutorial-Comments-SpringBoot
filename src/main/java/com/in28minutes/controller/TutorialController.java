package com.in28minutes.controller;

import com.in28minutes.entity.Tutorial;
import com.in28minutes.exception.ResourceNotFoundException;
import com.in28minutes.repository.TutorialRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TutorialController {
    public static final String TUTORIALS = "/tutorials";
    TutorialRepository tutorialRepository;
    private List<Tutorial> tutorials;

    public TutorialController(TutorialRepository tutorialRepository) {
        this.tutorialRepository = tutorialRepository;
    }

    /**
     * Get By title
     *
     * @return an ambiguous call
     */
    /*@GetMapping("tutorials/{x}")
    public ResponseEntity<Object> getAllTutorialsByTitle(@PathVariable(required = true, name = "x") String titleName) {
        tutorials = tutorialRepository.findByTitle(titleName);
        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }*/
    @GetMapping(TUTORIALS)
    public ResponseEntity<Object> getAllTutorials() {
        tutorials = tutorialRepository.findAll();
        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }

    //ambiguous call
    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Object> getTutorialById(@PathVariable(name = "id") int id) {
        Tutorial tutorial = tutorialRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("id: %d is not found", id)));
        return new ResponseEntity<>(tutorial, HttpStatus.OK);
    }

    @PostMapping("/tutorials")
    public ResponseEntity<Object> createTutorial(@RequestBody Tutorial tutorial) {
        Tutorial tutorial1 = tutorialRepository.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), tutorial.isPublished()));
        return new ResponseEntity<>(tutorial1, HttpStatus.CREATED);
    }

    @PutMapping("/tutorials/{id}")
    public ResponseEntity<Object> editTutorial(@PathVariable(name = "id") int id, @RequestBody Tutorial tutorial) {
        Tutorial exTutorial = tutorialRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("id: %d is not found", id)));
        exTutorial.setTitle(tutorial.getTitle());
        exTutorial.setDescription(tutorial.getDescription());
        exTutorial.setPublished(tutorial.isPublished());
        exTutorial = tutorialRepository.save(exTutorial);
        return new ResponseEntity<>(exTutorial, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<Object> deleteAllTutorials(@PathVariable int id) {
        tutorialRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("id: %d is not found", id)));
        tutorialRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/tutorials/published")
    public ResponseEntity<Object> getAllPublishedTutorials() {
        tutorials = tutorialRepository.findByPublished(true);
        if (tutorials.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        }
    }































    /*@GetMapping("/tutorials")
    public ResponseEntity<List<
            Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
        List<Tutorial> tutorials = new ArrayList<Tutorial>();

        if (title == null)
            tutorialRepository.findAll().forEach(tutorials::add);
        else
            tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

        if (tutorials.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") int id) {
        Tutorial tutorial = tutorialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

        return new ResponseEntity<>(tutorial, HttpStatus.OK);
    }

    @PostMapping("/tutorials")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        Tutorial _tutorial = tutorialRepository.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), true));
        return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
    }

    @PutMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") int id, @RequestBody Tutorial tutorial) {
        Tutorial _tutorial = tutorialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

        _tutorial.setTitle(tutorial.getTitle());
        _tutorial.setDescription(tutorial.getDescription());
        _tutorial.setPublished(tutorial.isPublished());

        return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
    }

    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") int id) {
        tutorialRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        tutorialRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/tutorials/published")
    public ResponseEntity<List<Tutorial>> findByPublished() {
        List<Tutorial> tutorials = tutorialRepository.findByPublished(true);

        if (tutorials.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }*/
}