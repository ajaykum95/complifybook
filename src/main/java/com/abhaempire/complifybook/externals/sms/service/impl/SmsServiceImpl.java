package com.abhaempire.complifybook.externals.sms.service.impl;

import com.abhaempire.complifybook.configs.ProviderConfig;
import com.abhaempire.complifybook.configs.SmsProviderConfig;
import com.abhaempire.complifybook.externals.sms.service.SmsService;
import com.abhaempire.complifybook.externals.sms.service.SmsViaProvider;
import com.abhaempire.complifybook.models.Otp;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService {
    private final SmsViaProvider way2SmsService;
    private final SmsViaProvider twoFactorSmsService;
    private final SmsProviderConfig smsProviderConfig;

    public SmsServiceImpl(@Qualifier("way2SmsService") SmsViaProvider way2SmsService,
                          @Qualifier("twoFactorSmsService") SmsViaProvider twoFactorSmsService,
                          ProviderConfig providerConfig) {
        this.way2SmsService = way2SmsService;
        this.twoFactorSmsService = twoFactorSmsService;
        this.smsProviderConfig = providerConfig.getProviderConfig();
    }

    @Override
    public boolean sendSms(Otp otp) {
        return getActiveProvider().sendSms(otp);
    }

    private SmsViaProvider getActiveProvider() {
        return switch (smsProviderConfig.getActiveSmsProvider()){
            case WAY2SMS -> way2SmsService;
            case TWO_FACTOR -> twoFactorSmsService;
        };
    }
}
