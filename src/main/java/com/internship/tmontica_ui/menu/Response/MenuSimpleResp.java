package com.internship.tmontica_ui.menu.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuSimpleResp {
    private int id;
    private String nameEng;
    private String nameKo;
    private String imgUrl;
    private int stock;

}
