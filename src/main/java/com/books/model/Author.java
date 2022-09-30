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
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Author_Book",
            joinColumns = {@JoinColumn(name = "book_id")}, // klucz obcy w tabeli Autor
            inverseJoinColumns = {@JoinColumn(name = "author_id")}) // klucz główny w tab
    private List<Book> books;

}
