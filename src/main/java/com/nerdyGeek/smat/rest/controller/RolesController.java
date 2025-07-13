package com.nerdyGeek.smat.rest.controller;

import com.nerdyGeek.smat.dto.APIResponseDTO;
import com.nerdyGeek.smat.dto.RolesDTO;
import com.nerdyGeek.smat.entities.RolesEntity;
import com.nerdyGeek.smat.sql.repositories.RolesRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/roles")
public class RolesController {

    private RolesRepository rolesRepository;

    RolesController(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @GetMapping
    public APIResponseDTO<RolesDTO> getRoles() {
        APIResponseDTO apiResponseDTO = new APIResponseDTO();
        List<RolesEntity> roles = rolesRepository.findAll();
        apiResponseDTO.setBody(roles);
        return apiResponseDTO;
    }


}
