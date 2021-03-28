package com.example.demo.entity;

import org.springframework.cloud.openfeign.FeignClient;

import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Entity
@FeignClient("book-service")
public class report {
    private long id;
    private List<TicketEntity> tickets;
    private Date dayExport;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<TicketEntity> getBooks() {
        return tickets;
    }

    public void setBooks(List<TicketEntity> tickets) {
        this.tickets = tickets;
    }

    public Date getDayExport() {
        return dayExport;
    }

    public void setDayExport(Date dayExport) {
        this.dayExport = dayExport;
    }
}
