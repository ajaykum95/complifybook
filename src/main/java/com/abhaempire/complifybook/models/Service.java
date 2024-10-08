package com.abhaempire.complifybook.models;

import com.abhaempire.complifybook.enums.StatusTypeEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Table(name = "tbl_service")
public class Service extends BaseEntity{

    @ManyToOne(targetEntity = SubCategory.class)
    @JoinColumn(nullable = false, name = "sub_category_id", referencedColumnName = "id")
    private SubCategory subCategory;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false, unique = true)
    private String slug;

    @Min(value = 3)
    private float rating;

    @Min(value = 0)
    private int ratingCount;

    @NotNull
    @Column(nullable = false, columnDefinition = "TEXT")
    private String summary;

    @Enumerated(EnumType.STRING)
    private StatusTypeEnum status;

    @OneToMany(mappedBy = "service", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<TagService> tagServices;
}
