package com.jellehuibregtse.cah.authservice.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * The application user model.
 *
 * @author Jelle Huibregtse
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationUser {

    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique=true)
    private @NotNull String username;
    private @NotNull String password;
    private String role;

    public ApplicationUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}