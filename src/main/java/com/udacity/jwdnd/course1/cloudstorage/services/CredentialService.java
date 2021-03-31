package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.UserCredential;
import com.udacity.jwdnd.course1.cloudstorage.model.UserNote;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;
    private final SaltService saltService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService, SaltService saltService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
        this.saltService = saltService;
    }

    public List<UserCredential> getCredentialsByUserId(Integer userId) {
        return credentialMapper.getCredentialsByUserId(userId);
    }

    public UserCredential getCredentialByCredentialId(Integer credentialId) {
        return credentialMapper.getCredentialByCredentialId(credentialId);
    }

    public int saveCredential(UserCredential credential) {
        String encodedSalt = saltService.getSalt();
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedSalt);
        return credentialMapper.saveCredential(new UserCredential(null,
                credential.getUrl(), credential.getUsername(),
                encodedSalt, encryptedPassword,
                credential.getUserId()));
    }

    public void updateCredential(UserCredential credential) {
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), credential.getKey());
        credential.setPassword(encryptedPassword);
        credentialMapper.updateCredential(credential);
    }

    public void deleteCredentialByCredentialId(Integer credentialId) {
        credentialMapper.deleteCredentialByCredentialId(credentialId);
    }

    public void deleteCredentialByCredentialIdAndUserId(Integer credentialId, Integer userId) {
        credentialMapper.deleteCredentialByCredentialIdAndUserId(credentialId, userId);
    }


}
