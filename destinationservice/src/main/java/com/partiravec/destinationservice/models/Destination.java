package com.partiravec.destinationservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Destination {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String title;
    private String text;
    @ElementCollection
    private Collection<String> images;
    private String location;
    @ManyToOne
    private Country country;
    private int author;
    @CreationTimestamp
    private LocalDateTime createDate;
    @UpdateTimestamp
    private LocalDateTime updateDate;

    public Destination(int id, String title, String text, ArrayList<String> images, String location, Country country, int author, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.images = images;
        this.location = location;
        this.country = country;
        this.author = author;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Destination() {
    }

    @Override
    public String toString() {
        return "Destination{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", images=" + images +
                ", location='" + location + '\'' +
                ", country=" + country +
                ", author=" + author +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Collection<String> getImages() {
        return images;
    }

    public String getLocation() {
        return location;
    }

    public Country getCountry() {
        return country;
    }

    public int getAuthor() {
        return author;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setAuthor(int author) {
        this.author = author;
    }
}
