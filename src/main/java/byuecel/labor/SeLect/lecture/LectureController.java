package byuecel.labor.SeLect.lecture;

import byuecel.labor.SeLect.db.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LectureController {

    @Autowired
    private LectureRepository repository;

    @GetMapping("/lecture")
    public ResponseEntity<Lecture> get(@RequestParam(value = "id") int id ){

        Lecture lecture = new Lecture();
        lecture.setId(id);
        lecture.setName("Thermodynamik");
        lecture.setDescription("Die Wissenschaft der Temperaturen");
        lecture.setProfessor("Professor Dr Braun");
        lecture.setIsDone(true);

        return new ResponseEntity<Lecture>(lecture, HttpStatus.OK);
    }

    @PostMapping("lecture")
    public ResponseEntity<Lecture> create(@RequestBody Lecture lecture){
        // save lecture in db
        repository.save(lecture);
        return new ResponseEntity<Lecture>(lecture, HttpStatus.OK);
    }
}