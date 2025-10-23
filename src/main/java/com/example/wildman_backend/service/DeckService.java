package com.example.wildman_backend.service;


import com.example.wildman_backend.domain.model.model.deck.Card;
import com.example.wildman_backend.domain.model.model.deck.Deck;
import com.example.wildman_backend.domain.model.user.User;
import com.example.wildman_backend.repository.DeckRepository;
import com.example.wildman_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Transactional
@RequiredArgsConstructor
@Service
public class DeckService {

    private final UserRepository userRepository;
    private final DeckRepository deckRepository;

    public List<Deck> getDecks(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).get();
        return deckRepository.findAllByUserId(user.getId());
    }

    public Deck createDeck(Authentication authentication, Deck deck) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).get();
        deck.setUser(user);
        return deckRepository.save(deck);
    }

    public Deck getDeck(Long deckId) {
        return deckRepository.findById(deckId).get();
    }

    public void deleteDeck(Long deckId) {
        deckRepository.deleteById(deckId);
    }

    public void changeDeckName(Long deckId, String newName) {
        Deck deck = deckRepository.findById(deckId).get();
        deck.setName(newName);
        deckRepository.save(deck);
    }


}
