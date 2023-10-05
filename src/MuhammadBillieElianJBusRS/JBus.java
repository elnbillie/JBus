package MuhammadBillieElianJBusRS;

/**
 * Write a description of class JBus here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class JBus
{
    public static Bus createBus() {
        Price price = new Price(750000, 5);
        Bus bus = new Bus(1, "Netlab Bus", Facility.LUNCH, price, 25, BusType.REGULER, City.BANDUNG, new Station(1, "Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station(2, "Halte UI", City.JAKARTA, "Universitas Indonesia"));
        return bus;
    }
    
    public static void main(String[] args) {


    Bus b = createBus();
    Timestamp schedule1 = Timestamp.valueOf("2023-7-18 15:00:00");
    Timestamp schedule2 = Timestamp.valueOf("2023-7-20 12:00:00");
    b.addSchedule(schedule1);
    b.addSchedule(schedule2);
    b.schedules.forEach(Schedule :: printSchedule);
     // Invalid date
    Timestamp t1 = Timestamp.valueOf("2023-7-19 15:00:00");
    System.out.println("Make booking at July 19, 2023 15:00:00 Seat RS12");
    System.out.println(Payment.makeBooking(t1, "RS12", b));
    // Valid date, invalid seat
    Timestamp t2 = Timestamp.valueOf("2023-7-18 15:00:00");
    System.out.println("\nMake booking at July 18, 2023 15:00:00 Seat RS20");
    System.out.println(Payment.makeBooking(t2, "RS20", b));
    // Valid date, valid seat
    System.out.println("\nMake booking at July 18, 2023 15:00:00 Seat RS07");
    System.out.println(Payment.makeBooking(t2, "RS07", b));
    Timestamp t3 = Timestamp.valueOf("2023-7-20 12:00:00");
    System.out.println("\nMake booking at July 20, 2023 12:00:00 Seat RS01");
    System.out.println(Payment.makeBooking(t3, "RS01", b));
    System.out.println("\nMake booking at July 20, 2023 12:00:00 Seat RS01 again");
    System.out.println(Payment.makeBooking(t3, "RS01", b));
    // Check if the data changed
    System.out.println("\nUpdated Schedule\n");
    b.schedules.forEach(Schedule :: printSchedule);
    
    }


    
    
}



