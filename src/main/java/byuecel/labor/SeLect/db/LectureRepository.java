package byuecel.labor.SeLect.db;

import byuecel.labor.SeLect.lecture.Lecture;
import org.springframework.data.repository.CrudRepository;

public interface LectureRepository extends CrudRepository<Lecture, Integer> {
}
