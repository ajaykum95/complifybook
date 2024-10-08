package com.abhaempire.complifybook.dtos;

import com.abhaempire.complifybook.enums.StatusTypeEnum;
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
public class ContactUsEnquiryResponse {
    private int id;
    private String name;
    private String email;
    private String mobile;
    private String subject;
    private String message;
    private LocalDate enquiryDate;
    private StatusTypeEnum status;
}
