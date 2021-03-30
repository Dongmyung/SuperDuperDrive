package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.UserNote;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    List<UserNote> getNotesByUserId(Integer userId);

    @Select("SELECT * FROM NOTES WHERE noteid = #{noteId}")
    UserNote getNoteByNoteId(Integer noteId);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{title}, #{description}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId", keyColumn = "noteid")
    int saveNote(UserNote note);

    @Update("UPDATE NOTES SET notetitle = #{title}, notedescription = #{description} WHERE noteid = #{noteId}")
    void updateNote(UserNote note);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    void deleteNoteByNoteId(Integer noteId);

}
