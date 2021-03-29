package com.example.demo.converter;

import com.example.demo.entity.ReportEntity;
import com.example.demo.model.Report;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReportConvert {

    @Autowired
    private ModelMapper modelMapper;

    public Report toModel(ReportEntity entity){
        return modelMapper.map(entity, Report.class);
    }
    public ReportEntity toEntity(Report book){
        return modelMapper.map(book, ReportEntity.class);
    }
}
