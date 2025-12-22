package com.example.wildman_backend.service;


import com.example.wildman_backend.domain.exception.ValidationException;
import com.example.wildman_backend.domain.model.model.deck.Card;
import com.example.wildman_backend.domain.model.model.deck.Deck;
import com.example.wildman_backend.domain.model.user.User;
import com.example.wildman_backend.repository.CardRepository;
import com.example.wildman_backend.repository.DeckRepository;
import com.example.wildman_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final DeckRepository deckRepository;
    private final UserRepository userRepository;

    public Card createCard(Long deckId, Card card) {
        card.setDeck(deckRepository.findById(deckId).get());
        return cardRepository.save(card);
    }

    public void updateCard(Long deckId, Long cardId, Card card) {
        card.setId(cardId);
        card.setDeck(deckRepository.findById(deckId).get());
        cardRepository.save(card);
    }

    public void deleteCard(Long cardId) {
        cardRepository.deleteById(cardId);
    }

    public List<Card> getCardByDeck(Long deckId, Authentication authentication) {

        String username = authentication.getName();
        User user = userRepository.findByUsername(username).get();
        boolean deckBelongsToUser = deckRepository.existsByIdAndUserUsername(deckId, username);

        if (!deckBelongsToUser) {
            throw new UsernameNotFoundException("Deck does not belong to user");
        }
        return cardRepository.findAllByDeckId(deckId);
    }
}
