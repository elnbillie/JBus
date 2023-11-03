package MuhammadBillieElianJBusRS;

import java.util.*;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;

public class Bus extends Serializable
{
    public int capacity;
    public Facility facility;
    public String name;
    public Price price;
    public BusType busType;
    public City city;
    public Station departure;
    public Station arrival;
    public List<Schedule> schedules;
    
    public Bus(String name, Facility facility, Price price, int capacity, BusType busType, City city, Station departure, Station arrival){
        super();
        this.name=name;
        this.facility=facility;
        this.price=price;
        this.capacity=capacity;
        this.busType=busType;
        this.city=city;
        this.departure=departure;
        this.arrival=arrival;
        this.schedules=new ArrayList<>();
    }
    

    
    public String toString() {
        return "Bus Id : " + super.id + "\n" +
           "Nama Bus : " + name + "\n" +
                "Berangkat : " + departure.city + "\n" +
                "Tujuan : " + arrival.city + "\n" +
                "Harga : " + price.price + "\n" +
                "fasilitas : " + facility + "\n" +
           "kapasitas : " + capacity + "\n" +
           "Tipe Bus : " + busType + "\n" +
           "Kota : " + city + "\n" ;

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
    
    
}
