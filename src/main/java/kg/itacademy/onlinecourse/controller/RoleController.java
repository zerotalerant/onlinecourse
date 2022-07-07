package kg.itacademy.onlinecourse.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kg.itacademy.onlinecourse.entity.RoleEntity;
import kg.itacademy.onlinecourse.model.RoleModel;
import kg.itacademy.onlinecourse.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping(path = "/create")
    public String createRole ( @RequestBody RoleModel roleModel )
    {
        RoleEntity role = new RoleEntity ();
        role.setRoleName ( roleModel.getName () );
        return roleRepository.save ( role ).getRoleName ();
    }
}
