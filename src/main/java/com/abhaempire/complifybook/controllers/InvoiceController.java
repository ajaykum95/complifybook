package com.abhaempire.complifybook.controllers;

import com.abhaempire.complifybook.dtos.InvoiceResponse;
import com.abhaempire.complifybook.models.enums.PaymentStatus;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1/invoice")
public class InvoiceController {

    @GetMapping()
    public String invoice(){
        return "private/invoice";
    }
    @GetMapping("/fetchAll")
    @ResponseBody
    public List<InvoiceResponse> AllInvoice(){
        return Collections.singletonList(
                InvoiceResponse.builder()
                        .id(1)
                        .orderNumber("INV00BH240101001")
                        .customerName("Ajay Kumar Tiwari")
                        .invoiceAmount(8999.00)
                        .taxAmount(999.00)
                        .dueAmount(8999.00)
                        .paymentStatus(PaymentStatus.DUE)
                        .date(LocalDate.now())
                        .build()
        );
    }
    @GetMapping("/new")
    public String newInvoice(){
        return "private/invoice-new";
    }
}
