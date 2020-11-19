package com.jellehuibregtse.cah.gameservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * The game object.
 *
 * @author Jelle Huibregtse
 */
@Getter
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ElementCollection
    private List<Long> cardIds;

    @NotNull
    private boolean isPrivate;

    private String username;
    private String password;
}
