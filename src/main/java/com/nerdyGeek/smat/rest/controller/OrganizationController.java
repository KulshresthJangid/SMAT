package com.nerdyGeek.smat.rest.controller;


import com.nerdyGeek.smat.dto.APIResponseDTO;
import com.nerdyGeek.smat.dto.OrganizationDTO;
import com.nerdyGeek.smat.entities.OrganizationsEntity;
import com.nerdyGeek.smat.services.DatabaseService;
import org.aspectj.weaver.ast.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/organization")
public class OrganizationController {

    private static final Logger logger = LoggerFactory.getLogger(OrganizationController.class);
    private final DatabaseService databaseService;

    OrganizationController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @GetMapping("/{id}")
    public APIResponseDTO<OrganizationDTO> getOrganizationId(@PathVariable(value = "id") Long orgId) {
        APIResponseDTO apiResponseDTO = new APIResponseDTO();
        try {
            Optional<OrganizationsEntity> organizationsEntity = databaseService.findById(orgId);
            if (organizationsEntity.isEmpty()) {
                apiResponseDTO.setHttpStatus(HttpStatus.NOT_FOUND);
                apiResponseDTO.setMessage("No organization found with provided id!");
                apiResponseDTO.setBody(null);
                return apiResponseDTO;
            }
            OrganizationDTO organizationDTO = new OrganizationDTO(organizationsEntity.get());
            apiResponseDTO.setHttpStatus(HttpStatus.OK);
            apiResponseDTO.setMessage("Organization Fetched Successfully!");
            apiResponseDTO.setBody(organizationDTO);
            return apiResponseDTO;
        } catch (Exception error) {
            logger.error("Error while fetching organization by id {id}", orgId);
            apiResponseDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            apiResponseDTO.setMessage("Error while fetching organization by id");
            apiResponseDTO.setBody(error);
            return apiResponseDTO;
        }
    }

    @PostMapping
    public APIResponseDTO<OrganizationDTO> createOrganization(@RequestBody OrganizationDTO organizationDTO) {
        APIResponseDTO apiResponseDTO = new APIResponseDTO();
        Optional<OrganizationsEntity> org = databaseService.findByEmailAndPhoneNumber(organizationDTO.getEmail(), organizationDTO.getPhoneNumber());
        try {
            if (org.isPresent()) {
                apiResponseDTO.setHttpStatus(HttpStatus.BAD_REQUEST);
                apiResponseDTO.setMessage("Organization Already Registered!!");
                apiResponseDTO.setBody(null);
                return apiResponseDTO;
            }

            OrganizationsEntity organizationsEntity = new OrganizationsEntity(organizationDTO);
            organizationsEntity = databaseService.save(organizationsEntity);
            apiResponseDTO.setHttpStatus(HttpStatus.CREATED);
            apiResponseDTO.setBody(new OrganizationDTO(organizationsEntity));
            apiResponseDTO.setMessage("Organization Created Successfully!");
            return apiResponseDTO;
        } catch (Exception e) {
            logger.error("Error while creating organization with email {email} and phone number {phoneNumber}", organizationDTO.getEmail(), organizationDTO.getPhoneNumber());
            apiResponseDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            apiResponseDTO.setMessage("Error while creating organization!!");
            apiResponseDTO.setBody(e);
            return apiResponseDTO;
        }


    }

}
