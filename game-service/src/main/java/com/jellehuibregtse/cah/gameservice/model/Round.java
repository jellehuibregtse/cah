package com.jellehuibregtse.cah.gameservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * The round object, which represents one round of the game.
 *
 * @author Jelle Huibregtse
 */
@Getter
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ElementCollection
    private List<Long> cardIds;
}
