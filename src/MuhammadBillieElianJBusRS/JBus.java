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
    public static void main(String[] args) {
        // PT Modul 5
        // Tes Pagination
        Bus b = createBus();
        List<Timestamp> listOfSchedules = new ArrayList<>();
        listOfSchedules.add(Timestamp.valueOf("2023-7-18 15:00:00"));
        listOfSchedules.add(Timestamp.valueOf("2023-7-20 12:00:00"));
        listOfSchedules.add(Timestamp.valueOf("2023-7-22 10:00:00"));
        listOfSchedules.add(Timestamp.valueOf("2023-7-26 12:00:00"));

        listOfSchedules.forEach(b::addSchedule);
        System.out.println("Page 1");
        Algorithm.paginate(b.schedules, 0, 3, t -> true).forEach(System.out::println);
        System.out.println("=====================================================");
        System.out.println("Page 2");
        Algorithm.paginate(b.schedules, 1, 3, t -> true).forEach(System.out::println);
        System.out.println("=====================================================");

        // Tes Booking
        String msgSuccess = "Booking Success!";
        String msgFailed = "Booking Failed";
        // valid date, invalid seat = Booking Failed
        Timestamp t1 = Timestamp.valueOf("2023-7-19 15:00:00");
        System.out.println("\nMake booking at July 19, 2023 15:00:00 Seats: RS17 RS18");
        System.out.println(Payment.makeBooking(t1, List.of("RS17", "RS18"), b)? msgSuccess : msgFailed);
        // valid date, invalid seat = Booking Failed
        Timestamp t2 = Timestamp.valueOf("2023-7-18 15:00:00");
        System.out.println("Make booking at July 18, 2023 15:00:00 Seat RS26");
        System.out.println(Payment.makeBooking(t2, "RS26", b)? msgSuccess : msgFailed);
        // valid date, valid seat = Booking Success
        System.out.println("Make booking at July 18, 2023 15:00:00 Seats: RS07 RS08");
        System.out.println(Payment.makeBooking(t2, List.of("RS07", "RS08"), b)? msgSuccess : msgFailed);
        // valid date, valid seat = Booking Success
        Timestamp t3 = Timestamp.valueOf("2023-7-20 12:00:00");
        System.out.println("Make booking at July 20, 2023 12:00:00 Seats: RS01 RS02");
        System.out.println(Payment.makeBooking(t3, List.of("RS01", "RS02"), b)? msgSuccess : msgFailed);
        // valid date, book the same seat = Booking Failed
        System.out.println("Make booking at July 20, 2023 12:00:00 Seat RS01");
        System.out.println(Payment.makeBooking(t3, "RS01", b)? msgSuccess : msgFailed);
        // check if the data changed
        System.out.println("\nUpdated Schedule");
        Algorithm.paginate(b.schedules, 0, 4, t-> true).forEach(System.out::println);
    }

    public static Bus createBus() {
        Price price = new Price(750000, 5);
        Bus bus = new Bus("Netlab Bus", Facility.LUNCH, price, 25, BusType.REGULER, City.BANDUNG, new Station("Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station("Halte UI", City.JAKARTA, "Universitas Indonesia"));
        return bus;
    }

}



