package com.abhaempire.complifybook.models;

import com.abhaempire.complifybook.enums.AddressType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@Entity
@Table(name = "tbl_user_address")
public class UserAddress extends BaseEntity {

    @Column(nullable = false)
    private String name;

    private String email;

    @Column(nullable = false)
    private String mobile;

    private String alternateMobile;

    @Column(name = "pin_code", nullable = false)
    private String pinCode;

    @Column(name = "locality", nullable = false)
    private String locality;

    @Column(name = "address_line_1", nullable = false)
    private String addressLine1;

    @Column(name = "address_line_2")
    private String addressLine2;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "state_code", nullable = false)
    private String stateCode;

    private String landmark;

    private boolean isDefault;

    @Enumerated(EnumType.STRING)
    @Column(name = "address_type", nullable = false)
    private AddressType addressType;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;
}
