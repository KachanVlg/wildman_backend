package com.example.wildman_backend.domain.model.model.deck;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cards")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "word")
    private String word;

    @Column(name = "translation")
    private String translation;

    @ManyToOne
    @JoinColumn(name = "deck_id")
    private Deck deck;


}
