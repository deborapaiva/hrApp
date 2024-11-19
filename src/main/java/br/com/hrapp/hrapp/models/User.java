package br.com.hrapp.hrapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name ="user_id")
    private UUID userID;

    @Column(unique = true)
    @NotNull(message = "UserName não pode ser nulo")
    private String username;

    @NotNull(message = "Senha não pode ser nulo")
    private String password;


    //REFERENCIAS ENTRE TABELAS USER_ID E ROLE_ID
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )

    private Set<Role> roles;

}
