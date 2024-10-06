package com.abhaempire.complifybook.dtos;

import com.abhaempire.complifybook.enums.PaymentStatus;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InvoiceResponse {
    private int id;
    private LocalDate date;
    private String orderNumber;
    private String customerName;
    private Double invoiceAmount;
    private Double taxAmount;
    private Double dueAmount;
    private PaymentStatus paymentStatus;
}
