package edu.ptit.rms.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_purchase_bill_detail", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class PurchaseBillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "unit_price", nullable = false)
    private float unitPrice;

    @Column(name = "quantity", nullable = false)
    private float quantity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "was_in", nullable = false)
    @JsonBackReference
    private PurchaseBill wasIn;

    @ManyToOne(optional = false)
    @JoinColumn(name = "has_ingredient", nullable = false)
    @JsonManagedReference
    private Ingredient hasIngredient;
}