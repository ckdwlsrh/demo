package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class StockEntity {
    private int id;
    private String stockcode;
    private String stockdate;
    private String stockname;
    private int stockidx;
    private int highprice;
    private int lowprice;
    private int startprice;
    private int endprice;
    private int fprice;
    private String frate;
}
