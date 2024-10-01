package com.shoe_ecommerce.brand.context.infrastructure.persistence.jpa.models;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "brands")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JpaBrand {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", columnDefinition = "VARCHAR(100)", nullable = false, unique = true)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(
            name = "joined_at",
            columnDefinition = "DATE",
            nullable = false,
            updatable = false,
            insertable = false)
    private LocalDate joinedAt;

    @Column(name = "about", columnDefinition = "TEXT", nullable = false)
    private String about;

    @Column(name = "logo", columnDefinition = "TINYTEXT", nullable = false)
    private String logo;

    @Column(name = "banner", columnDefinition = "TINYTEXT", nullable = false)
    private String banner;
}
