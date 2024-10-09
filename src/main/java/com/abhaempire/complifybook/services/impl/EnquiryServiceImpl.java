package com.abhaempire.complifybook.services.impl;

import com.abhaempire.complifybook.dtos.CallBackEnquiry;
import com.abhaempire.complifybook.dtos.CommonResponse;
import com.abhaempire.complifybook.enums.ResultTypeEnum;
import com.abhaempire.complifybook.models.Enquiry;
import com.abhaempire.complifybook.models.Otp;
import com.abhaempire.complifybook.repositories.EnquiryRepository;
import com.abhaempire.complifybook.services.EnquiryService;
import com.abhaempire.complifybook.services.OtpService;
import com.abhaempire.complifybook.utils.AppConstant;
import com.abhaempire.complifybook.utils.ObjectMapper;
import jakarta.transaction.Transactional;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnquiryServiceImpl implements EnquiryService {

    private final EnquiryRepository enquiryRepository;
    private final OtpService otpService;

    @Autowired
    public EnquiryServiceImpl(EnquiryRepository enquiryRepository, OtpService otpService) {
        this.enquiryRepository = enquiryRepository;
        this.otpService = otpService;
    }

    @Override
    @Transactional
    public CommonResponse saveCallBackEnquiry(CallBackEnquiry callBackEnquiry) {
        Otp otp = otpService.findByMobileAndLastOtp(callBackEnquiry.getMobile(), callBackEnquiry.getOtp());
        boolean isValidOtp = validateMobileOtp(otp);
        if (!isValidOtp) {
            return CommonResponse.builder()
                    .result(ResultTypeEnum.INVALID)
                    .message(AppConstant.INVALID_OTP)
                    .build();
        }
        Enquiry enquiry = ObjectMapper.mapToSaveEnquiry(callBackEnquiry);
        enquiryRepository.save(enquiry);
        otpService.saveUsedOtp(otp);
        return ObjectMapper.mapToCallBackEnquiryResponse();
    }

    private boolean validateMobileOtp(Otp otp) {
        if (Objects.isNull(otp)) {
            return false;
        }
        return true;
    }
}
