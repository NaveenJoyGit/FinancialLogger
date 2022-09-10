package com.financialog.enums;

public enum HoldStatus {

    ACTIVE("Active"),SOLD("Sold");

    private String status;

    HoldStatus(String status) {
        this.status = status;
    }

}
