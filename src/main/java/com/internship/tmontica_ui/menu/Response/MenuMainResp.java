package com.internship.tmontica_ui.menu.Response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuMainResp {
    private String categoryKo;
    private String categoryEng;
    private List<MenuSimpleResp> menus = new ArrayList<>();
}
