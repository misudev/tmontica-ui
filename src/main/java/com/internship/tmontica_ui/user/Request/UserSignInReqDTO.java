package com.internship.tmontica_ui.user.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class UserSignInReqDTO{

    @NotNull
    private String id;
    @NotNull
    private String password;
}
