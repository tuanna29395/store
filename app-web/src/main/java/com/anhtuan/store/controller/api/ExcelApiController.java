package com.anhtuan.store.controller.api;

import com.anhtuan.store.commons.enums.OrderStatus;
import com.anhtuan.store.dto.request.SearchOrderDto;
import com.anhtuan.store.dto.response.OrderResponseDto;
import com.anhtuan.store.excel_util.ExcelGenerator;
import com.anhtuan.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ExcelApiController {
    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/download/revenue.xlsx")
    public ResponseEntity<InputStreamResource> excelCustomersReport(@RequestParam(value = "fromDate", required = false) Date fromDate, @RequestParam(value = "endDate",required = false) Date endDate) throws IOException {
        SearchOrderDto searchOrderDto = new SearchOrderDto();
        searchOrderDto.setStatus(OrderStatus.COMPLETED.getValue());
        List<OrderResponseDto> orders = (List<OrderResponseDto>) orderService.getAll(searchOrderDto);

        ByteArrayInputStream in = ExcelGenerator.revenueToExcel(orders);
        // return IOUtils.toByteArray(in);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=customers.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }
}
