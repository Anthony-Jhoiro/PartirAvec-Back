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
    @Lob
    private String text;
    @ElementCollection
    private Collection<String> images;
    private double lat;
    private double lng;
    private String location;
    @CreationTimestamp
    private LocalDateTime createDate;
    @UpdateTimestamp
    private LocalDateTime updateDate;

    @ManyToOne
    private Country country;


    public Destination(int id, String title, String text, Collection<String> images, double lat, double lng, String location, LocalDateTime createDate, LocalDateTime updateDate, Country country) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.images = images;
        this.lat = lat;
        this.lng = lng;
        this.location = location;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.country = country;
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
                ", lat=" + lat +
                ", lng=" + lng +
                ", location='" + location + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", country=" + country +
                '}';
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
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
}
