package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.*;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final UserService userService;
    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialService credentialService;
    private final EncryptionService encryptionService;

    public HomeController(UserService userService,
                          FileService fileService,
                          NoteService noteService,
                          CredentialService credentialService,
                          EncryptionService encryptionService) {
        this.userService = userService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @GetMapping()
    public String homeView(Authentication authentication, Model model) {
        Integer userId = userService.getUser(authentication.getName()).getUserId();

        model.addAttribute("userFiles", fileService.getFilesByUserId(userId));
        model.addAttribute("userNotes", noteService.getNotesByUserId(userId));
        model.addAttribute("userCredentials", credentialService.getCredentialsByUserId(userId));
        model.addAttribute("encryptionService",encryptionService);

        return "home";
    }
}
