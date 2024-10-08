package com.abhaempire.complifybook.services.impl;

import com.abhaempire.complifybook.dtos.SubscriptionRequest;
import com.abhaempire.complifybook.dtos.SubscriptionResponse;
import com.abhaempire.complifybook.enums.StatusTypeEnum;
import com.abhaempire.complifybook.models.Subscriber;
import com.abhaempire.complifybook.repositories.SubscriberRepo;
import com.abhaempire.complifybook.services.SubscriptionService;
import com.abhaempire.complifybook.utils.ObjectMapper;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

  private final SubscriberRepo subscriberRepo;

  public SubscriptionServiceImpl(SubscriberRepo subscriberRepo) {
    this.subscriberRepo = subscriberRepo;
  }

  @Override
  public SubscriptionResponse saveSubscriber(SubscriptionRequest subscriptionRequest) {
    Subscriber subscriberExist = subscriberRepo.findByEmailAndStatusNot(subscriptionRequest.getEmail(),
        StatusTypeEnum.DELETED);
    if (Objects.nonNull(subscriberExist)){
      return ObjectMapper.mapToExistSubscriptionResponse();
    }
    Subscriber subscriber = ObjectMapper.maptoSubscriber(subscriptionRequest);
    subscriberRepo.save(subscriber);
    return ObjectMapper.mapToPassSubscriptionResponse();
  }
}
