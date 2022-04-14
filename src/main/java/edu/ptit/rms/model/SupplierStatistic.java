package edu.ptit.rms.model;

import lombok.Data;

import javax.validation.constraints.Digits;

@Data
public class SupplierStatistic extends Supplier{
//    private int id;
//    private String name;
    @Digits(integer = 3 /*precision*/, fraction = 2 /*scale*/)
    private double purchaseValue;
    private float itemAmount;
}
