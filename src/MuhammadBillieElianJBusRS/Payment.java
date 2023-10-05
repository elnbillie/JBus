package MuhammadBillieElianJBusRS;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

//modul 3 ngubah print ke toString
public class Payment extends Invoice
{
    private int busId;
    public Timestamp departureDate;
    public String busSeat;
        
    public Payment(int id, int buyerId, int renterId, int busId, String busSeat, Timestamp departureDate) {
        super(id, buyerId, renterId);
        this.busId = busId;
        this.departureDate = new Timestamp(departureDate.getTime() + 2 * 24 * 60 * 60 * 1000); 
        this.busSeat = busSeat;
    }
    
    public Payment(int id, Account buyer, Renter renter, int busId, String busSeat, Timestamp departureDate) {
        super(id, buyer, renter);
        this.busId = busId;
        this.departureDate = new Timestamp(departureDate.getTime() + 2 * 24 * 60 * 60 * 1000); 
        this.busSeat = busSeat;
    }
    
    /*public String getDepartureInfo(){
        return "busId : " + busId + " departureDate : " + departureDate.getTime() + " busSeat : " + busSeat ; 
    }*/
    
    public String getDepartureInfo(){
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
        return  super.toString() + " Bus ID: " + busId + " Departure Date: " + sdf.format(departureDate.getTime()) +" Bus Seat: " + busSeat;
    }
    
    public int getBusId(){
        return busId;
    }
    
    public String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
        //return sdf.format(departureDate.getTime()) ;
        return sdf.format(time.getTime()) ;
    }
    
    public static boolean isAvailable(Timestamp departureSchedule, String seat, Bus bus) {
        for (Schedule s : bus.schedules) {
            if (s.departureSchedule.equals(departureSchedule) && s.isSeatAvailable(seat)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean makeBooking(Timestamp departureSchedule, String seat, Bus bus) {
        for (Schedule s : bus.schedules) {
            if (s.departureSchedule.equals(departureSchedule) && s.isSeatAvailable(seat)) {
                s.bookSeat(seat);
                return true;
            }
        }
        return false;
    }
    
}
