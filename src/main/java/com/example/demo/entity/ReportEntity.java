package com.example.demo.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "report")
public class ReportEntity extends AbstractBaseEntity {

    @Column
    private String fileName;

    @Column
    @CreatedDate
    private Date createdDate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Attachment fileContent;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Attachment getFileContent() {
        return fileContent;
    }

    public void setFileContent(Attachment fileContent) {
        this.fileContent = fileContent;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
