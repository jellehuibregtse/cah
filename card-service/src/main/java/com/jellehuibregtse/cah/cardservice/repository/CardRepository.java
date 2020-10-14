package com.jellehuibregtse.cah.cardservice.repository;

import com.jellehuibregtse.cah.cardservice.model.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {}
