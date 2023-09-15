package MuhammadBillieElianJBusRS;


/**
 * Write a description of class Bus here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bus
{
    int capacity;
    Facility facility;
    String name;
    Price price;
    
    public Bus(String name, Facility facility,Price price,int capacity){
        this.name=name;
        this.facility=facility;
        this.price=price;
        this.capacity=capacity;
    } 
}
