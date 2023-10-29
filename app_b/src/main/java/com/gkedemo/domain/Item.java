package com.gkedemo.domain;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private UUID ownerId;

    public Item() {
    }

    public Item(final String name, final UUID ownerId) {
        this.name = name;
        this.ownerId = ownerId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;
        return Objects.equals(id, item.id) && Objects.equals(name, item.name) && Objects.equals(ownerId, item.ownerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ownerId);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ownerId=" + ownerId +
                '}';
    }
}