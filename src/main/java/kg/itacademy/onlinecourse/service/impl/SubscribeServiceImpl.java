package kg.itacademy.onlinecourse.service.impl;

import kg.itacademy.onlinecourse.entity.SubscribeEntity;
import kg.itacademy.onlinecourse.exceptions.FieldCantBeNullException;
import kg.itacademy.onlinecourse.mapper.SubscribeMapper;
import kg.itacademy.onlinecourse.model.SubscribeModel;
import kg.itacademy.onlinecourse.repository.SubscribeRepository;
import kg.itacademy.onlinecourse.service.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SubscribeServiceImpl implements SubscribeService {

    @Autowired
    private SubscribeRepository subscribeRepository;


    @Override
    public SubscribeModel create ( SubscribeModel subscribeModel )
    {
        if ( subscribeModel == null )
        {
            throw new FieldCantBeNullException ( "Field is null, check one more time" );
        }
        SubscribeEntity subscribeEntity = SubscribeMapper.INSTANCE.toEntity ( subscribeModel );
        subscribeEntity = subscribeRepository.save ( subscribeEntity );
        return SubscribeMapper.INSTANCE.toModel ( subscribeEntity );
    }

    @Override
    public boolean deleteById ( Long id )
    {
        subscribeRepository.deleteById ( id );
        return true;
    }

    @Override
    public List<SubscribeModel> getAll ()
    {
        List<SubscribeEntity> subscribeEntityList = subscribeRepository.findAll ();
        return SubscribeMapper.INSTANCE.toListModel ( subscribeEntityList );
    }
}
