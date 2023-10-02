package MuhammadBillieElianJBusRS;

import java.util.*;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;

public class Bus extends Serializable implements FileParser
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
    
    public Bus(int id, String name, Facility facility, Price price, int capacity, BusType busType, City city, Station departure, Station arrival){
        super(id);
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
    
    @Override
    public Object write() {
        return this;  
    }
    
    @Override
    public boolean read(String input) {
        return true;  
    }
    
    public String toString() {
        return "id=" + super.id +
           ", name=" + name + 
           ", facility=" + facility +
           ", price=" + price + 
           ", capacity=" + capacity +
           ", busType=" + busType +
           ", city=" + city +
           ", departure=" + departure +
           ", arrival=" + arrival ;
    }
    
    public void addSchedule(Timestamp schedule) { 
        schedules.add(new Schedule(schedule, this.capacity));
    }
    
    
}
