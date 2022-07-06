package kg.itacademy.onlinecourse.service.impl;

import kg.itacademy.onlinecourse.entity.UserEntity;
import kg.itacademy.onlinecourse.entity.UserRoleEntity;
import kg.itacademy.onlinecourse.exceptions.FieldCantBeNullException;
import kg.itacademy.onlinecourse.exceptions.UserNotFoundException;
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

import java.security.InvalidParameterException;
import java.util.ArrayList;
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
        UserEntity user = new UserEntity ();
        //Маппинг user
        user.setLogin ( userModel.getLogin () );
        user.setEmail ( userModel.getEmail () );
        //
        user.setPassword ( passwordEncoder.encode ( userModel.getPassword () ) );
        user.setIsActive ( true );

        UserRoleEntity userRole = new UserRoleEntity ();
        if ( userModel.getLogin ().contains ( "Teacher" ) )
        {
            userRole.setRole ( roleRepository.findFirstByNameRole ( "Teacher" ) );
        } else
        {
            userRole.setRole ( roleRepository.findFirstByNameRole ( "Student" ) );
        }
        userRole.setUser ( userRepository.save ( user ) );
        userRoleRepository.save ( userRole );
        return userModel;
    }

    @Override
    public boolean update ( UserModel userModel )
    {
        if ( userModel == null )
        {
            throw new FieldCantBeNullException ( "Field is null" );
        } else if ( userModel.getLogin () == null || userModel.getLogin ().equals ( "" ) )
        {
            throw new InvalidParameterException ( "airport name can't be empty" );
        } else if ( userModel.getId () == null )
        {
            throw new InvalidParameterException ( "User id can't be null" );
        }
        UserEntity existUser = userRepository.getById ( userModel.getId () );
        if ( existUser == null )
        {
            throw new UserNotFoundException ( "airport not found by id " + userModel.getId () );
        }

        existUser.setLogin ( existUser.getLogin () );
        existUser.setPassword ( existUser.getPassword () );
        existUser.setEmail ( existUser.getEmail () );

        existUser = userRepository.save ( existUser );

        return existUser.getId () != null;
    }

    @Override
    public UserModel userLogin ( UserModel userModel )
    {
        return userModel;
    }

    @Override
    public boolean deleteById ( Long id )
    {
        if ( !userRepository.existsById ( id ) )
        {
            throw new UserNotFoundException ( "User not found by id: " + id );
        }
        userRepository.deleteById ( id );
        return true;
    }

    @Override
    public UserModel getById ( Long id )
    {
        if ( id == null )
        {
            throw new FieldCantBeNullException ( "Id is null" );
        }

        UserEntity entity = userRepository.getById ( id );

        if ( entity == null )
        {
            throw new UserNotFoundException ( "User not found by id: " + id );
        }

        UserModel userModel = new UserModel ();
        userModel.setLogin ( userModel.getLogin () );
        userModel.setPassword ( userModel.getPassword () );
        userModel.setEmail ( userModel.getEmail () );

        return userModel;
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
        List<UserEntity> userEntityList = userRepository.findAll ();

        List<UserModel> userModelList = new ArrayList<> ();

        for (UserEntity user : userEntityList)
        {
            UserModel userModel = new UserModel ();
            userModel.setLogin ( user.getLogin () );
            userModel.setPassword ( user.getPassword () );
            userModel.setEmail ( user.getEmail () );

            userModelList.add ( userModel );
        }

        return userModelList;
    }
}
