package kg.itacademy.onlinecourse.mapper;

import kg.itacademy.onlinecourse.entity.UserEntity;
import kg.itacademy.onlinecourse.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper ( UserMapper.class );

    UserModel toModel ( UserEntity userEntity );

    UserEntity toEntity ( UserModel userModel );

    List<UserModel> toListModel ( List<UserEntity> users );
}
