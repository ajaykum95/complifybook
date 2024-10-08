package com.abhaempire.complifybook.controllers;

import com.abhaempire.complifybook.dtos.TestimonialResponse;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1/testimonial")
public class TestimonialController {

    @GetMapping()
    public String testimonial() {
        return "private/testimonial";
    }

    @GetMapping("/fetchAll")
    @ResponseBody
    public List<TestimonialResponse> fetchAllTestimonial() {
        return Collections.singletonList(
                TestimonialResponse.builder()
                        .id(1)
                        .imagePath("https://img.freepik.com/free-vector/businessman-character-avatar-isolated_24877-60111.jpg")
                        .reviewerName("Ajay Kumar")
                        .position("Developer")
                        .rating(4.8f)
                        .createdAt(LocalDate.now())
                        .build()
        );
    }

    @GetMapping("/new")
    public String newTestimonial() {
        return "private/testimonial-new";
    }
}
