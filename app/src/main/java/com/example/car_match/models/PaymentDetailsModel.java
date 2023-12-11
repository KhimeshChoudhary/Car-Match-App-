package com.example.car_match.models;

public class PaymentDetailsModel {
    private String paymentId;
    private String amountValue,addressValue,
            nameValue,
            modelNameValue,
            emailValue,phoneNumber;



    public PaymentDetailsModel() {
    }


    public PaymentDetailsModel(String paymentId, String amountValue, String addressValue, String nameValue, String modelNameValue, String emailValue, String phoneNumber) {
        this.paymentId = paymentId;
        this.amountValue = amountValue;
        this.addressValue = addressValue;
        this.nameValue = nameValue;
        this.modelNameValue = modelNameValue;
        this.emailValue = emailValue;
        this.phoneNumber = phoneNumber;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getAmountValue() {
        return amountValue;
    }

    public void setAmountValue(String amountValue) {
        this.amountValue = amountValue;
    }

    public String getAddressValue() {
        return addressValue;
    }

    public void setAddressValue(String addressValue) {
        this.addressValue = addressValue;
    }

    public String getNameValue() {
        return nameValue;
    }

    public void setNameValue(String nameValue) {
        this.nameValue = nameValue;
    }

    public String getModelNameValue() {
        return modelNameValue;
    }

    public void setModelNameValue(String modelNameValue) {
        this.modelNameValue = modelNameValue;
    }

    public String getEmailValue() {
        return emailValue;
    }

    public void setEmailValue(String emailValue) {
        this.emailValue = emailValue;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
