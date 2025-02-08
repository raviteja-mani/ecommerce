package com.rvtjakula.authservice.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@JsonDeserialize
@Getter
@Setter
@Entity(name = "roles")
@RequiredArgsConstructor
public class Role extends BaseModel {
    private String value;

}
