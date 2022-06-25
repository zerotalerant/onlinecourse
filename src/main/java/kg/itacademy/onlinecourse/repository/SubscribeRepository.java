package kg.itacademy.onlinecourse.repository;

import kg.itacademy.onlinecourse.entity.SubscribeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscribeRepository extends JpaRepository<SubscribeEntity, Long> {
}
