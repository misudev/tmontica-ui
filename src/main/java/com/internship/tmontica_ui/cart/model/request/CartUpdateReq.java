package com.internship.tmontica_ui.cart.model.request;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class CartUpdateReq {

    @Min(1)
    private int quantity;
}
