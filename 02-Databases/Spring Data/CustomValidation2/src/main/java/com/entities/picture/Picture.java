package com.entities.picture;

import com.entities.album.Album;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "pictures")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String caption;

    @Column(nullable = false)
    private String filepath;

    @ManyToMany(mappedBy = "pictures")
    Set<Album> albums;

    public Picture() {
        this.albums = new HashSet<>();
    }

    public Picture(String filepath) {
        this();
        this.filepath = filepath;
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

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Set<Album> getAlbums() {
        return Collections.unmodifiableSet(albums);
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }
}
