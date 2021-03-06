package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String signupView() {
        // Only show the page when the user is not logged in.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "signup";
        }

        // If the user is logged in, redirect to /home
        return "redirect:/home";
    }

    @PostMapping()
    public String signupUser(@ModelAttribute User user,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        String signupError = null;

        if (!userService.isUsernameAvailable(user.getUsername())) {
            signupError = "The username already exists.";
        } else {
            int rowsAdded = userService.createUser(user);
            if (rowsAdded > 0) {
                //Signup Success
                redirectAttributes.addFlashAttribute("signupSuccess", true);
                return "redirect:/login";
            } else {
                signupError = "There was an error signing you up. Please try again.";
            }
        }

        if (signupError != null) {
            model.addAttribute("signupError", signupError);
        }

        return "signup";
    }

}
