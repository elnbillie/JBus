package MuhammadBillieElianJBusRS;

import java.util.Calendar;
import java.text.SimpleDateFormat;

//modul 3 ngubah print ke toString
public class Payment extends Invoice
{
    private int busId;
    public Calendar departureDate;
    public String busSeat;
        
    public Payment(int id, int buyerId, int renterId, int busId, String busSeat) {
        super(id, buyerId, renterId);
        this.busId = busId;
        this.departureDate = Calendar.getInstance();
        this.departureDate.add(Calendar.DAY_OF_MONTH, 2);
        this.busSeat = busSeat;
    }
    
    public Payment(int id, Account buyer, Renter renter, int busId,String busSeat) {
        super(id, buyer, renter);
        this.busId = busId;
        this.departureDate = Calendar.getInstance();
        this.departureDate.add(Calendar.DAY_OF_MONTH, 2);
        this.busSeat = busSeat;
    }
    
    public String getDepartureInfo(){
        return "busId : " + busId + " departureDate : " + departureDate.getTime() + " busSeat : " + busSeat ; 
    }
    
    public int getBusId(){
        return busId;
    }
    
    public String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
        return sdf.format(departureDate.getTime()) ;
    }
    
    
}
