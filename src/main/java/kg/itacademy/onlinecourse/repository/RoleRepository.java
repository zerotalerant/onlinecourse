package kg.itacademy.onlinecourse.repository;

import kg.itacademy.onlinecourse.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByRole ( String roleName );
}
