package kg.itacademy.onlinecourse.controller;

import kg.itacademy.onlinecourse.exceptions.SubscribeNotFoundException;
import kg.itacademy.onlinecourse.exceptions.SubscriberDoesNotExistException;
import kg.itacademy.onlinecourse.model.SubscribeModel;
import kg.itacademy.onlinecourse.repository.SubscribeRepository;
import kg.itacademy.onlinecourse.service.SubscribeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api/subscribes")
@Slf4j
public class SubscribeController {

    @Autowired
    private SubscribeService subscribeService;

    @Autowired
    private SubscribeRepository subscribeRepository;

    @PostMapping(path = "/add")
    public ResponseEntity<SubscribeModel> addSubscriber ( @RequestBody SubscribeModel subscribeModel )
    {
        SubscribeModel subscriber = subscribeService.create ( subscribeModel );
        if ( subscriber.getId () != null )
        {
            return ResponseEntity.status ( HttpStatus.CREATED ).body ( subscriber );
        } else
        {
            return ResponseEntity.status ( HttpStatus.INTERNAL_SERVER_ERROR ).body ( null );
        }
    }

    @DeleteMapping(path = "/delete/{subscribeId}")
    public ResponseEntity<Boolean> deleteSubscriberById ( @PathVariable("subscribeId") Long id )
    {
        try
        {
            return ResponseEntity.ok ( subscribeService.deleteById ( id ) );
        } catch (SubscriberDoesNotExistException e)
        {
            log.error ( e.getMessage (), e );
            return ResponseEntity.status ( HttpStatus.INTERNAL_SERVER_ERROR ).body ( null );
        }
    }

    @GetMapping(path = "/get/all")
    public ResponseEntity<List<SubscribeModel>> getAll ()
    {
        try
        {
            return ResponseEntity.ok ( subscribeService.getAll () );
        } catch (SubscribeNotFoundException e)
        {
            log.error ( e.getMessage (), e );
            return ResponseEntity.status ( HttpStatus.INTERNAL_SERVER_ERROR ).body ( null );
        }

    }
}
