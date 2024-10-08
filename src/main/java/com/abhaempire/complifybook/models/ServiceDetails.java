package com.abhaempire.complifybook.models;


import com.abhaempire.complifybook.enums.StatusTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "tbl_service_detail")
public class ServiceDetails extends BaseEntity{

    @ManyToOne(targetEntity = Service.class)
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @NotNull
    @Column(nullable = false)
    private String tabName;

    @NotNull
    @Min(value = 1, message = "Order start from 1")
    private int tabOrder;

    @NotNull
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private StatusTypeEnum status;
}
