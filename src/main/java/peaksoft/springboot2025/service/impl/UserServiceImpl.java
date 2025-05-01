package peaksoft.springboot2025.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.springboot2025.entity.User;
import peaksoft.springboot2025.repository.UserRepository;
import peaksoft.springboot2025.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);

    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("User with id " + id + " not found")
        );
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void updateUserById(Long oldUser, User newUser) {
        User user = userRepository.findById(oldUser).orElseThrow(
                () -> new NoSuchElementException("User with id " + oldUser + " not found")
        );
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setEmail(newUser.getEmail());
        user.setAge(newUser.getAge());
        userRepository.save(user);

    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("User with id " + id + " not found")
        );
        userRepository.delete(user);

    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserWithEmail(email);
    }
}
