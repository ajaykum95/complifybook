package com.abhaempire.complifybook.externals.sms.way2sms;

import com.abhaempire.complifybook.externals.sms.service.SmsViaProvider;
import com.abhaempire.complifybook.models.Otp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("way2SmsService")
@Slf4j
public class Way2Sms implements SmsViaProvider {
    @Override
    public boolean sendSms(Otp otp) {
        //TODO IMPLEMENT CODE
        log.info("Way2SMS OTP {} Sent on mobile : {}", otp.getLastOtp(), otp.getMobile());
        return true;
    }
}
