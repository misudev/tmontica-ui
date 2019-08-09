package com.internship.tmontica_ui.user.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class UserChangePasswordDTO {

    private String id;
    private String password;
}
