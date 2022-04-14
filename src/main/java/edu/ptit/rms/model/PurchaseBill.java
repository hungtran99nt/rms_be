package edu.ptit.rms.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_purchase_bill", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class PurchaseBill {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @Column(name = "date_create", nullable = false)
  private Date dateCreate;

  @Column(name = "des", length = 100)
  private String des;

  @ManyToOne(optional = false)
  @JoinColumn(name = "created_by", nullable = false)
  @JsonManagedReference
  private User createdBy;

  @ManyToOne(optional = false)
  @JoinColumn(name = "delivered_by", nullable = false)
  @JsonManagedReference
  private Supplier deliveredBy;

  @ManyToOne(optional = false)
  @JoinColumn(name = "stored_in", nullable = false)
  @JsonManagedReference
  private Warehouse storedIn;

  @OneToMany(mappedBy = "wasIn", cascade = CascadeType.ALL)
  @ToString.Exclude
  @JsonManagedReference
  private List<PurchaseBillDetail> purchaseBillDetails;
}
