package com.MuhammadBillieElianJBusRS;

import com.MuhammadBillieElianJBusRS.dbjson.Serializable;

import java.sql.Timestamp;


/**
 * Kelas yang mewakili faktur dalam sistem.
 */

public class Invoice extends Serializable
{
    public Timestamp time;
    public int buyerId;
    public int renterId;
    public BusRating rating;
    public PaymentStatus status;


    /**
     * Enumerasi yang mendefinisikan penilaian bus.
     */
    public enum BusRating{
        NONE, NEUTRAL, GOOD, BAD;
    }

    /**
     * Enumerasi yang mendefinisikan status pembayaran.
     */
    
    public enum PaymentStatus{
        FAILED, WAITING, SUCCESS;
    }


    /**
     * Konstruktor untuk membuat objek faktur dengan ID pembeli dan penyewa yang spesifik.
     * @param buyerId ID pembeli.
     * @param renterId ID penyewa.
     */
    protected Invoice(int buyerId, int renterId){
        super();
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = new Timestamp(System.currentTimeMillis());
        this.rating = rating.NONE;
        this.status = status.WAITING;
    }


    /**
     * Konstruktor untuk membuat objek faktur dengan objek akun pembeli dan penyewa.
     *
     * @param buyer Objek akun pembeli.
     * @param renter Objek penyewa.
     */
    public Invoice(Account buyer, Renter renter){
        super();
        this.buyerId = buyer.id;
        this.renterId = renter.id;
        this.time = new Timestamp(System.currentTimeMillis());
        this.rating = rating.NONE;
        this.status = status.WAITING;
    }


    /**
     * Mengembalikan representasi string dari faktur.
     *
     * @return Representasi string dari faktur.
     */
    public String toString(){
        return "Invoice Id : " + super.id + " Buyer Id : " + buyerId + " Renter Id : " + renterId + " time : "+ time;
    }
}
