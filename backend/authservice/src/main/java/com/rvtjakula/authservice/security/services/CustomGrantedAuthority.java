package com.rvtjakula.authservice.security.services;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rvtjakula.authservice.models.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
@JsonDeserialize
@Getter
@Setter
@NoArgsConstructor
public class CustomGrantedAuthority implements GrantedAuthority  {
    private String authority;

    public CustomGrantedAuthority(Role role) {
        this.authority = role.getValue();
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
