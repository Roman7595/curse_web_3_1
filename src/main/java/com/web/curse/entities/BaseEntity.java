package com.web.curse.entities;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {
    private long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
