package com.jellehuibregtse.cah.cardservice.repositories;

import com.jellehuibregtse.cah.cardservice.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findById(long id);
    List<Card> findAll();
}
