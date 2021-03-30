package com.example.demo.service;

import com.example.demo.api.TicketApi;
import com.example.demo.converter.ReportConvert;
import com.example.demo.entity.Attachment;
import com.example.demo.entity.ReportEntity;
import com.example.demo.export.ExportExcel;
import com.example.demo.model.Report;
import com.example.demo.model.TicketModel;
import com.example.demo.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private ReportRepository repository;

    @Autowired
    private TicketApi ticketApi;

    @Autowired
    private ReportConvert convert;

    public Report createReport() throws IOException {

        ReportEntity report = new ReportEntity();

        LocalDate date = LocalDate.now();
        LocalDate firstDayOflastMonth = LocalDate.of(date.getYear(), date.getMonth(), 1).minusMonths(1);
        LocalDate lastDayOflastMonth = LocalDate.of(firstDayOflastMonth.getYear(), firstDayOflastMonth.getMonth(), firstDayOflastMonth.lengthOfMonth());

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM");
        String currentDateTime = dateFormatter.format(new Date());

        String fileName = "ticket_" + currentDateTime + ".xlsx";

        List<TicketModel> listTicket = ticketApi.list(firstDayOflastMonth, lastDayOflastMonth);

        ExportExcel exportExcel = new ExportExcel(listTicket);

        report.setFileName(fileName);
        report.setCreatedDate(new Date());

        Attachment reportContent = new Attachment();
        reportContent.setFile(exportExcel.export());
        report.setFileContent(reportContent);
        report = repository.save(report);
        return convert.toModel(report);
    }

    public List<Report> getAll(){
        List<ReportEntity> entity = repository.findAll();
        List<Report> reportList = new ArrayList<>();
        for(ReportEntity r: entity){
            Report report = new Report();
            report.setFileName(r.getFileName());
            report.setId(r.getId());
            report.setCreatedDate(r.getCreatedDate());
            reportList.add(report);
        }
        return reportList;
    }

    public Report getReportById(long id){
        ReportEntity report = repository.findById(id).get();
        Report result = convert.toModel(report);
        result.setFile(report.getFileContent().getFile());
        return result;
    }
}
