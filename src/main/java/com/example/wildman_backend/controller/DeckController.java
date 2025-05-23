package com.example.wildman_backend.controller;


import com.example.wildman_backend.domain.dto.CardDto;
import com.example.wildman_backend.domain.dto.DeckDto;
import com.example.wildman_backend.domain.model.model.deck.Card;
import com.example.wildman_backend.domain.model.model.deck.Deck;
import com.example.wildman_backend.service.DeckService;
import com.example.wildman_backend.util.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/decks")
public class DeckController {

    private final DeckService deckService;
    private final DtoMapper dtoMapper;

    @GetMapping
    public List<DeckDto> getDecks(Authentication authentication) {
        return dtoMapper.toDto(deckService.getDecks(authentication), DeckDto.class);
    }

    @PostMapping
    public DeckDto createDeck(Authentication authentication, @RequestBody DeckDto deckDto) {
        Deck deck = dtoMapper.toModel(deckDto, Deck.class);
        return dtoMapper.toDto(deckService.createDeck(authentication, deck), DeckDto.class);
    }

    @GetMapping("{deckId}")
    public DeckDto getDeck(@PathVariable Long deckId) {
        return dtoMapper.toDto(deckService.getDeck(deckId), DeckDto.class);
    }

    @DeleteMapping("{deckId}")
    public void deleteDeck(@PathVariable Long deckId) {
        deckService.deleteDeck(deckId);
    }

    @PutMapping("{deckId}")
    public void changeDeckName(@PathVariable Long deckId, @RequestBody String newName) {
        deckService.changeDeckName(deckId, newName);
    }
}
