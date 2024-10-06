package com.abhaempire.complifybook.models;

import com.abhaempire.complifybook.enums.StatusTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tbl_tag_service")
public class TagService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = Service.class)
    @JoinColumn(name = "service_id", nullable = false, referencedColumnName = "id")
    private Service service;

    @NotNull
    @Column(nullable = false)
    private Integer tagServiceId;

    @Enumerated(EnumType.STRING)
    private StatusTypeEnum status;
}
