package com.partiravec.destinationservice.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Book {
    @GeneratedValue(strategy= GenerationType.AUTO) @Id
    private int id;

    private String title;

    @ElementCollection
    private Collection<String> users;

    public Book(int id, String title, Collection<String> users) {
        this.id = id;
        this.title = title;
        this.users = users;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<String> getUsers() {
        return users;
    }

    public void setUsers(Collection<String> users) {
        this.users = users;
    }
}
