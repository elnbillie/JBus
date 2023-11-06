package MuhammadBillieElianJBusRS;

import java.sql.Timestamp;

public class BookingThread extends Thread{
    private Bus bus;
    private Timestamp timestamp ;
    private String seatCode = "RS01";

    public BookingThread(String name, Bus bus, Timestamp timestamp){
        super(name);
        this.bus = bus;
        this.timestamp = timestamp;
        this.start();
    }

    public void run(){
        // Buat Kode anda disini
        System.out.println("Thread " + this.getName() + " ID: " +  this.getId() +  " is running");

        synchronized (bus) {
            if (Payment.makeBooking(timestamp, seatCode, bus)) {
                System.out.println("Thread " + this.getId() + " Berhasil Melakukan Booking");
            } else {
                System.out.println("Thread " + this.getId() + " Gagal Melakukan Booking");
            }
        }
    }
}
