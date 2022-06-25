package kg.itacademy.onlinecourse.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel {
    private Long id;
    private String login;
    private String password;
    private String email;
    private Boolean isActive;
}
