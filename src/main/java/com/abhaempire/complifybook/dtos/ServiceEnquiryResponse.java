package com.abhaempire.complifybook.dtos;

import com.abhaempire.complifybook.enums.EnquiryStatus;
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
public class ServiceEnquiryResponse {
    private int id;
    private LocalDate enquiryDate;
    private String name;
    private String email;
    private String mobile;
    private String urlPath;
    private EnquiryStatus status;
}
