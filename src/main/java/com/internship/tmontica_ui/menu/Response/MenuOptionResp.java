package com.internship.tmontica_ui.menu.Response;

import lombok.Data;

@Data
public class MenuOptionResp {
    private int id;
    private String name;
    private int price;
    private String type;
    private int quantity = 0;

}
