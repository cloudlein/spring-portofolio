package com.my.portofolio.service;

import com.my.portofolio.dto.user.UserCreateRequest;
import com.my.portofolio.dto.user.UserUpdateRequest;
import com.my.portofolio.dto.user.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    void createUser(UserCreateRequest request);
    void updateUser(Long id, UserUpdateRequest request);
    void deleteUser(Long id);
    UserResponse getById(Long id);
    Page<UserResponse> getAll(String search, Pageable pageable);

}
