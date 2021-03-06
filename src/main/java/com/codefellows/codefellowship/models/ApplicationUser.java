package com.codefellows.codefellowship.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String username;
    String password;
    String firstName;
    String lastName;
    String dateOfBirth;
    String bio;

    @OneToMany(mappedBy = "thisUser", fetch = FetchType.EAGER)
    List<UserPost> userPostList;

    @ManyToMany (mappedBy = "following")
    Set<ApplicationUser> followedBy;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="followedby_to_following",
            joinColumns = {@JoinColumn(name="followedBy")},
            inverseJoinColumns = {@JoinColumn(name="following")}
    )
    Set<ApplicationUser> following;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<UserPost> getUserPostList() {
        return userPostList;
    }

    public void setUserPostList(List<UserPost> userPostList) {
        this.userPostList = userPostList;
    }

    public Set<ApplicationUser> getFollowedBy() {
        return followedBy;
    }

    public Set<ApplicationUser> getFollowing() {
        return following;
    }
}
