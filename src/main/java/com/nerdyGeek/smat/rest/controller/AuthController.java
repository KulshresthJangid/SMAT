package com.nerdyGeek.smat.rest.controller;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nerdyGeek.smat.dto.APIResponseDTO;
import com.nerdyGeek.smat.dto.LoginResponseDTO;
import com.nerdyGeek.smat.dto.LoginUserDTO;
import com.nerdyGeek.smat.dto.RegisterUserDTO;
import com.nerdyGeek.smat.entities.UserEntity;
import com.nerdyGeek.smat.services.AuthenticationService;
import com.nerdyGeek.smat.services.JwtService;
import com.nerdyGeek.smat.services.DatabaseService;

@RequestMapping("/auth")
@RestController
public class AuthController {

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    private final DatabaseService databaseService;

    public AuthController(JwtService jwtService,
            AuthenticationService authenticationService,
            DatabaseService databaseService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.databaseService = databaseService;
    }

    @PostMapping("/signup")
    public ResponseEntity<APIResponseDTO<RegisterUserDTO>> register(
            @RequestBody RegisterUserDTO registerUserDTO) {
        UserEntity existingUser = databaseService.findByUsernameAndEmail(
                registerUserDTO.getUsername(), registerUserDTO.getEmail());
        if (Objects.nonNull(existingUser)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new APIResponseDTO(HttpStatus.BAD_REQUEST,
                            "User already exists with the same username and email!!",
                            null));
        }
        UserEntity registeredUser = authenticationService
                .signup(registerUserDTO);
        registerUserDTO.setPassword(null);
        return ResponseEntity.ok(new APIResponseDTO<RegisterUserDTO>(
                HttpStatus.CREATED, "User Created Successfully please login!!",
                registerUserDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponseDTO<LoginResponseDTO>> authenticate(
            @RequestBody LoginUserDTO loginUserDto) {
        UserEntity authenticatedUser = authenticationService
                .authenticate(loginUserDto);

        if (Objects.isNull(authenticatedUser)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new APIResponseDTO(HttpStatus.BAD_REQUEST,
                            "No User Found With Provided Email!!", null));
        }

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponseDTO loginResponse = new LoginResponseDTO();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        loginResponse.setEmail(authenticatedUser.getEmail());
        loginResponse.setUsername(authenticatedUser.getUsername());

        return ResponseEntity
                .ok(new APIResponseDTO<LoginResponseDTO>(HttpStatus.OK,
                        "User authenticated Successfully!", loginResponse));
    }
}
