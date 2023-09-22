package MuhammadBillieElianJBusRS;

public class Payment extends Invoice
{
    private int busId;
    public String departureDate;
    public String busSeat;
    
    /*public Payment(int id, int buyerId, int renterId, String time, int busId, String departureDate, String busSeat){
    super(id,buyerId,renterId,time);
    this.renterId=renterId;
    this.time=time;
    this.busId=busId;
    this.departureDate = departureDate;
    this.busSeat = busSeat;
    }
    
    public Payment(int id, Account buyer, Renter renter, String time, int busId,String departureDate, String busSeat){
    super(id,buyerId,renterId,time);
    this.buyerId = buyer.id;
    this.renterId = renter.id;
    this.time = time;
    this.busId = busId;
    this.departureDate = departureDate;
    this.busSeat = busSeat;
    }*/
    
    public Payment(int id, int buyerId, int renterId, String time, int busId, String departureDate, String busSeat) {
        super(id, buyerId, renterId, time);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }
    
    public Payment(int id, Account buyer, Renter renter, String time, int busId, String departureDate, String busSeat) {
        super(id, buyer, renter, time);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }
    
    public String print(){
        return "busId : " + busId + " departureDate : " + departureDate + " busSeat : " + busSeat ; 
    }
    
    public int getBusId(){
        return busId;
    }
}
