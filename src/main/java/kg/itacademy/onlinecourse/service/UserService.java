package kg.itacademy.onlinecourse.service;

import kg.itacademy.onlinecourse.model.UserAuthModel;
import kg.itacademy.onlinecourse.model.UserModel;

import java.util.List;

public interface UserService {
    String getToken ( UserAuthModel userAuthDto );

    String createUser ( UserModel userAuthDto );

    UserModel update ( UserModel userModel );

    UserModel userLogin ( UserModel userModel );

    String deleteById ( Long id );

    UserModel getById ( Long id );

    List<UserModel> getAllUsers ();
}
