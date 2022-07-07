package kg.itacademy.onlinecourse.mapper;

import kg.itacademy.onlinecourse.entity.CourseEntity;
import kg.itacademy.onlinecourse.model.CourseModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper ( CourseMapper.class );

    CourseModel toModel ( CourseEntity courseEntity );

    CourseEntity toEntity ( CourseModel courseModel );

    List<CourseModel> toListModel ( List<CourseEntity> courses );

}
