package com.abhaempire.complifybook.services;

import com.abhaempire.complifybook.dtos.SubscriptionRequest;
import com.abhaempire.complifybook.dtos.SubscriptionResponse;

public interface SubscriptionService {
  SubscriptionResponse saveSubscriber(SubscriptionRequest subscriptionRequest);
}
