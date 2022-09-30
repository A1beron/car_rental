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
public class User {

    public User(String login,
                String email,
                String password,
                String firstName,
                String lastName,
                List<Role> roles,
                Address address) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
        this.address = address;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, length = 25)
    private String login;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
    @JoinTable(name = "User_Roles",
            joinColumns = {@JoinColumn(name = "user_id")}, // klucz obcy w tabeli Autor
            inverseJoinColumns = {@JoinColumn(name = "role_id")}) // klucz główny w tab
    private List<Role> roles;
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    private Address address;
    @OneToMany(mappedBy = "user")
    private List<Rental> rentals;

}
