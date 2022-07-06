package kg.itacademy.onlinecourse.service;

import kg.itacademy.onlinecourse.model.UserAuthModel;
import kg.itacademy.onlinecourse.model.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserModel createNewUser ( UserModel userModel );

    boolean update ( UserModel userModel );

    UserModel userLogin ( UserModel userModel );

    boolean deleteById ( Long id );

    UserModel getById ( Long id );

    List<UserModel> getAllByUsers ( String userName );

    String getToken ( UserAuthModel userAuthDto );

    String createUser ( UserModel userAuthDto );
}
