package ru.springproject.core.dz.services;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.springproject.core.dz.entity.User;
import ru.springproject.core.dz.entity.UserRole;
import ru.springproject.core.dz.repositories.UserRepository;


@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void addUser(User user){
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        user.setUserRole(UserRole.USER);
        userRepository.addUser(user);
    }

    @Transactional
    public boolean existsUserByUsername(String username){
        return userRepository.getUseByUsername(username) == null;
    }

    @Transactional
    public boolean existsUserByEmail (String email){
        return userRepository.getUserByEmail(email) == null;
    }

    @Transactional
    public User getUserByUsername(String username){
        return userRepository.getUseByUsername(username);
    }
}
