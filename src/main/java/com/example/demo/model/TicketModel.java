package com.example.demo.model;

import java.util.Date;

public class TicketModel {

    private long id;
    private String idUser;
    private boolean status;
    private Date dayOfHire;
    private Date expirationDate;

    public TicketModel(long id, String idUser, boolean status, Date dayOfHire, Date expirationDate) {
        this.id = id;
        this.idUser = idUser;
        this.status = status;
        this.dayOfHire = dayOfHire;
        this.expirationDate = expirationDate;
    }

    public TicketModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDayOfHire() {
        return dayOfHire;
    }

    public void setDayOfHire(Date dayOfHire) {
        this.dayOfHire = dayOfHire;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
