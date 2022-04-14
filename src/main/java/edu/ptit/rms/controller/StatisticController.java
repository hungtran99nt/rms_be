package edu.ptit.rms.controller;

import edu.ptit.rms.common.BaseConstants;
import edu.ptit.rms.dao.PurchaseBillStatisticDAO;
import edu.ptit.rms.dao.SupplierStatisticDAO;
import edu.ptit.rms.model.Period;
import edu.ptit.rms.model.PurchaseBillStatistic;
import edu.ptit.rms.model.SupplierStatistic;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/statistic")
public class StatisticController {

  private static final Logger logger = LoggerFactory.getLogger(StatisticController.class);

  private final SupplierStatisticDAO supplierStatisticDAO;
  private final PurchaseBillStatisticDAO purchaseBillStatisticDAO;
  private final ModelMapper modelMapper;

  @Autowired
  public StatisticController(
      SupplierStatisticDAO supplierStatisticDAO,
      PurchaseBillStatisticDAO purchaseBillStatisticDAO,
      ModelMapper modelMapper) {
    this.supplierStatisticDAO = supplierStatisticDAO;
    this.purchaseBillStatisticDAO = purchaseBillStatisticDAO;
    this.modelMapper = modelMapper;
  }

  //  /**
  //   * Get all supplier statistic
  //   *
  //   * @return List SupplierStatistic
  //   */
  //  @GetMapping("/supplier/all")
  //  @ApiOperation("Get all supplier statistic....")
  //  public List<SupplierStatistic> getAllSupplierStatistic() {
  //    return supplierStatisticDAO.getAllSupplierStatistic().stream()
  //        .map(statistic -> modelMapper.map(statistic, SupplierStatistic.class))
  //        .collect(Collectors.toList());
  //  }

  /**
   * Get supplier statistic by date
   *
   * @param period
   * @return List SupplierStatistic
   * @throws Exception
   */
  @PostMapping("/supplier")
  @ApiOperation("Get supplier statistic by date....")
  public List<SupplierStatistic> getSupplierStatisticByDate(@RequestBody Period period)
      throws Exception {

    if ((period.getBeginDate() == null)
        && (period.getEndDate() != null)) {
      return supplierStatisticDAO.getAllSupplierStatistic().stream()
              .map(statistic -> modelMapper.map(statistic, SupplierStatistic.class))
              .collect(Collectors.toList());
    }
    if ((period.getEndDate() == null)
        && (period.getBeginDate() != null)) {
      return supplierStatisticDAO.getAllSupplierStatistic().stream()
              .map(statistic -> modelMapper.map(statistic, SupplierStatistic.class))
              .collect(Collectors.toList());
    }
    if (period.getBeginDate() == null && period.getEndDate() == null) {
      return supplierStatisticDAO.getAllSupplierStatistic().stream()
          .map(statistic -> modelMapper.map(statistic, SupplierStatistic.class))
          .collect(Collectors.toList());
    }

//      Date beginDateConverted =
//          new SimpleDateFormat(BaseConstants.PATTERN_DATE_yMd_FORMAT).parse(period.getBeginDate());
//      Date endDateConverted =
//          new SimpleDateFormat(BaseConstants.PATTERN_DATE_yMd_FORMAT).parse(period.getEndDate());

    return supplierStatisticDAO
        .getSupplierStatisticByDate(period.getBeginDate(), period.getEndDate())
        .stream()
        .map(statistic -> modelMapper.map(statistic, SupplierStatistic.class))
        .collect(Collectors.toList());
  }

  //  /**
  //   * Get all purchase bill statistic
  //   *
  //   * @return List PurchaseBillStatistic
  //   */
  //  @GetMapping("/purchaseBill/{supID}/all")
  //  @ApiOperation("Get all supplier statistic....")
  //  public List<PurchaseBillStatistic> getAllPurchaseBillStatistic(
  //      @PathVariable(name = "supID") int supID) {
  //
  //    return purchaseBillStatisticDAO.getAllPurchaseBillStatistic(supID).stream()
  //        .map(statistic -> modelMapper.map(statistic, PurchaseBillStatistic.class))
  //        .collect(Collectors.toList());
  //  }

  /**
   * Get purchase bill statistic by date
   *
   * @param period
   * @return List PurchaseBillStatistic
   * @throws Exception
   */
  @PostMapping("/purchaseBill/{supID}")
  @ApiOperation("Get supplier statistic by date....")
  public List<PurchaseBillStatistic> getPurchaseBillStatisticByDate(
      @PathVariable(name = "supID") int supID, @RequestBody Period period) throws Exception {

    if ((period.getBeginDate() == null)
            && (period.getEndDate() != null)) {
      return purchaseBillStatisticDAO.getAllPurchaseBillStatistic(supID).stream()
              .map(statistic -> modelMapper.map(statistic, PurchaseBillStatistic.class))
              .collect(Collectors.toList());
    }
    if ((period.getEndDate() == null)
            && period.getBeginDate() != null) {
      return purchaseBillStatisticDAO.getAllPurchaseBillStatistic(supID).stream()
              .map(statistic -> modelMapper.map(statistic, PurchaseBillStatistic.class))
              .collect(Collectors.toList());
    }
    if (period.getBeginDate() == null && period.getEndDate() == null) {
      return purchaseBillStatisticDAO.getAllPurchaseBillStatistic(supID).stream()
          .map(statistic -> modelMapper.map(statistic, PurchaseBillStatistic.class))
          .collect(Collectors.toList());
    }

//      Date beginDateConverted =
//          new SimpleDateFormat(BaseConstants.PATTERN_DATE_yMd_FORMAT).parse(period.getBeginDate());
//      Date endDateConverted =
//          new SimpleDateFormat(BaseConstants.PATTERN_DATE_yMd_FORMAT).parse(period.getEndDate());

    return purchaseBillStatisticDAO
        .getPurchaseBillStatisticByDate(supID, period.getBeginDate(), period.getEndDate())
        .stream()
        .map(statistic -> modelMapper.map(statistic, PurchaseBillStatistic.class))
        .collect(Collectors.toList());

  }
}
