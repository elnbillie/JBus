package com.MuhammadBillieElianJBusRS;

public class Renter extends Serializable {

    private static final String REGEX_PHONE = "^\\d{9,12}$";
    private static final String REGEX_NAME = "^[A-Z][a-zA-Z0-9_]{4,19}$";

    public String address;
    public String companyName;
    public String phoneNumber;


    public Renter(String companyName, String phoneNumber, String address) {
        super();
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Renter(String companyName, String phoneNumber) {
        super();
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = "";
    }

    public Renter(String companyName) {
        super();
        this.companyName = companyName;
        this.phoneNumber = "";
        this.address = "";
    }


    public boolean validate() {
        //String phoneString = Integer.toString(phoneNumber);
        return companyName.matches(REGEX_NAME) && phoneNumber.matches(REGEX_PHONE);
    }
}