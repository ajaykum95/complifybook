package com.abhaempire.complifybook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/service")
public class PublicServiceController {

    @GetMapping("/{serviceSlug}")
    public String service(@PathVariable String serviceSlug){
        return "public/service-details";
    }

    @GetMapping("/all")
    public String allService(){
        return "public/services";
    }
}
