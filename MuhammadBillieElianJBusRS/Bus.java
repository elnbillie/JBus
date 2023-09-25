package MuhammadBillieElianJBusRS;

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

}
