package ru.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;


@Setter
@Getter
@Entity
public class Locations {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @Column(name = "latitude")
    private BigDecimal latitude;
    @Basic(optional = false)
    @Column(name = "longitude")
    private BigDecimal longitude;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Locations locations = (Locations) o;
        return Objects.equals(id, locations.id) && Objects.equals(name, locations.name) && Objects.equals(userId, locations.userId) && Objects.equals(latitude, locations.latitude) && Objects.equals(longitude, locations.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, userId, latitude, longitude);
    }
}
