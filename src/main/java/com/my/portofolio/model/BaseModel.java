package com.my.portofolio.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
