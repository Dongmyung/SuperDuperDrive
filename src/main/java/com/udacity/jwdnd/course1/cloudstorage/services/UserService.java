package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final HashService hashService;
    private final SaltService saltService;

    public UserService(UserMapper userMapper, HashService hashService, SaltService saltService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
        this.saltService = saltService;
    }

    public boolean isUsernameAvailable(String username) {
        return userMapper.getUserByUsername(username) == null;
    }

    public User getUser(String username) {
        return userMapper.getUserByUsername(username);
    }

    public int createUser(User user) {
        String encodedSalt = saltService.getSalt();
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        return userMapper.saveUser(new User(null, user.getUsername(), encodedSalt,
                hashedPassword, user.getFirstName(), user.getLastName()));
    }
}
