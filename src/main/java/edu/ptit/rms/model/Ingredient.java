package edu.ptit.rms.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(
    name = "tbl_ingredient",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Ingredient {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @Column(name = "name", nullable = false, length = 30)
  private String name;

  @Column(name = "des", length = 100)
  private String des;

  @ManyToOne(optional = false)
  @JoinColumn(name = "has_unit", nullable = false)
  @JsonManagedReference
  private IngredientUnit unit;

  @ManyToOne(optional = false)
  @JoinColumn(name = "has_type", nullable = false)
  @JsonManagedReference
  private IngredientType type;

  @OneToMany(mappedBy = "hasIngredient", cascade = CascadeType.ALL)
  @ToString.Exclude
  @JsonBackReference
  private List<PurchaseBillDetail> purchaseBillDetails;
}
