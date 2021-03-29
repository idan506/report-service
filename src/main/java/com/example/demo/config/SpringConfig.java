package com.example.demo.config;

import com.example.demo.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.Date;

@Configuration
@EnableScheduling
public class SpringConfig {

    @Autowired
    ReportService reportService;

    @Scheduled(cron = "0 0 12 1 * ?")
    public void scheduleTaskUsingCronExpression() throws IOException {
        reportService.createReport();
    }
}
