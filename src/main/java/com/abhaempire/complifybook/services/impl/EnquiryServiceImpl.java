package com.abhaempire.complifybook.services.impl;

import com.abhaempire.complifybook.dtos.CallBackEnquiry;
import com.abhaempire.complifybook.dtos.CommonResponse;
import com.abhaempire.complifybook.models.Enquiry;
import com.abhaempire.complifybook.repositories.EnquiryRepository;
import com.abhaempire.complifybook.services.EnquiryService;
import com.abhaempire.complifybook.utils.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class EnquiryServiceImpl implements EnquiryService {

    private final EnquiryRepository enquiryRepository;

    public EnquiryServiceImpl(EnquiryRepository enquiryRepository) {
        this.enquiryRepository = enquiryRepository;
    }

    @Override
    public CommonResponse saveCallBackEnquiry(CallBackEnquiry callBackEnquiry) {
        validateMobileOtp(callBackEnquiry);
        Enquiry enquiry = ObjectMapper.mapToSaveEnquiry(callBackEnquiry);
        enquiryRepository.save(enquiry);
        return ObjectMapper.mapToCallBackEnquiryResponse();
    }

    private void validateMobileOtp(CallBackEnquiry callBackEnquiry) {
        //TODO VALIDATE MOBILE WITH OTP
    }
}
