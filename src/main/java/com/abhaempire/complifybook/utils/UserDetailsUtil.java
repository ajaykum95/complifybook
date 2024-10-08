package com.abhaempire.complifybook.utils;

import com.abhaempire.complifybook.configs.security.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserDetailsUtil {
    public static UserDetailsImpl getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            return (UserDetailsImpl) authentication.getPrincipal();
        }catch (Exception e){
            return null;
        }
    }
}
