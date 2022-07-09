package kg.itacademy.onlinecourse.service.impl;

import kg.itacademy.onlinecourse.entity.UserEntity;
import kg.itacademy.onlinecourse.entity.UserRoleEntity;
import kg.itacademy.onlinecourse.exceptions.FieldCantBeNullException;
import kg.itacademy.onlinecourse.exceptions.UserNotFoundException;
import kg.itacademy.onlinecourse.exceptions.UserSignInException;
import kg.itacademy.onlinecourse.mapper.UserMapper;
import kg.itacademy.onlinecourse.model.UserAuthModel;
import kg.itacademy.onlinecourse.model.UserModel;
import kg.itacademy.onlinecourse.repository.RoleRepository;
import kg.itacademy.onlinecourse.repository.UserRepository;
import kg.itacademy.onlinecourse.repository.UserRoleRepository;
import kg.itacademy.onlinecourse.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl ( UserRepository userRepository, UserRoleRepository userRoleRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder )
    {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserModel update ( UserModel userModel )
    {
        if ( userModel == null )
        {
            throw new FieldCantBeNullException ( "Field is null, check one more time!" );
        }
        UserEntity existUser = userRepository.findById ( userModel.getId () )
                .orElseThrow ( () -> new UserNotFoundException ( "User not found by id: " + userModel.getId () ) );

        return UserMapper.INSTANCE.toModel ( existUser );
    }

    @Override
    public UserModel userLogin ( UserModel userModel )
    {

        return userModel;
    }

    @Override
    public String deleteById ( Long id )
    {
        userRepository.deleteById ( id );
        return "User successfully deleted!";
    }

    @Override
    public UserModel getById ( Long id )
    {
        UserEntity existUser = userRepository.findById ( id )
                .orElseThrow ( () -> new UserNotFoundException ( "User not found by id: " + id ) );

        return UserMapper.INSTANCE.toModel ( existUser );
    }

    @Override
    public String getToken ( UserAuthModel userAuthDto )
    {
        UserEntity user = userRepository.findByLogin ( userAuthDto.getLogin () );
        if ( user == null )
        {
            throw new UserNotFoundException ( "User not found by login: " + userAuthDto.getLogin () );
        }
        boolean isMatch = passwordEncoder.matches ( userAuthDto.getPassword (), user.getPassword () );
        if ( isMatch )
        {
            return "Basic " + new String ( Base64.getEncoder ()
                    .encode ( ( user.getLogin () + ":" + userAuthDto.getPassword () ).getBytes () ) );
        } else
        {
            throw new UserSignInException ( "Неправильный логин или пароль!", HttpStatus.UNAUTHORIZED );
        }
    }

    @Override
    public String createUser ( UserModel userAuthDto )
    {
        UserEntity user = new UserEntity ();
        user.setLogin ( userAuthDto.getLogin () );
        user.setEmail ( userAuthDto.getEmail () );

        user.setPassword ( passwordEncoder.encode ( userAuthDto.getPassword () ) );
        user.setIsActive ( true );

        UserRoleEntity userRole = new UserRoleEntity ();
        if ( userAuthDto.getLogin ().contains ( "teacher" ) )
        {
            userRole.setRole ( roleRepository.findFirstByRoleName ( "Teacher" ) );
        } else
        {
            userRole.setRole ( roleRepository.findFirstByRoleName ( "Student" ) );
        }
        {
            userRole.setUser ( userRepository.save ( user ) );
            userRoleRepository.save ( userRole );
            return "User created";
        }
    }

    @Override
    public List<UserModel> getAllUsers ()
    {
        List<UserEntity> userEntityList = userRepository.findAll ();
        return UserMapper.INSTANCE.toListModel ( userEntityList );
    }
}
