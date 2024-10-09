package com.abhaempire.complifybook.models;

import com.abhaempire.complifybook.enums.OtpStatus;
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
@Table(name = "tbl_otp")
public class Otp extends BaseDbEntity {
    @NotNull
    @Column(unique = true)
    private String mobile;

    @NotNull
    private String lastOtp;

    private String oldOtp;

    @Enumerated(EnumType.STRING)
    private OtpStatus otpStatus;

    private String url;
}
