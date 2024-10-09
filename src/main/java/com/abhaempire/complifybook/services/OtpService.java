package com.abhaempire.complifybook.services;

import com.abhaempire.complifybook.dtos.CommonResponse;
import com.abhaempire.complifybook.dtos.OtpRequest;
import com.abhaempire.complifybook.models.Otp;

public interface OtpService {
    CommonResponse generateOtp(OtpRequest otpRequest);

    Otp findByMobileAndLastOtp(String mobile, String otp);

    void saveUsedOtp(Otp otp);
}
