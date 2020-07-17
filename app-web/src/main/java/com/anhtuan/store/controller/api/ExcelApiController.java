package com.anhtuan.store.controller.api;

import com.anhtuan.store.commons.enums.OrderStatus;
import com.anhtuan.store.commons.utils.DateTimeUtils;
import com.anhtuan.store.dto.request.SearchOrderDto;
import com.anhtuan.store.dto.response.OrderResponseDto;
import com.anhtuan.store.excel_util.ExcelGenerator;
import com.anhtuan.store.service.OrderService;
import com.anhtuan.store.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin/api/report")
public class ExcelApiController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private StatisticsService statisticsService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        // change the format according to your need.
        dateFormat.setLenient(false);

        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping(value = "/download/revenue.xlsx")
    public ResponseEntity<InputStreamResource> excelCustomersReport(@RequestParam(value = "fromDate", required = false) Date fromDate, @RequestParam(value = "endDate", required = false) Date endDate) throws IOException, ParseException {
        Date fromDateN = fromDate;
        Date endDateN = endDate;
        if (fromDate == null) fromDateN = DateTimeUtils.defaultDate();
        if (endDate == null) endDateN = DateTimeUtils.addTime(new Date(), 23, 59, 59);
        ByteArrayInputStream in = ExcelGenerator.revenueToExcel(statisticsService.getDataReportRevenue(fromDate, endDate), fromDateN, endDateN);
        // return IOUtils.toByteArray(in);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=Doanhthu.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

    @GetMapping(value = "/download/order.xlsx/{id}")
    public ResponseEntity<InputStreamResource> excelCustomersReport(@PathVariable Integer id) throws IOException, ParseException {

        ByteArrayInputStream in = ExcelGenerator.orderToExcel(statisticsService.getDataReportOrder(id));
        // return IOUtils.toByteArray(in);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=hoadon.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }
}
