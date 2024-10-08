package com.abhaempire.complifybook.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CallBackEnquiry {
    private String name;
    private String email;
    private String mobile;
    private String otp;
    private String url;
}
