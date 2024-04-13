package com.cheikh.bibliotheque2.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Emprunt {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY,targetEntity= Utilisateur.class)
    private Utilisateur utilisateur;
    @ManyToOne(fetch = FetchType.LAZY,targetEntity= Livre.class)
    private Livre livre;
    private Boolean rendu;


}
