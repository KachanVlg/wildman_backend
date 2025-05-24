package com.example.wildman_backend.service;


import com.example.wildman_backend.domain.model.model.deck.Card;
import com.example.wildman_backend.repository.CardRepository;
import com.example.wildman_backend.repository.DeckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final DeckRepository deckRepository;

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

    public List<Card> getCardByDeck(Long deckId) {
        return cardRepository.findAllByDeckId(deckId);
    }
}
