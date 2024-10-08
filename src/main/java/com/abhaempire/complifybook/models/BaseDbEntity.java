package com.abhaempire.complifybook.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@MappedSuperclass
public class BaseDbEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;
}
