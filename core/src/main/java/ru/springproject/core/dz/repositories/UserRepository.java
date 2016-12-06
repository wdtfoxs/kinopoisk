package ru.springproject.core.dz.repositories;


import ru.springproject.core.dz.entity.User;

public interface UserRepository {
    void addUser(User user);

    User getUseByUsername(String username);

    User getUserByEmail(String email);

    User getUserByVkID(Integer id);
}
