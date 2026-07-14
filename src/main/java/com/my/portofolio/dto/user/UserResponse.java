package com.my.portofolio.dto.user;

import com.my.portofolio.model.enums.RoleUser;
import lombok.*;

@AllArgsConstructor
@Data
@Builder
public class UserResponse {

    private final Long id;
    private final String email;
    private final String password;
    private final RoleUser roleUser;

}
