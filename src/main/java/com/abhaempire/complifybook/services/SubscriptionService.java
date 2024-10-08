package com.abhaempire.complifybook.services;

import com.abhaempire.complifybook.dtos.SubscriberResponse;
import com.abhaempire.complifybook.dtos.SubscriptionRequest;
import com.abhaempire.complifybook.dtos.CommonResponse;
import java.util.List;

public interface SubscriptionService {
  CommonResponse saveSubscriber(SubscriptionRequest subscriptionRequest);

    List<SubscriberResponse> fetchAllSubscriber();

  void deleteSubscriber(Integer subscriberId);
}
