package edu.ptit.rms.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_supplier", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Supplier {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Column(name = "des")
  private String des;

  @Column(name = "address")
  private String address;

  @Column(name = "email", nullable = false, length = 50)
  private String email;

  @Column(name = "phone_number", length = 15)
  private String phoneNumber;

  @OneToMany(mappedBy = "deliveredBy", cascade = CascadeType.ALL)
  @ToString.Exclude
  @JsonBackReference
  private List<PurchaseBill> purchaseBills;
}
