package kg.itacademy.onlinecourse.mapper;

import kg.itacademy.onlinecourse.entity.SubscribeEntity;
import kg.itacademy.onlinecourse.model.SubscribeModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubscribeMapper {
    SubscribeMapper INSTANCE = Mappers.getMapper ( SubscribeMapper.class );

    SubscribeModel toModel ( SubscribeEntity subscribeEntity );

    SubscribeEntity toEntity ( SubscribeModel subscribeModel );

    List<SubscribeModel> toListModel ( List<SubscribeEntity> subscribes );
}
