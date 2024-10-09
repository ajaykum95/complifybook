package com.abhaempire.complifybook.externals.sms.twofactor;

import com.abhaempire.complifybook.externals.sms.service.SmsViaProvider;
import com.abhaempire.complifybook.models.Otp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("twoFactorSmsService")
@Slf4j
public class TwoFactorSms implements SmsViaProvider {
    @Override
    public boolean sendSms(Otp otp) {
        //TODO IMPLEMENT CODE
        log.info("2FactorSMS OTP {} Sent on mobile : {}", otp.getLastOtp(), otp.getMobile());
        return true;
    }
}
