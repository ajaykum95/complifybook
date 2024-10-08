package com.abhaempire.complifybook.services;

import com.abhaempire.complifybook.dtos.CallBackEnquiry;
import com.abhaempire.complifybook.dtos.CommonResponse;

public interface EnquiryService {
    CommonResponse saveCallBackEnquiry(CallBackEnquiry callBackEnquiry);
}
