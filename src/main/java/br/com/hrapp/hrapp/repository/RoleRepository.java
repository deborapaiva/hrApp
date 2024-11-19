package br.com.hrapp.hrapp.repository;

import br.com.hrapp.hrapp.models.Role;
import br.com.hrapp.hrapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
