package com.ducthang.ManagerUsers.ultil;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = GenderDeserializer.class)
public enum Gender {
    MALE, FEMALE;
}
