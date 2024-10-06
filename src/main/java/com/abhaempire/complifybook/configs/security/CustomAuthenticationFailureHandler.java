package com.abhaempire.complifybook.configs.security;

import com.abhaempire.complifybook.models.User;
import com.abhaempire.complifybook.enums.StatusTypeEnum;
import com.abhaempire.complifybook.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {

        String redirectUrl = request.getContextPath() + "/login?error";

        String email = request.getParameter("username");
        if (!StringUtils.isEmpty(email)) {
            User user = userRepository.findByEmail(email)
                    .orElse(null);
            if (Objects.isNull(user)) {
                redirectUrl = request.getContextPath() + "/login?error";
            } else if (!user.isEmailVerified()) {
                redirectUrl = request.getContextPath() + "/login?verifyEmail";
            } else if (StatusTypeEnum.INACTIVE.equals(user.getStatus())) {
                redirectUrl = request.getContextPath() + "/login?inactive";
            } else if (StatusTypeEnum.DELETED.equals(user.getStatus())) {
                redirectUrl = request.getContextPath() + "/login?deleted";
            }
        }
        response.sendRedirect(redirectUrl);
    }

}
