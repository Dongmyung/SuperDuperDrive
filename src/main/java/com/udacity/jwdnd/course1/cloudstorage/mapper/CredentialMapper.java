package com.udacity.jwdnd.course1.cloudstorage.mapper;


import com.udacity.jwdnd.course1.cloudstorage.model.UserCredential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    List<UserCredential> getCredentialsByUserId(Integer userId);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    UserCredential getCredentialByCredentialId(Integer credentialId);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES(#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId", keyColumn = "credentialid")
    int saveCredential(UserCredential credential);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, password = #{password} WHERE credentialid = #{credentialId} AND userid = #{userId}")
    void updateCredential(UserCredential credential);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    void deleteCredentialByCredentialId(Integer credentialId);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId} AND userid = #{userId}")
    void deleteCredentialByCredentialIdAndUserId(Integer credentialId, Integer userId);
}
