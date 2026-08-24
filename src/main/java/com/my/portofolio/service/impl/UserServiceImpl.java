package com.my.portofolio.service.impl;

import com.my.portofolio.dto.user.UserCreateRequest;
import com.my.portofolio.dto.user.UserUpdateRequest;
import com.my.portofolio.dto.user.UserResponse;
import com.my.portofolio.exception.ConflictException;
import com.my.portofolio.exception.ResourceNotFoundException;
import com.my.portofolio.mapper.UserMapper;
import com.my.portofolio.model.User;
import com.my.portofolio.model.enums.RoleUser;
import com.my.portofolio.repository.UserRepository;
import com.my.portofolio.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void createUser(UserCreateRequest request) {
        log.info("Creating user with email: {}", request.getEmail());

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ConflictException("Email already registered");
        }

        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(RoleUser.USER); // Set default role

        User savedUser = userRepository.save(user);
        log.info("User created successfully with id: {}", savedUser.getId());
    }

    @Override
    @Transactional
    public void updateUser(Long id, UserUpdateRequest request) {
        log.info("Updating user with id: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        // Check email conflict if email is changing
        if (!user.getEmail().equalsIgnoreCase(request.getEmail()) && userRepository.existsByEmailIgnoreCaseAndIdNot(request.getEmail(), id)) {
            throw new ConflictException("Email already in use");
        }

        user.setEmail(request.getEmail());
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        user.setRole(request.getRoleUser());

        userRepository.save(user);
        log.info("User updated successfully with id: {}", id);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        log.info("Deleting user with id: {}", id);

        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
        log.info("User deleted successfully with id: {}", id);
    }

    @Override
    public UserResponse getById(Long id) {
        log.info("Fetching user with id: {}", id);

        return userRepository.findById(id)
                .map(userMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    public Page<UserResponse> getAll(String search, Pageable pageable) {
        log.info("Fetching users with search: '{}', pageable: {}", search, pageable);

        Page<User> userPage;
        if (search != null && !search.trim().isEmpty()) {
            userPage = userRepository.searchUsers(search.trim(), pageable);
        } else {
            userPage = userRepository.findAll(pageable);
        }

        return userPage.map(userMapper::toResponse);
    }
}
