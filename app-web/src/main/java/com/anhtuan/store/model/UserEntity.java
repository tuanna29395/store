package com.anhtuan.store.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "user")
@Entity
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    private String name;
    @OneToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;
    private Integer deletedFlg;
    private Timestamp createdAt;
    private Timestamp updateAt;

}
