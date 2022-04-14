package edu.ptit.rms.dao;

import edu.ptit.rms.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public interface SupplierStatisticDAO extends JpaRepository<Supplier, Integer> {

  /**
   * Get supplier statistic by date
   *
   * @param beginDate
   * @param endDate
   * @return List SupplierStatistic
   */
  @Nullable
  @Query(
      value =
          "select new map(s.id as id"
              + ", s.name as name"
              + ", sum(pd.quantity) as itemAmount"
              + ", sum(pd.quantity * pd.unitPrice) as purchaseValue) "
              + "from Supplier s "
              + "inner join PurchaseBill p on p.deliveredBy = s.id "
              + "inner join PurchaseBillDetail pd on pd.wasIn = p.id "
              + "where (p.dateCreate) between (:beginDate) and (:endDate) "
              + "group by s.id")
  List<HashMap<String, Object>> getSupplierStatisticByDate(
      @Param("beginDate") Date beginDate, @Param("endDate") Date endDate);

  /**
   * Get supplier statistic all the time
   *
   * @return List SupplierStatistic
   */
  @Nullable
  @Query(
      value =
          "select new map(s.id as id "
              + ", s.name as name"
              + ", sum(pd.quantity) as itemAmount "
              + ", sum(pd.quantity * pd.unitPrice) as purchaseValue) "
              + "from Supplier s "
              + "inner join PurchaseBill p on p.deliveredBy = s.id "
              + "inner join PurchaseBillDetail pd on pd.wasIn = p.id "
              + "group by s.id")
  List<HashMap<String, Object>> getAllSupplierStatistic();
}
