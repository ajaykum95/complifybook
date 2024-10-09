package com.abhaempire.complifybook.repositories;

import com.abhaempire.complifybook.models.Otp;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<Otp, Integer> {
    Optional<Otp> findByMobile(String mobile);

    Otp findByMobileAndLastOtp(String mobile, String otp);
}
