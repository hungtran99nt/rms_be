package edu.ptit.rms.dao;

import edu.ptit.rms.model.PurchaseBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public interface PurchaseBillStatisticDAO extends JpaRepository<PurchaseBill, Integer> {

    /**
     * Get Purchase Bill Statistic by date
     * @param beginDate
     * @param endDate
     * @return List Purchase Bill Statistic
     */
    @Query(
            value = "select new map(p.id as billID"
                    + ", s.name as supplierName"
                    + ", p.dateCreate as dateCreate"
                    + ", sum(pd.quantity) as itemAmount"
                    + ", sum(pd.quantity * pd.unitPrice) as purchaseValue) "
                    + "from Supplier s "
                    + "inner join PurchaseBill p on p.deliveredBy = s.id "
                    + "inner join PurchaseBillDetail pd on pd.wasIn = p.id "
                    + "where (p.dateCreate) between (:beginDate) and (:endDate) "
                    + "and p.deliveredBy.id = (:supID) "
                    + "group by p.id"
    )
  List<HashMap<String, Object>> getPurchaseBillStatisticByDate(
      @Param("supID") int supID, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate);

    /**
     * Get Purchase Bill Statistic all the time
     * @return List Purchase Bill Statistic
     */
    @Query(
            value = "select new map(p.id as billID"
                    + ", s.name as supplierName"
                    + ", p.dateCreate as dateCreate"
                    + ", sum(pd.quantity) as itemAmount"
                    + ", round(sum(pd.quantity * pd.unitPrice), 2) as purchaseValue) "
                    + "from Supplier s "
                    + "inner join PurchaseBill p on p.deliveredBy = s.id "
                    + "inner join PurchaseBillDetail pd on pd.wasIn = p.id "
                    + "and p.deliveredBy.id = (:supID) "
                    + "group by p.id"
    )
    List<HashMap<String, Object>> getAllPurchaseBillStatistic(@Param("supID") int supID);
}
