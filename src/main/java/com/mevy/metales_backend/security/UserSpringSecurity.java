package com.mevy.metales_backend.security;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mevy.metales_backend.entities.enums.ProfileEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserSpringSecurity implements UserDetails {

    private Long id;
    // Email
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSpringSecurity(
        Long id,
        String username,
        String password,
        Set<ProfileEnum> authorities
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities.stream()
                                        .map(x -> new SimpleGrantedAuthority(x.getDescription()))
                                        .toList();
    }
    
    public boolean hasRole(ProfileEnum profileEnum) {
        return getAuthorities().contains(new SimpleGrantedAuthority(profileEnum.getDescription()));
    }

}
