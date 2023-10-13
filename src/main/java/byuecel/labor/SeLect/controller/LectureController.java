package byuecel.labor.SeLect.controller;

import byuecel.labor.SeLect.model.Lecture;
import byuecel.labor.SeLect.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class LectureController {

    @Autowired
    private LectureRepository repository;

    @GetMapping("/lecture")
    public ResponseEntity<Lecture> get(@RequestParam(value = "id") int id ){

        Optional<Lecture> lecture = repository.findById(id);
        if(lecture.isPresent())
            return new ResponseEntity<Lecture>(lecture.get(), HttpStatus.OK);

        return new ResponseEntity("No Lecture found for this id " + id, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/lecture/all")
    public ResponseEntity<Iterable<Lecture>> getAll(){
        Iterable<Lecture> lectures = repository.findAll();
        return new ResponseEntity<Iterable<Lecture>>(lectures, HttpStatus.OK);
    }

    @PostMapping("/lecture")
    public ResponseEntity<Lecture> create(@RequestBody Lecture lecture){
        // save lecture in db
        repository.save(lecture);
        return new ResponseEntity<Lecture>(lecture, HttpStatus.CREATED);
    }

    @PostMapping("/lecture/multiple")
    public ResponseEntity<Iterable<Lecture>> createMultiple(@RequestBody Iterable<Lecture> lectures){
        repository.saveAll(lectures);
        return new ResponseEntity<Iterable<Lecture>>(lectures, HttpStatus.OK);
    }

    @DeleteMapping("/lecture")
    public ResponseEntity delete(@RequestParam(value = "id") int id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return new ResponseEntity("Lecture deleted", HttpStatus.OK);
        }
        return new ResponseEntity("No Lecture with id " + id, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/lecture")
    public ResponseEntity<Lecture> update(@RequestBody Lecture lecture){
        if(repository.existsById(lecture.getId())){
            repository.save(lecture);
            return new ResponseEntity<Lecture>(lecture, HttpStatus.OK);
        } else {
            return new ResponseEntity("No Lecture to update found with id " + lecture.getId() + ".", HttpStatus.NOT_FOUND);
        }
    }
}