package com.jtzook.gbapi.models;

import com.jtzook.gbapi.auth.ERole;

import jakarta.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long role_id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    // foreign key user_id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserRole() {

    }

    public UserRole(ERole name) {
        this.name = name;
    }

    public Long getId() {
        return role_id;
    }

    public void setId(Long id) {
        this.role_id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}

