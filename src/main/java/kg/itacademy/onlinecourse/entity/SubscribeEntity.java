package kg.itacademy.onlinecourse.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "subscribers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubscribeEntity extends BaseEntity {
    @Column(name = "user_name", nullable = false, unique = true)
    String userName;

    @Column
    LocalDate expireDate;

    @Column(name = "is_active")
    Boolean isActive;

    //@Job
}
