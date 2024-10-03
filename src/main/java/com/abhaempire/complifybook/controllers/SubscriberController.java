package com.abhaempire.complifybook.controllers;

import com.abhaempire.complifybook.dtos.SubscriberResponse;
import com.abhaempire.complifybook.models.enums.StatusTypeEnum;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1/subscriber")
public class SubscriberController {

    @GetMapping()
    public String subscribers() {
        return "private/subscribers";
    }

    @GetMapping("/fetchAll")
    @ResponseBody
    public List<SubscriberResponse> allSubscribers() {
        return Collections.singletonList(
                SubscriberResponse.builder()
                        .id(1)
                        .subscribedOn(LocalDate.now())
                        .email("ajay30935@gmail.com")
                        .urlPath("/api/v1/service/nbfc-registration")
                        .status(StatusTypeEnum.ACTIVE)
                        .build()
        );
    }

    @GetMapping("/new")
    public String newSubscribers() {
        return "private/subscriber-new";
    }
}
