package com.anhtuan.store.excel_util;

import com.anhtuan.store.commons.utils.DateTimeUtils;
import com.anhtuan.store.dto.response.OrderItemResponseDto;
import com.anhtuan.store.dto.response.OrderReportDto;
import com.anhtuan.store.dto.response.ReportRevenueDto;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ExcelGenerator {
    public static ByteArrayInputStream revenueToExcel(List<ReportRevenueDto> dataReport, Date from, Date to) throws IOException {
        String[] columns = {"STT", "Mã hóa đơn", "Mã sản phẩm", "Tên sản phẩm", "Giá bán", "Tên Size", "Giá size", "Số lượng", "Tổng tiền"};
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ) {
            Sheet sheet = workbook.createSheet("revenue");

            Header header = sheet.getHeader();
            header.setCenter("Báo cáo tổng hợp bán hàng theo hóa đơn bán hàng ");
            header.setLeft("left");
            header.setRight("right");

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
                sheet.autoSizeColumn(col);
            }


            int rowIdx = 1;
            int totalAmount = 0;
            int totalQuantity = 0;
            for (ReportRevenueDto report : dataReport) {
                int index = rowIdx++;
                Row row = sheet.createRow(index);

                row.createCell(0).setCellValue(index);
                row.createCell(1).setCellValue(report.getOrderId());
                row.createCell(2).setCellValue(report.getProductId());
                row.createCell(3).setCellValue(report.getProductName());
                row.createCell(4).setCellValue(report.getSoldPrice());
                row.createCell(5).setCellValue(report.getSizeName());
                row.createCell(6).setCellValue(report.getSizePrice());
                row.createCell(7).setCellValue(report.getQuantity());

                row.createCell(8).setCellValue(report.getAmount());
                totalAmount += Integer.parseInt(report.getAmount().replace(",", ""));
                totalQuantity += report.getQuantity();
            }
            sheet.createRow(rowIdx++);
            Row total = sheet.createRow(rowIdx++);
            total.createCell(1).setCellValue("Tổng doanh thu từ " + DateTimeUtils.convertToString(from) + "-" + DateTimeUtils.convertToString(to));
            total.createCell(7).setCellValue(totalQuantity);
            total.createCell(8).setCellValue(String.format("%,d", totalAmount));

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    public static ByteArrayInputStream orderToExcel(List<OrderReportDto> dtos) throws IOException {
        String[] columns = {"STT", "Mã hóa đơn", "Mã sản phẩm", "Tên sản phẩm", "Giá bán", "Tên Size", "Số lượng"};
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ) {
            Sheet sheet = workbook.createSheet("order");

            Header header = sheet.getHeader();
            header.setCenter("Báo cáo tổng hợp bán hàng theo hóa đơn bán hàng ");
            header.setLeft("left");
            header.setRight("right");

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
                sheet.autoSizeColumn(col);
            }


            int rowIdx = 1;
            int totalAmount = 0;
            int totalQuantity = 0;
            for (OrderReportDto report : dtos) {
                int index = rowIdx++;
                Row row = sheet.createRow(index);

                row.createCell(0).setCellValue(index);
                row.createCell(1).setCellValue(report.getOrderId());
                row.createCell(2).setCellValue(report.getProductId());
                row.createCell(3).setCellValue(report.getProductName());
                row.createCell(4).setCellValue(report.getSalePrice());
                row.createCell(5).setCellValue(report.getSizeName());
                row.createCell(6).setCellValue(report.getQuantity());

                totalAmount += Integer.parseInt(report.getAmount().replace(",", ""));
                totalQuantity += report.getQuantity();
            }
            sheet.createRow(rowIdx++);
            Row total = sheet.createRow(rowIdx++);
            total.createCell(1).setCellValue("Tổng tiền hóa đơn");
            total.createCell(6).setCellValue(totalQuantity);
            total.createCell(7).setCellValue(String.format("%,d", totalAmount));

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}


