package kg.itacademy.onlinecourse.service;

import kg.itacademy.onlinecourse.model.SubscribeModel;

import java.util.List;

public interface SubscribeService {

    SubscribeModel create ( SubscribeModel SubscribeModel );

    boolean deleteById ( Long id );

    List<SubscribeModel> getAll ();


}
