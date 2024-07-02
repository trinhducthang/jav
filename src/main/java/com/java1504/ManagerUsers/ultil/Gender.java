package com.java1504.ManagerUsers.ultil;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = GenderDeserializer.class)
public enum Gender {
    MALE, FEMALE;
}
