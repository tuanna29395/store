package com.anhtuan.store.model;

import lombok.Data;

import javax.persistence.*;

@Table(name = "user")
@Entity
@Data
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private String userName;

    @OneToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;
}
