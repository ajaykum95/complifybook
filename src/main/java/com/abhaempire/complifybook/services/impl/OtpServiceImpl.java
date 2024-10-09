package com.abhaempire.complifybook.services.impl;

import com.abhaempire.complifybook.dtos.CommonResponse;
import com.abhaempire.complifybook.dtos.OtpRequest;
import com.abhaempire.complifybook.enums.AbhaException;
import com.abhaempire.complifybook.enums.OtpStatus;
import com.abhaempire.complifybook.externals.sms.service.SmsService;
import com.abhaempire.complifybook.models.Otp;
import com.abhaempire.complifybook.repositories.OtpRepository;
import com.abhaempire.complifybook.services.OtpService;
import com.abhaempire.complifybook.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.abhaempire.complifybook.utils.ExceptionUtil.buildException;

@Service
public class OtpServiceImpl implements OtpService {

    private final OtpRepository otpRepository;
    private final SmsService smsService;

    @Autowired
    public OtpServiceImpl(OtpRepository otpRepository, SmsService smsService) {
        this.otpRepository = otpRepository;
        this.smsService = smsService;
    }

    @Override
    public CommonResponse generateOtp(OtpRequest otpRequest) {
        Otp otp = otpRepository.findByMobile(otpRequest.getMobile())
                .orElse(ObjectMapper.mapToSaveOtp(otpRequest));
        ObjectMapper.mapToSaveOtp(otp, otpRequest);
        boolean result = smsService.sendSms(otp);
        if (!result){
            throw buildException(AbhaException.SMS_SENDER_ERROR);
        }
        otpRepository.save(otp);
        return ObjectMapper.mapToSentOtpResponse();
    }

    @Override
    public Otp findByMobileAndLastOtp(String mobile, String otp) {
        return otpRepository.findByMobileAndLastOtp(mobile, otp);
    }

    @Override
    public void saveUsedOtp(Otp otp) {
        otp.setOtpStatus(OtpStatus.USED);
        otpRepository.save(otp);
    }
}
