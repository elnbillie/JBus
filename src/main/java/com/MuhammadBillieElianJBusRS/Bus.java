package com.MuhammadBillieElianJBusRS;

import com.MuhammadBillieElianJBusRS.dbjson.Serializable;

import java.util.*;
import java.sql.Timestamp;

/**
 * Kelas yang mewakili bus dalam sistem pemesanan.
 */
public class Bus extends Serializable
{
    public int capacity;
    public List<Facility> facilities;
    public String name;
    public Price price;
    public BusType busType;
    //public City city;
    public Station departure;
    public Station arrival;
    public List<Schedule> schedules;
    public int accountId;

    /**
     * Konstruktor untuk membuat objek bus baru.
     *
     * @param accountId ID akun yang terkait dengan bus.
     * @param name Nama bus.
     * @param facilities Daftar fasilitas yang tersedia di bus.
     * @param price Objek yang menggambarkan harga bus.
     * @param capacity Kapasitas penumpang bus.
     * @param busType Tipe bus.
     * @param departure Stasiun keberangkatan bus.
     * @param arrival Stasiun tujuan bus.
     */
    public Bus(int accountId, String name, List<Facility> facilities, Price price, int capacity, BusType busType, Station departure, Station arrival) {
        super();
        this.accountId = accountId;
        this.capacity = capacity;
        this.facilities = facilities;
        this.name = name;
        this.price = price;
        this.busType = busType;
        this.arrival = arrival;
        this.departure = departure;
        this.schedules = new ArrayList<>();
    }

    /**
     * Mengembalikan representasi string dari objek bus.
     *
     * @return Representasi string dari bus.
     */
    public String toString() {
        return "Bus Id : " + super.id + "\n" +
           "Nama Bus : " + name + "\n" +
                "Berangkat : " + departure.city + "\n" +
                "Tujuan : " + arrival.city + "\n" +
                "Harga : " + price.price + "\n" +
                "fasilitas : " + facilities + "\n" +
           "kapasitas : " + capacity + "\n" +
           "Tipe Bus : " + busType + "\n" ;
           //"Kota : " + city + "\n" ;
    }

    /**
     * Menambahkan jadwal baru ke dalam daftar jadwal bus.
     *
     * @param schedule Timestamp untuk jadwal yang akan ditambahkan.
     */
    public void addSchedule(Timestamp schedule) { 
        //schedules.add(new Schedule(schedule, this.capacity));
        //throw new IllegalArgumentException("Schedule with the same timestamp already exists.");
        try {
            for(Schedule existSchedule: this.schedules){
                if(existSchedule.departureSchedule.equals(schedule)){
                    return;
                }
            }
            this.schedules.add(new Schedule(schedule, this.capacity));
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Menulis data bus ke penyimpanan.
     *
     * @return Objek yang mewakili data bus yang ditulis.
     */
    public Object write(){
        return null;
    }

    /**
     * Menulis data bus ke penyimpanan.
     *
     * @return Objek yang mewakili data bus yang ditulis.
     */
    public boolean read(String filename){
        return false;
    }
}
