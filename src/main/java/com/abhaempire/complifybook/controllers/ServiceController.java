package com.abhaempire.complifybook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/service")
public class ServiceController {

    @GetMapping()
    public String service(){
        return "private/services";
    }
    @GetMapping("/new")
    public String newService(){
        return "private/service-new";
    }
    @GetMapping("/details")
    public String serviceDetails(){
        return "private/service-details";
    }
    @GetMapping("/details/new")
    public String newServiceDetails(){
        return "private/service-details-new";
    }
}
