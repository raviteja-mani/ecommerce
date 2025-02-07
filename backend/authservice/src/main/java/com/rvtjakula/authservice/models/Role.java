package com.rvtjakula.authservice.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "roles")
@RequiredArgsConstructor
public class Role extends BaseModel {
    private String value;

    public Role(String user) {
        super();
    }
}
