package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "attachment")
public class Attachment extends AbstractBaseEntity {

    @Lob
    private byte[] file;

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
