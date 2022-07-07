package kg.itacademy.onlinecourse.mapper;


import kg.itacademy.onlinecourse.entity.LessonEntity;
import kg.itacademy.onlinecourse.model.LessonModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LessonMapper {

    LessonMapper INSTANCE = Mappers.getMapper ( LessonMapper.class );

    LessonModel toModel ( LessonEntity lessonEntity );

    LessonEntity toEntity ( LessonModel lessonModel );

    List<LessonModel> toListModel ( List<LessonEntity> lessons );
}
