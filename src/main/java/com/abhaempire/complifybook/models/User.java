package com.abhaempire.complifybook.models;

import com.abhaempire.complifybook.enums.StatusTypeEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import java.util.Date;
import java.util.List;
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
@Table(name = "tbl_user")
public class User extends BaseEntity {

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @Email
    @Column(name = "email", nullable = false)
    private String email;

    private boolean isEmailVerified;

    private String mobile;

    private boolean isMobileVerified;

    @Column(name = "password", nullable = false)
    private String password;

    private String token;

    @Column(name = "dob")
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    private StatusTypeEnum status;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private List<UserRole> userRoles;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<UserAddress> userAddressList;
}
