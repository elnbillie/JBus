package com.MuhammadBillieElianJBusRS;

import com.MuhammadBillieElianJBusRS.dbjson.Serializable;

import java.util.*;
import java.sql.Timestamp;

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

    public Object write(){
        return null;
    }
    public boolean read(String filename){
        return false;
    }
}
