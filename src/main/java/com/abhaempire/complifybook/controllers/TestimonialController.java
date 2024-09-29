package com.abhaempire.complifybook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/testimonial")
public class TestimonialController {

    @GetMapping()
    public String testimonial(){
        return "private/testimonial";
    }
    @GetMapping("/new")
    public String newTestimonial(){
        return "private/testimonial-new";
    }
}
