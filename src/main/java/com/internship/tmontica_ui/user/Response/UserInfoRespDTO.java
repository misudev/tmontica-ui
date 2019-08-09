package com.internship.tmontica_ui.user.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserInfoRespDTO {

    private String name;
    private String id;
    private String email;
    private String birthDate;
    private int point;
}
