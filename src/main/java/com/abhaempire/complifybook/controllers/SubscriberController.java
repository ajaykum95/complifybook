package com.abhaempire.complifybook.controllers;

import com.abhaempire.complifybook.dtos.Message;
import com.abhaempire.complifybook.dtos.SubscriberResponse;
import com.abhaempire.complifybook.enums.StatusTypeEnum;
import com.abhaempire.complifybook.exception.AbhaBaseRunTimeException;
import com.abhaempire.complifybook.services.SubscriptionService;
import com.abhaempire.complifybook.utils.RequestValidator;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1/subscriber")
public class SubscriberController {

    private final SubscriptionService subscriptionService;

    public SubscriberController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping()
    public String subscribers() {
        return "private/subscribers";
    }

    @GetMapping("/fetchAll")
    @ResponseBody
    public List<SubscriberResponse> allSubscribers() {
        return subscriptionService.fetchAllSubscriber();
    }

    @GetMapping("/delete/{subscriberId}")
    public String deleteSubscriber(@PathVariable Integer subscriberId, Model model) {
        try{
            RequestValidator.validateId(subscriberId);
            subscriptionService.deleteSubscriber(subscriberId);
            return "redirect:/api/v1/subscriber";
        }catch (AbhaBaseRunTimeException e){
            model.addAttribute("formMessage",
                    new Message("alert-danger", e.getMessage()));
            return "public/404";
        }
    }
}
