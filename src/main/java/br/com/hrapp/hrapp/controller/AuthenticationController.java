package br.com.hrapp.hrapp.controller;

import br.com.hrapp.hrapp.DTO.AuthResponseDTO;
import br.com.hrapp.hrapp.DTO.AutheticationDTO;
import br.com.hrapp.hrapp.DTO.RegistroDTO;
import br.com.hrapp.hrapp.models.User;
import br.com.hrapp.hrapp.repository.UserRepository;
import br.com.hrapp.hrapp.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;

//LOGIN DOS USUÁRIOS CRIADOS
    @PostMapping("/login")
    @Operation(
            summary = ("Login"),
            description = ("Login de USER e ADM"),
            tags={"auth"}
    )
    public ResponseEntity<?> login(@RequestBody @Valid AutheticationDTO data){
        try{
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this. authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new AuthResponseDTO(token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed: " + e.getMessage());
        }
    }

    //CRIAÇÂO DE NOVO USER
    @PostMapping("/registro")
    @Operation(
            summary = ("Registro"),
            description = ("Registro de USER e ADM"),
            tags={"auth"}
    )
    public ResponseEntity registro(@RequestBody @Valid RegistroDTO data){
        if(this.repository.findByUsername(data.username()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.username(), encryptedPassword, data.role());

        this.repository.save(newUser);

        // Debugging
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        System.out.println("Usuário autenticado: " + auth.getName());
        System.out.println("Permissões: " + auth.getAuthorities());

        return ResponseEntity.ok().build();
    }

}