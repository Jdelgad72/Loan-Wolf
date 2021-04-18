package com.example.loanwolf;

public class PaymentScheduleListObject {
    private String startDate;
    private String amount;

    public PaymentScheduleListObject(String startDate, String amount) {
        this.startDate = startDate;
        this.amount = amount;
    }

    public String getstartDate() {
        return this.startDate;
    }

    public String getamount() {
        return this.amount;
    }

}
