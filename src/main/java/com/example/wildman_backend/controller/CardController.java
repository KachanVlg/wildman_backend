package com.example.wildman_backend.controller;


import com.example.wildman_backend.domain.dto.CardDto;
import com.example.wildman_backend.domain.model.model.deck.Card;
import com.example.wildman_backend.service.CardService;
import com.example.wildman_backend.util.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/decks/{deckId}/cards")
public class CardController {

    private final CardService cardService;
    private final DtoMapper dtoMapper;

    @PutMapping("/{cardId}")
    public void updateCard(
            @PathVariable Long deckId,
            @PathVariable Long cardId,
            @RequestBody CardDto dto
    ) {
        cardService.updateCard(deckId, cardId, dtoMapper.toModel(dto, Card.class));
    }

    @PostMapping
    public CardDto createCard(
            @PathVariable Long deckId,
            @RequestBody CardDto dto
    ) {

        Card card = dtoMapper.toModel(dto, Card.class);
        return dtoMapper.toDto(cardService.createCard(deckId, card), CardDto.class);
    }

    @DeleteMapping("/{cardId}")
    public void deleteCard(
            @PathVariable Long deckId,
            @PathVariable Long cardId
    ) {
        cardService.deleteCard(cardId);
    }

    @GetMapping
    public List<CardDto> getCardsByDeck(@PathVariable Long deckId, Authentication authentication) {
        List<Card> cards = cardService.getCardByDeck(deckId, authentication);
        return dtoMapper.toDto(cards, CardDto.class);
    }

}
