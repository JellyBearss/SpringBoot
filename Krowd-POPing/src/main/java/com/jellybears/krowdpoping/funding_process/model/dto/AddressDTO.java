package com.jellybears.krowdpoping.funding_process.model.dto;

public class AddressDTO {
    private String addressTitle;
    private String recipientName;
    private String recipientAddress;
    private String recipientPhoneNumber;
    private boolean whetherDefaultAddress;

    public String getAddressTitle() {
        return addressTitle;
    }

    public void setAddressTitle(String addressTitle) {
        this.addressTitle = addressTitle;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getRecipientPhoneNumber() {
        return recipientPhoneNumber;
    }

    public void setRecipientPhoneNumber(String recipientPhoneNumber) {
        this.recipientPhoneNumber = recipientPhoneNumber;
    }

    public boolean isWhetherDefaultAddress() {
        return whetherDefaultAddress;
    }

    public void setWhetherDefaultAddress(boolean whetherDefaultAddress) {
        this.whetherDefaultAddress = whetherDefaultAddress;
    }
}
