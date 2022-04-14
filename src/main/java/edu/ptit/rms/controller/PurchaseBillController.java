package edu.ptit.rms.controller;

import edu.ptit.rms.dao.PurchaseBillDAO;
import edu.ptit.rms.model.PurchaseBill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/purchaseBill")
public class PurchaseBillController {

  public static final Logger log = LoggerFactory.getLogger(PurchaseBillController.class);

  public final PurchaseBillDAO purchaseBillDAO;

  @Autowired
  public PurchaseBillController(PurchaseBillDAO purchaseBillDAO) {
    this.purchaseBillDAO = purchaseBillDAO;
  }

  @GetMapping("/{id}")
  public PurchaseBill getPurchaseBillById(@PathVariable(name = "id") Integer id) {
    return purchaseBillDAO.findById(id).orElseThrow(() -> new RuntimeException("BILL NOT FOUND"));
  }
}
