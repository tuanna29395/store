package com.anhtuan.store.excel_util;

import com.anhtuan.store.dto.response.OrderResponseDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelGenerator {
    public static ByteArrayInputStream revenueToExcel(List<OrderResponseDto> orderResponseDtos) throws IOException {
        String[] columns = {"STT", "CODE", "TOTAL PRICE"};
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ) {

            Sheet sheet = workbook.createSheet("revenue");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < columns.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(columns[col]);
                cell.setCellStyle(headerCellStyle);
            }


            int rowIdx = 1;
            for (OrderResponseDto order : orderResponseDtos) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(rowIdx++);
                row.createCell(1).setCellValue(order.getId());
                row.createCell(2).setCellValue(order.getTotalPrice());

            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}

