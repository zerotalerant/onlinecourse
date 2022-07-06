package kg.itacademy.onlinecourse.service;

import kg.itacademy.onlinecourse.model.SubscribeModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubscribeService {

    SubscribeModel create ( SubscribeModel SubscribeModel );

    boolean deleteById ( Long id );

    List<SubscribeModel> getAll ();


}
