package kg.itacademy.onlinecourse.controller;

import kg.itacademy.onlinecourse.entity.RoleEntity;
import kg.itacademy.onlinecourse.model.RoleModel;
import kg.itacademy.onlinecourse.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api/roles")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleController {

    final RoleRepository roleRepository;


    @PostMapping(path = "/create")
    public String createRole ( @RequestBody RoleModel roleModel )
    {
        RoleEntity role = new RoleEntity ();
        role.setRoleName ( roleModel.getRoleName () );
        return roleRepository.save ( role ).getRoleName ();
    }
}
