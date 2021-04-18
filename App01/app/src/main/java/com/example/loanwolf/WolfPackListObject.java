package com.example.loanwolf;

public class WolfPackListObject {
    private String borrowerLender;
    private String openLoanID;
    private String amount;
    private String interestRate;
    private String paymentType;
    private String startDate;
    private String numPayments;

    public WolfPackListObject(String borrowerLender, String openLoanID, String amount, String interestRate, String paymentType, String startDate, String numPayments){
        this.borrowerLender = borrowerLender;
        this.openLoanID = openLoanID;
        this.amount = amount;
        this.interestRate = interestRate;
        this.paymentType = paymentType;
        this.startDate = startDate;
        this.numPayments = numPayments;
    }

    public String getBorrowerLender() {
        return this.borrowerLender;
    }

    public String getOpenLoanID() {
        return this.openLoanID;
    }

    public String getAmount() {
        return this.amount;
    }

    public String getInterestRate() {
        return this.interestRate;
    }

    public String getPaymentType() {
        return this.paymentType;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getNumPayments() {
        return this.numPayments;
    }
}
