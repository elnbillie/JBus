package MuhammadBillieElianJBusRS;

public class Bus extends Serializable
{
    public int capacity;
    public Facility facility;
    public String name;
    public Price price;
    
    /*public Bus(String name, Facility facility,Price price,int capacity){
        this.name=name;
        this.facility=facility;
        this.price=price;
        this.capacity=capacity;
    } */
    public Bus(int id, String name, Facility facility, Price price, int capacity){
        super(id);
        this.name=name;
        this.facility=facility;
        this.price=price;
        this.capacity=capacity;
    }
}
