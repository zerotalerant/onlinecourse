package kg.itacademy.onlinecourse.service.impl;

import kg.itacademy.onlinecourse.entity.SubscribeEntity;
import kg.itacademy.onlinecourse.exceptions.FieldCantBeNullException;
import kg.itacademy.onlinecourse.exceptions.SubscribeNotFoundException;
import kg.itacademy.onlinecourse.mapper.SubscribeMapper;
import kg.itacademy.onlinecourse.model.SubscribeModel;
import kg.itacademy.onlinecourse.repository.SubscribeRepository;
import kg.itacademy.onlinecourse.service.SubscribeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SubscribeServiceImpl implements SubscribeService {

    private final SubscribeRepository subscribeRepository;

    public SubscribeServiceImpl ( SubscribeRepository subscribeRepository )
    {
        this.subscribeRepository = subscribeRepository;
    }


    @Override
    public SubscribeModel create ( SubscribeModel subscribeModel )
    {
        if ( subscribeModel == null )
        {
            throw new FieldCantBeNullException ( "Field is null, check one more time" );
        }
        SubscribeEntity subscribeEntity = SubscribeMapper.INSTANCE.toEntity ( subscribeModel );
        subscribeEntity.setExpireDate ( LocalDate.now ().plusMonths ( 1 ) );
        subscribeEntity = subscribeRepository.save ( subscribeEntity );
        return SubscribeMapper.INSTANCE.toModel ( subscribeEntity );
    }

    @Override
    public String deleteById ( Long id )
    {
        subscribeRepository.deleteById ( id );
        return "Subscriber successfully deleted!";
    }

    @Override
    public List<SubscribeModel> getAll ()
    {
        List<SubscribeEntity> subscribeEntityList = subscribeRepository.findAll ();
        return SubscribeMapper.INSTANCE.toListModel ( subscribeEntityList );
    }

    @Override
    public SubscribeModel update ( SubscribeModel subscribeModel )
    {
        SubscribeEntity existSubscribeEntity = subscribeRepository.getById ( subscribeModel.getId () );
        if ( existSubscribeEntity == null )
        {
            throw new SubscribeNotFoundException ( "Subscribe not found by id: " + subscribeModel.getId () );
        }

        SubscribeEntity existSubscribe = subscribeRepository.findById ( subscribeModel.getId () )
                .orElseThrow ( () -> new SubscribeNotFoundException ( "Subscribe not found by id: " + subscribeModel.getId () ) );

        existSubscribe.setUserName ( subscribeModel.getUserName () );
        existSubscribe.setExpireDate ( LocalDate.now ().plusMonths ( 1 ) );

        existSubscribe = subscribeRepository.save ( existSubscribe );

        return SubscribeMapper.INSTANCE.toModel ( existSubscribe );
    }
}
