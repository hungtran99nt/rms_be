package edu.ptit.rms.model;

import lombok.Data;

import java.util.Date;

@Data
public class PurchaseBillStatistic extends PurchaseBill{
//    private int billID;
//    private String supplierName;
//    private Date dateCreate;
    private double purchaseValue;
    private float itemAmount;
}
