package com.internship.tmontica_ui.order.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderMenusResp {
    private int menuId;
    private String nameEng;
    private String nameKo;
    private String option;
    private String imgUrl;
    private int quantity;
    private int price;
}


