package com.abhaempire.complifybook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/subscriber")
public class SubscriberController {

    @GetMapping()
    public String subscribers(){
        return "private/subscribers";
    }
    @GetMapping("/new")
    public String newSubscribers(){
        return "private/subscribers";
    }
}
