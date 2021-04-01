package com.example.demo.export;


import com.example.demo.model.TicketModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

public class ExportExcel {
    private XSSFWorkbook workbook;
//    private XSSFSheet sheet;
    private List<TicketModel> tickets;

    public ExportExcel(List<TicketModel> ticketModelList) {
        this.tickets = ticketModelList;
        this.workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine(XSSFSheet sheet) {


        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        row.createCell(0).setCellValue("Ticket ID");
        row.createCell(1).setCellValue("Day of hire");
        row.createCell(2).setCellValue("Expiration Date");
        row.createCell(3).setCellValue("User ID");
        row.createCell(4).setCellValue("Status");

//        createCell(row, 0, "Ticket ID", style);
//        createCell(row, 1, "Day of hire", style);
//        createCell(row, 2, "Expiration Date", style);
//        createCell(row, 3, "User ID", style);
//        createCell(row, 4, "Status", style);
    }

    private Cell setCellStyle(Sheet sheet, Row row, int column, Object value, CellStyle style) {
        Cell cell = row.createCell(column);
        if (value instanceof Integer) {
            cell.setCellValue((int) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
        sheet.autoSizeColumn(column);
        return cell;
    }

    private void writeDataLines(XSSFSheet sheet) {

        int rowCount = 1;
//        CellStyle dateStyle = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");

        XSSFFont font = workbook.createFont();


        for (TicketModel ticket : tickets) {
            Row row = sheet.createRow(rowCount++);

            Cell cell = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            cell.setCellValue(ticket.getId());


            cell = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            cell.setCellValue(sdf.format(ticket.getDayOfHire()));

            cell = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            cell.setCellValue(sdf.format(ticket.getExpirationDate()));

            cell = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            cell.setCellValue(ticket.getIdUser());

            cell = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            cell.setCellValue(ticket.isStatus());

            IntStream.range(0, 4).forEach(x -> {
                sheet.autoSizeColumn(x);
            });

        }
    }

    public byte[] export() throws IOException {
        XSSFSheet sheet = workbook.getSheet("Tickets");
        if(sheet == null){
            sheet = workbook.createSheet("Tickets");
        }
        writeHeaderLine(sheet);
        writeDataLines(sheet);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        workbook.close();
        return outputStream.toByteArray();
    }
}
