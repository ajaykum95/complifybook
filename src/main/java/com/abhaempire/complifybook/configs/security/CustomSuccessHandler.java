package com.abhaempire.complifybook.configs.security;

import com.abhaempire.complifybook.enums.AbhaException;
import com.abhaempire.complifybook.enums.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import static com.abhaempire.complifybook.utils.ExceptionUtil.buildException;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        String redirectUrl = null;
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = Stream.of(Role.ROLE_ADMIN, Role.ROLE_SALES, Role.ROLE_SCO).map(Enum::name).toList();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (roles.contains(grantedAuthority.getAuthority())) {
                redirectUrl = "/api/v1/dashboard";
            } else {
                throw buildException(AbhaException.AUTHORITY_NOT_FOUND, grantedAuthority.getAuthority());
            }
        }

        if (redirectUrl == null) {
            throw new IllegalStateException();
        }
        new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}