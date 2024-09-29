package com.abhaempire.complifybook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/invoice")
public class InvoiceController {

    @GetMapping()
    public String invoice(){
        return "private/invoice";
    }
    @GetMapping("/new")
    public String newInvoice(){
        return "private/invoice-new";
    }
}
