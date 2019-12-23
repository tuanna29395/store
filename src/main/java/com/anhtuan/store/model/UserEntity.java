package com.anhtuan.store.model;

import lombok.Data;

import javax.persistence.*;

@Table(name = "user")
@Entity
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;
}
