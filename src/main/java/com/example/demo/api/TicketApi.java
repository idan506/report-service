package com.example.demo.api;

import com.example.demo.model.TicketModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@FeignClient(value = "book-service")
public interface TicketApi {

    @GetMapping("/ticket/export")
    List<TicketModel> list(@RequestParam(name = "firstDay")LocalDate firstDay, @RequestParam(name = "lastDay")LocalDate lastDay);
}
