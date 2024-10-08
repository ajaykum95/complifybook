package com.abhaempire.complifybook.controllers;

import com.abhaempire.complifybook.dtos.SubscriptionRequest;
import com.abhaempire.complifybook.dtos.CommonResponse;
import com.abhaempire.complifybook.enums.ResultTypeEnum;
import com.abhaempire.complifybook.exception.AbhaBaseRunTimeException;
import com.abhaempire.complifybook.services.SubscriptionService;
import com.abhaempire.complifybook.utils.RequestValidator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscriber")
public class PublicSubscriberController {

  private final SubscriptionService subscriptionService;

  public PublicSubscriberController(SubscriptionService subscriptionService) {
    this.subscriptionService = subscriptionService;
  }

  @PostMapping("/new")
  public CommonResponse subscribe(@RequestBody SubscriptionRequest subscriptionRequest){
    try {
      RequestValidator.validateSubscriptionRequest(subscriptionRequest);
      return subscriptionService.saveSubscriber(subscriptionRequest);
    }catch (AbhaBaseRunTimeException e){
      return CommonResponse.builder()
          .result(ResultTypeEnum.FAIL)
          .message(e.getMessage())
          .build();
    }
  }
}
