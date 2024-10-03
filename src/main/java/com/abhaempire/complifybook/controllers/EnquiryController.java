package com.abhaempire.complifybook.controllers;

import com.abhaempire.complifybook.dtos.ContactUsEnquiryResponse;
import com.abhaempire.complifybook.dtos.PartnershipEnquiryResponse;
import com.abhaempire.complifybook.dtos.ServiceEnquiryResponse;
import com.abhaempire.complifybook.models.enums.EnquiryStatus;
import com.abhaempire.complifybook.models.enums.StatusTypeEnum;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1/enquiry")
public class EnquiryController {

    @GetMapping("/service")
    public String serviceEnquiry(){
        return "private/service-enquiry";
    }
    @GetMapping("/service/new")
    public String newServiceEnquiry(){
        return "private/service-enquiry-new";
    }
    @GetMapping("/service/fetchAll")
    @ResponseBody
    public List<ServiceEnquiryResponse> serviceEnquiryAll(){
        return Collections.singletonList(
                ServiceEnquiryResponse.builder()
                        .id(1)
                        .enquiryDate(LocalDate.now())
                        .name("Ajay Kumar")
                        .email("ajay30935@gmail.com")
                        .mobile("7550756640")
                        .status(EnquiryStatus.NEW)
                        .urlPath("/service/environmental-clearance")
                        .build()
        );
    }
    @GetMapping("/partnership")
    public String partnershipEnquiry(){
        return "private/partnership-enquiry";
    }
    @GetMapping("/partnership/fetchAll")
    @ResponseBody
    public List<PartnershipEnquiryResponse> partnershipEnquiryAll(){
        return Collections.singletonList(
                PartnershipEnquiryResponse.builder()
                        .id(1)
                        .name("Ajay Kumar")
                        .email("ajay30935@gmail.com")
                        .mobile("7550756640")
                        .occupation("Developer")
                        .status(StatusTypeEnum.ACTIVE)
                        .message("Partnership enquiry message")
                        .enquiryDate(LocalDate.now())
                        .build()
        );
    }
    @GetMapping("/contact-us")
    public String contactUsEnquiry(){
        return "private/contact-us-enquiry";
    }
    @GetMapping("/contact-us/fetchAll")
    @ResponseBody
    public List<ContactUsEnquiryResponse> contactUsEnquiryAll(){
        return Collections.singletonList(
                ContactUsEnquiryResponse.builder()
                        .id(1)
                        .name("Ajay Kumar")
                        .email("ajay30935@gmail.com")
                        .mobile("7578767890")
                        .subject("Service Related Issue")
                        .message("This is message")
                        .status(StatusTypeEnum.ACTIVE)
                        .enquiryDate(LocalDate.now())
                        .build()
        );
    }
}
