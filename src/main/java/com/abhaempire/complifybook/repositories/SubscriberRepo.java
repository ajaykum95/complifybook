package com.abhaempire.complifybook.repositories;

import com.abhaempire.complifybook.enums.StatusTypeEnum;
import com.abhaempire.complifybook.models.Subscriber;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriberRepo extends JpaRepository<Subscriber, Integer> {
  Subscriber findByEmailAndStatusNot(String email, StatusTypeEnum status);
}
