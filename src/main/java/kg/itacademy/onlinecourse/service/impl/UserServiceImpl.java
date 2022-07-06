package kg.itacademy.onlinecourse.service.impl;

import kg.itacademy.onlinecourse.model.UserAuthModel;
import kg.itacademy.onlinecourse.model.UserModel;
import kg.itacademy.onlinecourse.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

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
    public List<UserModel> getAllByUsers ( String userName )
    {
        return null;
    }

    @Override
    public String getToken ( UserAuthModel userAuthDto )
    {
        return null;
    }

    @Override
    public String createUser ( UserModel userAuthDto )
    {
        return null;
    }
}
