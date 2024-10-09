package com.abhaempire.complifybook.controllers;

import com.abhaempire.complifybook.dtos.CommonResponse;
import com.abhaempire.complifybook.dtos.OtpRequest;
import com.abhaempire.complifybook.enums.ResultTypeEnum;
import com.abhaempire.complifybook.exception.AbhaBaseRunTimeException;
import com.abhaempire.complifybook.services.OtpService;
import com.abhaempire.complifybook.utils.RequestValidator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/otp")
public class OtpController {

    private final OtpService otpService;

    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }

    @PostMapping("/generate")
    public CommonResponse generateOtp(@RequestBody OtpRequest otpRequest) {
        try {
            RequestValidator.validateOtpRequest(otpRequest);
            return otpService.generateOtp(otpRequest);
        } catch (AbhaBaseRunTimeException e) {
            return CommonResponse.builder()
                    .result(ResultTypeEnum.FAIL)
                    .message(e.getMessage())
                    .build();
        }
    }
}
