package com.abhaempire.complifybook.services.impl;

import com.abhaempire.complifybook.dtos.CommonResponse;
import com.abhaempire.complifybook.dtos.SubscriberResponse;
import com.abhaempire.complifybook.dtos.SubscriptionRequest;
import com.abhaempire.complifybook.enums.AbhaException;
import com.abhaempire.complifybook.enums.StatusTypeEnum;
import com.abhaempire.complifybook.models.Subscriber;
import com.abhaempire.complifybook.repositories.SubscriberRepo;
import com.abhaempire.complifybook.services.SubscriptionService;
import com.abhaempire.complifybook.utils.ObjectMapper;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

import static com.abhaempire.complifybook.utils.ExceptionUtil.buildException;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

  private final SubscriberRepo subscriberRepo;

  public SubscriptionServiceImpl(SubscriberRepo subscriberRepo) {
    this.subscriberRepo = subscriberRepo;
  }

  @Override
  public CommonResponse saveSubscriber(SubscriptionRequest subscriptionRequest) {
    Subscriber subscriberExist = subscriberRepo.findByEmailAndStatusNot(subscriptionRequest.getEmail(),
        StatusTypeEnum.DELETED);
    if (Objects.nonNull(subscriberExist)){
      return ObjectMapper.mapToExistSubscriptionResponse();
    }
    Subscriber subscriber = ObjectMapper.maptoSubscriber(subscriptionRequest);
    subscriberRepo.save(subscriber);
    return ObjectMapper.mapToPassSubscriptionResponse();
  }

  @Override
  public List<SubscriberResponse> fetchAllSubscriber() {
    List<Subscriber> subscriberList = subscriberRepo.findByStatusNot(StatusTypeEnum.DELETED);
    return ObjectMapper.mapToSubscriberResponse(subscriberList);
  }

  @Override
  public void deleteSubscriber(Integer subscriberId) {
    Subscriber subscriber = subscriberRepo.findByIdAndStatusNot(subscriberId, StatusTypeEnum.DELETED)
            .orElseThrow(() -> buildException(AbhaException.SUBSCRIBER_NOT_FOUND));
    subscriber.setStatus(StatusTypeEnum.DELETED);
    subscriberRepo.save(subscriber);
  }
}
