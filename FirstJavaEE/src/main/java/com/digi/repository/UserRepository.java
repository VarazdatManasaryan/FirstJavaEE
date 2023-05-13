package com.digi.repository;

import com.digi.model.User;

public interface UserRepository {

    void save(User user);

    User getByUsername(String email);

    void updateUser(User user);

    void changePassword(String email, String password);

    void deleteAccount(User user);
}