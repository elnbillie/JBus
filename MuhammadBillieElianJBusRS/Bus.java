package MuhammadBillieElianJBusRS;

import java.util.*;
import java.text.SimpleDateFormat;

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
    
    public void addSchedule(Calendar calendar) {
        Schedule newSchedule = new Schedule(calendar, capacity);
        schedules.add(newSchedule);
    }
    
    public void printSchedule(Schedule schedule) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
        System.out.println("Tanggal keberangkatan: " + sdf.format(schedule.departureSchedule.getTime()));
        System.out.println("Daftar kursi dan ketersediaan kursinya:");
        for (Map.Entry<String, Boolean> entry : schedule.seatAvailability.entrySet()) {
            System.out.println("Seat: " + entry.getKey() + " - Available: " + entry.getValue());
        }
    }
}
