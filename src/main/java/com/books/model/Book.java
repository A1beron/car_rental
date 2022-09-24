package com.books.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @Column(unique = true)
    private int isbn;
    @OneToOne
    private Category category;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "books")
    private List<Author> authors;
    @OneToMany(mappedBy = "book")
    private List<Review> reviews;

 }
