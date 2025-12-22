package com.example.wildman_backend.repository;


import com.example.wildman_backend.domain.model.model.deck.Deck;
import com.example.wildman_backend.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {

    List<Deck> findAllByUserId(Long user_id);
    boolean existsByIdAndUserUsername(Long deckId, String username);
    boolean existsByIdAndUserId(Long deckId, Long userId);
}
