package com.internship.tmontica_ui.user.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class UserFindIdRespDTO {

    private List<String> userIdList;
}
