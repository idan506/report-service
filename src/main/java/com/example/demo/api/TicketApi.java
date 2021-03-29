package com.example.demo.api;

import com.example.demo.model.TicketModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "book-service")
public interface TicketApi {

    @GetMapping("/ticket/export")
    public List<TicketModel> list();
}
