package com.jumio.notificationSystem.service;

import com.jumio.notificationSystem.dto.CreateUserRequest;
import com.jumio.notificationSystem.entity.User;
import com.jumio.notificationSystem.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        return userRepository.save(user);
    }
}
