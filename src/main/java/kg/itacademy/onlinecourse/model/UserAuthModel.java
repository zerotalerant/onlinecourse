package kg.itacademy.onlinecourse.model;

import javax.validation.constraints.NotBlank;

public class UserAuthModel {

    @NotBlank(message = "Login can't be blank")
    String login;

    @NotBlank(message = "Password can't be blank")
    String password;
}
