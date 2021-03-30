package com.example.demo.controller;

import com.example.demo.model.Report;
import com.example.demo.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public List<Report> getAllReport(){
        return reportService.getAll();
    }

    @Scheduled(cron = "0 0 12 1 * ?")
    @GetMapping("/export")
    public ResponseEntity<Report> exportToExcel() throws IOException {
        return ResponseEntity.ok(reportService.createReport());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> downLoadReport(@PathVariable(name = "id") long id, HttpServletResponse response){

        Report report = reportService.getReportById(id);
        Resource resource = new ByteArrayResource(report.getFile());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + report.getFileName() )
                .body(resource);
    }
}
