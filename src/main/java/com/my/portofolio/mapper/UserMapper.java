package com.my.portofolio.mapper;

import com.my.portofolio.dto.user.UserCreateRequest;
import com.my.portofolio.dto.user.UserResponse;
import com.my.portofolio.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserCreateRequest userCreateRequest);

    @Mapping(source = "role", target = "roleUser")
    UserResponse toResponse(User user);
}
