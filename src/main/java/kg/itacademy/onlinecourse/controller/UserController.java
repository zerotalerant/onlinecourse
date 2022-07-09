package kg.itacademy.onlinecourse.controller;

import kg.itacademy.onlinecourse.exceptions.UserNotFoundException;
import kg.itacademy.onlinecourse.model.UserAuthModel;
import kg.itacademy.onlinecourse.model.UserModel;
import kg.itacademy.onlinecourse.repository.UserRepository;
import kg.itacademy.onlinecourse.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api/users")
@Slf4j
public class UserController {

    private final UserService userService;

    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserController ( UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder )
    {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(path = "/get/{userId}")
    public ResponseEntity<UserModel> getUserById ( @PathVariable("userId") Long userId )
    {
        try
        {
            return ResponseEntity.ok ( userService.getById ( userId ) );
        } catch (UserNotFoundException ex)
        {
            log.error ( ex.getMessage (), ex );
            return ResponseEntity
                    .status ( HttpStatus.INTERNAL_SERVER_ERROR )
                    .body ( null );
        }
    }

    //Удалить пользователя по id
    @GetMapping(path = "/delete/{userId}")
    public ResponseEntity<Boolean> deleteUserById ( @PathVariable("userId") Long userId )
    {
        try
        {
            return ResponseEntity.ok ( userService.deleteById ( userId ) );
        } catch (UserNotFoundException ex)
        {
            log.error ( ex.getMessage (), ex );
            return ResponseEntity
                    .status ( HttpStatus.INTERNAL_SERVER_ERROR )
                    .body ( null );
        }
    }

    @PostMapping(path = "/sign-in")
    public String getAuthToken ( @Valid @RequestBody UserAuthModel userAuthDto )
    {
        return userService.getToken ( userAuthDto );
    }

    @PostMapping("/sign-up")
    public String register ( @RequestBody UserModel userModel )
    {
        return userService.createUser ( userModel );
    }


    @PutMapping(path = "/update")
    public ResponseEntity<Boolean> updateUser ( @RequestBody UserModel userModel )
    {
        try
        {
            userService.update ( userModel );
            return ResponseEntity.ok ( true );
        } catch (UserNotFoundException ex)
        {

            log.error ( ex.getMessage (), ex );
            return ResponseEntity
                    .status ( HttpStatus.INTERNAL_SERVER_ERROR )
                    .body ( null );
        }
    }


    @GetMapping(path = "/get/all")
    public List<UserModel> getAll ()
    {
        return userService.getAllUsers ();
    }


    @PostMapping(path = "/login")
    public String userLogin ( @ModelAttribute(name = "login") UserModel userModel )
    {
        userService.userLogin ( userModel );
        return "Successfully logged into the system";
    }
}
