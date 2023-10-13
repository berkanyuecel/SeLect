package byuecel.labor.SeLect.controller;

import byuecel.labor.SeLect.model.Professor;
import byuecel.labor.SeLect.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProfessorController {

    @Autowired
    private ProfessorRepository repository;

    @GetMapping("/professor")
    public ResponseEntity<Professor> get(@RequestParam(value = "id") int id){
        Optional<Professor> professor = repository.findById(id);
        if(professor.isPresent()){
            return new ResponseEntity<Professor>(professor.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity("No Professor with id " + id + " found.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/professor/all")
    public ResponseEntity<Iterable<Professor>> getAll(){
        Iterable<Professor> professors = repository.findAll();
        return new ResponseEntity<Iterable<Professor>>(professors, HttpStatus.OK);
    }

    @PostMapping("/professor")
    public ResponseEntity<Professor> create(@RequestBody Professor professor){
        repository.save(professor);
        return new ResponseEntity<Professor>(professor, HttpStatus.OK);
    }

    @PostMapping("/professor/multiple")
    public ResponseEntity<Iterable<Professor>> createMultiple(@RequestBody Iterable<Professor> professors){
        repository.saveAll(professors);
        return new ResponseEntity<Iterable<Professor>>(professors, HttpStatus.OK);
    }

    @DeleteMapping("/professor")
    public ResponseEntity delete(@RequestParam(value = "id") int id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return new ResponseEntity("Professor with id " + id + " deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity("No Professor with id " + id + " found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/professor")
    public ResponseEntity<Professor> update(@RequestBody Professor professor){
        if(repository.existsById(professor.getId())){
            repository.save(professor);
            return new ResponseEntity<Professor>(professor, HttpStatus.OK);
        } else {
            return new ResponseEntity("No Professor to update found with id " + professor.getId() + ".", HttpStatus.NOT_FOUND);
        }
    }
}
