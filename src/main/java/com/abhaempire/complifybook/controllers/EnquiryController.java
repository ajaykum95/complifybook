package com.abhaempire.complifybook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/enquiry")
public class EnquiryController {

    @GetMapping("/service")
    public String serviceEnquiry(){
        return "private/service-enquiry";
    }
    @GetMapping("/partnership")
    public String partnershipEnquiry(){
        return "private/partnership-enquiry";
    }
    @GetMapping("/contact-us")
    public String contactUsEnquiry(){
        return "private/contact-us-enquiry";
    }
}
