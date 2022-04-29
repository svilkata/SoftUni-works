package com.entities.album;

import com.entities.picture.Picture;
import com.entities.user.User;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    private AlbumColor color = AlbumColor.WHITE;

    private boolean isPublic = false;

    @ManyToMany(targetEntity = Picture.class)
    private Set<Picture> pictures;

    @ManyToOne(targetEntity = User.class)
    private User owner;

    public Album() {
    }

    public Album(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AlbumColor getColor() {
        return color;
    }

    public void setColor(AlbumColor color) {
        this.color = color;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public Set<Picture> getPictures() {
        return Collections.unmodifiableSet(pictures);
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }
}
