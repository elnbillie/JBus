package com.MuhammadBillieElianJBusRS;

import com.MuhammadBillieElianJBusRS.dbjson.Serializable;


/**
 * Kelas yang merepresentasikan penyewa atau pemilik bus dalam sistem.
 */
public class Renter extends Serializable {

    private static final String REGEX_PHONE = "^\\d{9,12}$";
    private static final String REGEX_NAME = "^[A-Z][a-zA-Z0-9_]{4,19}$";

    public String address;
    public String companyName;
    public String phoneNumber;



    /**
     * Konstruktor untuk membuat objek Renter dengan nama perusahaan, nomor telepon, dan alamat.
     *
     * @param companyName Nama perusahaan.
     * @param phoneNumber Nomor telepon.
     * @param address Alamat.
     */
    public Renter(String companyName, String phoneNumber, String address) {
        super();
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    /**
     * Konstruktor untuk membuat objek Renter dengan nama perusahaan dan nomor telepon, tanpa alamat.
     *
     * @param companyName Nama perusahaan.
     * @param phoneNumber Nomor telepon.
     */
    public Renter(String companyName, String phoneNumber) {
        super();
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = "";
    }

    /**
     * Konstruktor untuk membuat objek Renter hanya dengan nama perusahaan.
     *
     * @param companyName Nama perusahaan.
     */
    public Renter(String companyName) {
        super();
        this.companyName = companyName;
        this.phoneNumber = "";
        this.address = "";
    }

    /**
     * Memvalidasi nama perusahaan dan nomor telepon penyewa.
     *
     * @return true jika nama perusahaan dan nomor telepon valid, false jika tidak.
     */
    public boolean validate() {
        //String phoneString = Integer.toString(phoneNumber);
        return companyName.matches(REGEX_NAME) && phoneNumber.matches(REGEX_PHONE);
    }
}