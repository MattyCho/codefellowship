package com.codefellows.codefellowship.controllers;

import com.codefellows.codefellowship.models.ApplicationUser;
import com.codefellows.codefellowship.models.UserPost;
import com.codefellows.codefellowship.repositories.ApplicationUserRepository;
import com.codefellows.codefellowship.repositories.UserPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    UserPostRepository userPostRepository;

    @GetMapping("/")
    public String getHomePage(Principal p, Model m) {
        if (p != null) {
            String username = p.getName();
            ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);

            m.addAttribute("username", username);
        }
        return "index.html";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login.html";
    }

    @GetMapping("/signup")
    public String getSignupPage() {
        return "signup.html";
    }

    @GetMapping("/test")
    public String getTestPage() {
        return "test.html";
    }

    @PostMapping("/signup")
    public RedirectView createUser(String username, String password, String firstName, String lastName, String dateOfBirth, String bio) {
        ApplicationUser newApplicationUser = new ApplicationUser();
        newApplicationUser.setUsername(username);
        String hashedPassword = passwordEncoder.encode(password);
        newApplicationUser.setPassword(hashedPassword);
        newApplicationUser.setFirstName(firstName);
        newApplicationUser.setLastName(lastName);
        newApplicationUser.setDateOfBirth(dateOfBirth);
        newApplicationUser.setBio(bio);

        applicationUserRepository.save(newApplicationUser);

        authWithHttpServlet(username, password);
        return new RedirectView("/");
    }

    public void authWithHttpServlet (String username, String password)
    {
        try
        {
            request.login(username, password);
        } catch (ServletException se)
        {
            System.out.println("Error when logging in: ");
            se.printStackTrace();
        }
    }

    @GetMapping("/myprofile")
    public String getCurrentUserInfo(Model m, Principal p) {
        if (p != null) {
            String username = p.getName();
            ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
            m.addAttribute("username", username);
            m.addAttribute("usersUsername", applicationUser.getUsername());
            m.addAttribute("usersFirstName", applicationUser.getFirstName());
            m.addAttribute("usersLastName", applicationUser.getLastName());
            m.addAttribute("usersDateOfBirth", applicationUser.getDateOfBirth());
            m.addAttribute("usersBio", applicationUser.getBio());
            m.addAttribute("usersPostList", applicationUser.getUserPostList());
        }
        return "user-info.html";
    }

    @GetMapping("/users/{id}")
    public String getUserInfo(Model m, Principal p, @PathVariable Long id) {
        if (p != null) {
            String username = p.getName();
            ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
            m.addAttribute("username", username);
        }

        ApplicationUser applicationUser = applicationUserRepository.findById(id).orElseThrow();
        m.addAttribute("applicationUser", applicationUser);
        m.addAttribute("usersUsername", applicationUser.getUsername());
        m.addAttribute("usersFirstName", applicationUser.getFirstName());
        m.addAttribute("usersLastName", applicationUser.getLastName());
        m.addAttribute("usersDateOfBirth", applicationUser.getDateOfBirth());
        m.addAttribute("usersBio", applicationUser.getBio());
        m.addAttribute("usersPostList", applicationUser.getUserPostList());
        return "user-info.html";
    }

    @PostMapping("/createPost")
    public RedirectView addPostToUser(Principal p, String postBody) {
        if (p != null) {
            String username = p.getName();
            ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
            UserPost newPost = new UserPost(postBody, applicationUser);
            userPostRepository.save(newPost);
        }
        return new RedirectView("/myprofile");
    }
}
