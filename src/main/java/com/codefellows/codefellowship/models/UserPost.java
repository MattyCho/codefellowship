package com.codefellows.codefellowship.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class UserPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    public String body;
    public LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    ApplicationUser thisUser;

    protected  UserPost() {

    }

    public UserPost(String body, ApplicationUser applicationUser) {
        this.body = body;
        thisUser  =applicationUser;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
