package com.jellehuibregtse.cah.cardservice.repositories;

import com.jellehuibregtse.cah.cardservice.models.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {
    @Override
    List<Card> findAll();
}
