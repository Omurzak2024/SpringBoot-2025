package peaksoft.springboot2025.service;

import peaksoft.springboot2025.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    void updateUserById(Long oldUser, User newUser);
    void deleteUserById(Long id);
    User getUserByEmail(String email);
}
