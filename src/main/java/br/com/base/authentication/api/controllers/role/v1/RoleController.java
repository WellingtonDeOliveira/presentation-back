package br.com.base.authentication.api.controllers.role.v1;

import br.com.base.authentication.api.controllers.role.v1.annotations.GetAllRolesEndpoint;
import br.com.base.shared.annotations.web.ApiController;
import br.com.base.shared.annotations.web.OpenApiController;
import br.com.base.shared.models.enums.RoleType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@OpenApiController(name = "Roles Management")
@ApiController(path = "/v1/roles")
public class RoleController {

    @GetAllRolesEndpoint
    public ResponseEntity<List<RoleType>> getAllRoles() {
        return new ResponseEntity<>(List.of(RoleType.values()), HttpStatus.OK);
    }
}
