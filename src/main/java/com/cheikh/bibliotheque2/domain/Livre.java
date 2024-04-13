package com.cheikh.bibliotheque2.domain;

import com.cheikh.bibliotheque2.domain.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Livre {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Category.class)
    private Category category;
    private String titre;
    private String auteur;
    private String description;
    private LocalDate publicationDate;
    @Lob
    private String image;
}
