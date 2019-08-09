package com.internship.tmontica_ui.order.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderListResp {
    private int orderId;
    private Date orderDate;
    private String status;
    private List<String> menuNames;
}
