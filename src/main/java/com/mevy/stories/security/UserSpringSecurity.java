package com.mevy.stories.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mevy.stories.entities.enums.ProfileEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserSpringSecurity implements UserDetails{

    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSpringSecurity(Long id, String username, String password,
            Collection<ProfileEnum> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities.stream().map(role -> new SimpleGrantedAuthority(role.getDescription())).toList();
    }

    public boolean hasRole(ProfileEnum profileEnum) {
        return this.getAuthorities().contains(new SimpleGrantedAuthority(profileEnum.getDescription()));
    }
    
}
