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
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.sql.Timestamp;


public class JBus {
    public static void main(String[] args) throws InterruptedException {
        String filepath = "D:\\Kuliah\\Semester 3\\OOP\\JBus\\JBus\\data\\accountDatabase.json";

        try {
            JsonTable<Account> tableAccount = new JsonTable<>(Account.class, filepath);

            Account newAccount = new Account( "Dio", "dio@gmail.com", "NgikNgok");

            tableAccount.add(newAccount);

            tableAccount.writeJson();

            System.out.println(newAccount);
        } catch (IOException e) {
            System.err.println("An IOException was caught: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An exception was caught: " + e.getMessage());
            e.printStackTrace();
        }

        Bus bus = createBus();
        bus.schedules.forEach(Schedule::printSchedule);
        for (int i = 0; i < 10; i++) {
            BookingThread thread = new BookingThread("Thread " + i, bus,
                    Timestamp.valueOf("2023-07-27 19:00:00"));
        }
        Thread.sleep(1000);
        bus.schedules.forEach(Schedule::printSchedule);

    }

    public static Bus createBus() {
        Price price = new Price(750000, 5);
        Bus bus = new Bus("Netlab Bus", Facility.LUNCH, price, 25, BusType.REGULER, City.BANDUNG, new Station("Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station("Halte UI", City.JAKARTA, "Universitas Indonesia"));
        Timestamp timestamp = Timestamp.valueOf("2023-07-27 19:00:00");
        bus.addSchedule(timestamp);
        return bus;
    }
}


/*
try {
            String filepath = "D:\\Kuliah\\Semester 3\\OOP\\JBus\\JBus\\data\\accountDatabase.json";
            JsonTable<Bus> busList = new JsonTable<>(Bus.class,filepath);
            //List<Bus> filteredBus= filterByDepartureAndArrival(busList, City.JAKARTA, 0, 3);
            //List<Bus> filteredBus= filterByPrice(busList, 100000,500000);
            //List<Bus> filteredBus= filterBusId(busList, 155);
            //List<Bus> filteredBus= filterByDepartureAndArrival(busList, City.JAKARTA, City.SURABAYA, 0, 3);
            //filteredBus.forEach(bus -> System.out.println(bus.toString()));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static List<Bus> filterByDeparture(List<Bus> buses, City departure, int page, int pageSize){
        try{
            Predicate<Bus> s = (val) -> val.departure.city.equals(departure);
            List<Bus> filteredBus = Algorithm.collect(buses, s);
            return Algorithm.paginate(filteredBus, page, pageSize, t->true);
        }
        catch(Throwable t){
            t.printStackTrace();
            return null;
        }
    }

    public static List<Bus> filterByPrice(List<Bus> buses, int min, int max){
        Predicate<Bus> priceFilter = b -> b.price.price >= min && b.price.price <= max;
        return Algorithm.collect(buses, priceFilter);
    }

    public static List<Bus> filterBusId(List<Bus> buses, int id){
        return Algorithm.collect(buses, (Predicate<Bus>) b -> b.id == id);
    }

    public static List<Bus> filterByDepartureAndArrival(List<Bus> buses, City departure, City arrival, int page, int pageSize){
        return Algorithm.paginate(buses, page, pageSize, b -> b.departure.city.equals(departure) && b.arrival.city.equals(arrival));
    }
*/






