package com.gatech.hearthealthtracker2;

import java.time.LocalDateTime;

public class ProfileDto {
    private Integer height;
    private String name;
    private Integer age;
    private String guid;

    public ProfileDto() {
    }

    public ProfileDto(String name, Integer height, Integer age, String guid) {
        this.height = height;
        this.name = name;
        this.age = age;
        this.guid = guid;
    }

    public Integer getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getGuid() {
        return guid;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
}
