package com.abhaempire.complifybook.externals.sms.service;

import com.abhaempire.complifybook.models.Otp;

public interface SmsService {
    boolean sendSms(Otp otp);
}
