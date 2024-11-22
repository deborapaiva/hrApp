package br.com.hrapp.hrapp.models;

import jakarta.persistence.*;

@Entity
@Table(name="tb_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private Long roleId;
    private String name;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public enum UserRole {
        ADMIN("admin"),
        BASIC("user");

        private String role;

        UserRole(String role){
            this.role = role;
        }

        public String getRole(){
            return role;
        }
    }
}
