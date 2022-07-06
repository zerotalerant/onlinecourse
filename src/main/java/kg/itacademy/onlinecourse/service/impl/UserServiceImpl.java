package kg.itacademy.onlinecourse.service.impl;

import kg.itacademy.onlinecourse.entity.UserEntity;
import kg.itacademy.onlinecourse.entity.UserRoleEntity;
import kg.itacademy.onlinecourse.exceptions.UserSignInException;
import kg.itacademy.onlinecourse.model.UserAuthModel;
import kg.itacademy.onlinecourse.model.UserModel;
import kg.itacademy.onlinecourse.repository.RoleRepository;
import kg.itacademy.onlinecourse.repository.UserRepository;
import kg.itacademy.onlinecourse.repository.UserRoleRepository;
import kg.itacademy.onlinecourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Base64;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserModel createNewUser ( UserModel userModel )
    {
        return null;
    }

    @Override
    public boolean update ( UserModel userModel )
    {
        return false;
    }

    @Override
    public UserModel userLogin ( UserModel userModel )
    {
        return null;
    }

    @Override
    public boolean deleteById ( Long id )
    {
        return false;
    }

    @Override
    public UserModel getById ( Long id )
    {
        return null;
    }

    @Override
    public String getToken ( UserAuthModel userAuthDto )
    {
        UserEntity user = userRepository
                .findByLogin ( userAuthDto.getLogin () );
        if ( user == null )
        {
            throw new UsernameNotFoundException ( "Username not found" );
        }
        boolean isMatches = passwordEncoder.matches ( userAuthDto.getPassword (), user.getPassword () );
        if ( isMatches )
        {
            return "Basic " + new String ( Base64.getEncoder ()
                    .encode ( ( user.getLogin () + ":" + userAuthDto.getPassword () ).getBytes () ) );
        } else
        {
            throw new UserSignInException ( "Неправильный логин или пароль!", HttpStatus.UNAUTHORIZED );
        }
    }

    @Override
    public String createUser ( UserModel userModel )
    {
        UserEntity user = new UserEntity ();

        user.setLogin ( userModel.getLogin () );
        user.setEmail ( userModel.getEmail () );

        user.setPassword ( passwordEncoder.encode ( userModel.getPassword () ) );


        UserRoleEntity userRole = new UserRoleEntity ();
        if ( userModel.getLogin ().contains ( "admin" ) )
        {
            userRole.setRole ( roleRepository.findFirstByNameRole ( "Teacher" ) );
        } else
        {
            userRole.setRole ( roleRepository.findFirstByNameRole ( "User" ) );
        }
        userRole.setUser ( userRepository.save ( user ) );
        userRoleRepository.save ( userRole );
        return "created";
    }

    @Override
    public List<UserModel> getAllUsers ()
    {
        return null;
    }
}
