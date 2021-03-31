package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.model.UserNote;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class NoteController {
    private final NoteService noteService;
    private final UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping("/note/save")
    public String saveNote(@ModelAttribute UserNote userNote,
                           Authentication authentication,
                           RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("activeTab", "notes");

        Integer userId = userService.getUser(authentication.getName()).getUserId();
        userNote.setUserId(userId);

        try {
            if (userNote.getNoteId() == null) {
                noteService.saveNote(userNote);
            } else {
                noteService.updateNote(userNote);
            }
            redirectAttributes.addFlashAttribute("success", true);
            redirectAttributes.addFlashAttribute("message", "Note saved successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", true);
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/home";
    }

    @GetMapping("/note/delete/{noteId}")
    public String deleteNote(@PathVariable("noteId") Integer noteId,
                             Authentication authentication,
                             RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("activeTab", "notes");

        Integer userId = userService.getUser(authentication.getName()).getUserId();

        try {
            noteService.deleteNoteByNoteIdAndUserId(noteId, userId);
            redirectAttributes.addFlashAttribute("success", true);
            redirectAttributes.addFlashAttribute("message", "Note deleted successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", true);
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/home";
    }

}
