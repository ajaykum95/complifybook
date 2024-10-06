package com.abhaempire.complifybook.configs.security;

import com.abhaempire.complifybook.models.User;
import com.abhaempire.complifybook.enums.StatusTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

    @Getter
    private final Integer id;

    private final String username;

    @Getter
    private final String email;

    private final String firstName;

    @JsonIgnore
    private String password;

    private final Boolean isEmailVerified;

    private final Boolean isActive;

    private final Boolean isDeleted;

    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Integer id, String username, String email,String firstName, String password,
                                  Collection<? extends GrantedAuthority> authorities,
                           Boolean isEmailVerified,Boolean isActive,Boolean isDeleted) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.password = password;
        this.authorities = authorities;
        this.isEmailVerified = isEmailVerified;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getUserRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getEmail(),
                user.getEmail(),
                user.getFirstName(),
                user.getPassword(),
                authorities,
                user.isEmailVerified(),
                StatusTypeEnum.ACTIVE.equals(user.getStatus()),
                StatusTypeEnum.DELETED.equals(user.getStatus()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.isDeleted;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isEmailVerified;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isActive;
    }
}
