package com.example.demo.repository;

import com.example.demo.api.TicketApi;
import com.example.demo.entity.ReportEntity;
import com.example.demo.model.TicketModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ReportRepository extends JpaRepository<ReportEntity, Long> {

}
