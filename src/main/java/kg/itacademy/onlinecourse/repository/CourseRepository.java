package kg.itacademy.onlinecourse.repository;

import kg.itacademy.onlinecourse.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

}
