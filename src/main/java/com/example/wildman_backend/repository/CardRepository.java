package com.example.wildman_backend.repository;


import com.example.wildman_backend.domain.model.model.deck.Card;
import com.example.wildman_backend.domain.model.model.deck.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findAllByDeckId(Long deck_id);
}
