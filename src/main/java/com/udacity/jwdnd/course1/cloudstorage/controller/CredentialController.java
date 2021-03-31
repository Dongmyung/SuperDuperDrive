package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.UserCredential;
import com.udacity.jwdnd.course1.cloudstorage.model.UserNote;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CredentialController {

    private final CredentialService credentialService;
    private final UserService userService;

    public CredentialController(CredentialService credentialService, UserService userService) {
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @PostMapping("/credential/save")
    public String saveCredential(@ModelAttribute UserCredential userCredential,
                           Authentication authentication,
                           RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("activeTab", "credentials");

        Integer userId = userService.getUser(authentication.getName()).getUserId();
        userCredential.setUserId(userId);

        try {
            if (userCredential.getCredentialId() == null) {
                credentialService.saveCredential(userCredential);
            } else {
                credentialService.updateCredential(userCredential);
            }
            redirectAttributes.addFlashAttribute("success", true);
            redirectAttributes.addFlashAttribute("message", "Credential saved successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", true);
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/home";
    }

    @GetMapping("/credential/delete/{credentialId}")
    public String deleteCredential(@PathVariable("credentialId") Integer credentialId,
                             Authentication authentication,
                             RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("activeTab", "credentials");

        Integer userId = userService.getUser(authentication.getName()).getUserId();

        try {
            credentialService.deleteCredentialByCredentialIdAndUserId(credentialId, userId);
            redirectAttributes.addFlashAttribute("success", true);
            redirectAttributes.addFlashAttribute("message", "Credential deleted successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", true);
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/home";
    }

}
