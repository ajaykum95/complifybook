package com.abhaempire.complifybook.models;

import com.abhaempire.complifybook.enums.EnquiryStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "tbl_enquiry")
public class Enquiry extends BaseDbEntity {
    @NotNull
    @Column(nullable = false)
    private String name;

    private String email;

    @NotNull
    @Column(nullable = false)
    private String mobile;

    private String url;

    @Enumerated(EnumType.STRING)
    private EnquiryStatus enquiryStatus;
}
