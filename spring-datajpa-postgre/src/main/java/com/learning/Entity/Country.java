package com.learning.Entity;

import javax.persistence.*;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private boolean status;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Country setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isStatus() {
        return status;
    }

    public Country setStatus(boolean status) {
        this.status = status;
        return this;
    }
}
