package edu.ptit.rms.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(
    name = "tbl_ingredient_type",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class IngredientType {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "name", nullable = false, length = 30)
  private String name;

  @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
  @ToString.Exclude
  @JsonBackReference
  private List<Ingredient> ingredients;
}
