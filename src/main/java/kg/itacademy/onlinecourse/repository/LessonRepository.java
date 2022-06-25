package kg.itacademy.onlinecourse.repository;

import kg.itacademy.onlinecourse.entity.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, Long> {

}
