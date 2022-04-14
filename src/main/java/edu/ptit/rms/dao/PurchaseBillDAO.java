package edu.ptit.rms.dao;

import edu.ptit.rms.model.PurchaseBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseBillDAO extends JpaRepository<PurchaseBill, Integer> {

}
