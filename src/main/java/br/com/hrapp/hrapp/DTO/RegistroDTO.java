package br.com.hrapp.hrapp.DTO;

import br.com.hrapp.hrapp.models.Role;

public record RegistroDTO(String username, String password, Role.UserRole role) {
}
