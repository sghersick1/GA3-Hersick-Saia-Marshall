package edu.loyola.cs485.model.entity;

import java.sql.Timestamp;

public class Playlist extends AbstractEntity{
    private Integer id;
    private String name;
    private Timestamp creationTimestamp;

    // Constructors
    public Playlist(){}

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Timestamp creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationTimestamp=" + creationTimestamp +
                '}';
    }
}
