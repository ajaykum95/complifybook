package com.abhaempire.complifybook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

//    private final UserService userService;

   /* public HomeController(UserService userService) {
        this.userService = userService;
    }*/

    @GetMapping("/")
    public String home(){
        return "public/index";
    }
    @GetMapping({"/login", "/signup"})
    public String loginSignup() {
        return "public/login-signup";
    }
    /*@PostMapping("/signup")
    public String signup(@RequestParam String firstName, @RequestParam String lastName,
                         @RequestParam String email, @RequestParam String password){
        User user = ObjectMapper.mapToSaveUser(firstName, lastName, email, password);
        userService.saveUser(user);
        return "public/login-signup";
    }*/
    @GetMapping("/contact-us")
    public String contactUs(){
        return "public/contact-us";
    }
    @GetMapping("/about-us")
    public String aboutUs(){
        return "public/about-us";
    }
    @GetMapping("/become-partner")
    public String becomePartner(){
        return "public/become-partner";
    }
    @GetMapping("/online-payment")
    public String onlinePayment(){
        return "public/online-payment";
    }
    @GetMapping("/sitemap")
    public String sitemap(){
        return "public/sitemap";
    }
    @GetMapping("/privacy-policy")
    public String privacyPolicy(){
        return "public/privacy-policy";
    }
    @GetMapping("/terms-of-service")
    public String termsOfService(){
        return "public/terms-of-service";
    }
    @GetMapping("/refund-policy")
    public String refundPolicy(){
        return "public/refund-policy";
    }
    @GetMapping("/cookies")
    public String cookies(){
        return "public/cookies";
    }
    @GetMapping("/terms-of-use")
    public String termsOfUse(){
        return "public/terms-of-use";
    }
    @GetMapping("/calculator")
    public String calculator(){
        return "public/calculator";
    }
    @GetMapping("/faq")
    public String faq(){
        return "public/faq";
    }
}