package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.UserNote;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public List<UserNote> getNotesByUserId(Integer userId) {
        return noteMapper.getNotesByUserId(userId);
    }

    public UserNote getNoteByNoteId(Integer noteId) {
        return noteMapper.getNoteByNoteId(noteId);
    }

    public int saveNote(UserNote note) {
        return noteMapper.saveNote(note);
    }

    public void updateNote(UserNote note) {
        noteMapper.updateNote(note);
    }

    public void deleteNoteByNoteId(Integer noteId) {
        noteMapper.deleteNoteByNoteId(noteId);
    }

    public void deleteNoteByNoteIdAndUserId(Integer noteId, Integer userId) {
        noteMapper.deleteNoteByNoteIdAndUserId(noteId, userId);
    }

}
