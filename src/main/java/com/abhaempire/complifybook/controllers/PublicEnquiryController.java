package com.abhaempire.complifybook.controllers;

import com.abhaempire.complifybook.dtos.CallBackEnquiry;
import com.abhaempire.complifybook.dtos.CommonResponse;
import com.abhaempire.complifybook.enums.ResultTypeEnum;
import com.abhaempire.complifybook.exception.AbhaBaseRunTimeException;
import com.abhaempire.complifybook.services.EnquiryService;
import com.abhaempire.complifybook.utils.RequestValidator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enquiry")
public class PublicEnquiryController {

    private final EnquiryService enquiryService;

    public PublicEnquiryController(EnquiryService enquiryService) {
        this.enquiryService = enquiryService;
    }

    @PostMapping("/callBack/save")
    public CommonResponse saveCallBackEnquiry(@RequestBody CallBackEnquiry callBackEnquiry) {
        try {
            RequestValidator.validateCallBackRequest(callBackEnquiry);
            return enquiryService.saveCallBackEnquiry(callBackEnquiry);
        } catch (AbhaBaseRunTimeException e) {
            return CommonResponse.builder()
                    .result(ResultTypeEnum.FAIL)
                    .message(e.getMessage())
                    .build();
        }
    }
}
