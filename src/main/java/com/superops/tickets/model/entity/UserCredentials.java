package com.superops.tickets.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserCredentials {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "user_id")
    private String userId;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="authority")
    private String authority;

}
