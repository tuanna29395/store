package com.anhtuan.store.excel_util;

import com.anhtuan.store.dto.response.ReportRevenueDto;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelGenerator {
    public static ByteArrayInputStream revenueToExcel(List<ReportRevenueDto> dataReport) throws IOException {
        String[] columns = {"STT", "Order code", "Product code", "Product name", "Sold price","Size name","size price", "Quantity", "Amount" };
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
            total.createCell(1).setCellValue("Total");
            total.createCell(7).setCellValue(totalQuantity);
            total.createCell(8).setCellValue(String.format("%,d", totalAmount));

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    public static void main(String[] args) throws IOException {

        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");

        Header header = sheet.getHeader();
        header.setCenter("Center Header");
        header.setLeft("Left Header");
        header.setRight(HSSFHeader.font("Stencil-Normal", "Italic") +
                HSSFHeader.fontSize((short) 16) + "Right w/ Stencil-Normal Italic font and size 16");

        FileOutputStream fileOut = new FileOutputStream("ApacheHeaderFooter_Out.xls");
        wb.write(fileOut);
        fileOut.close();

        System.out.println("Aspose Header Footer Created.");
    }
}


