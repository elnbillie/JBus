package com.MuhammadBillieElianJBusRS.controller;

import com.MuhammadBillieElianJBusRS.Account;
import com.MuhammadBillieElianJBusRS.Bus;
import com.MuhammadBillieElianJBusRS.BusType;
import com.MuhammadBillieElianJBusRS.Station;
import com.MuhammadBillieElianJBusRS.Facility;
import com.MuhammadBillieElianJBusRS.dbjson.JsonAutowired;
import com.MuhammadBillieElianJBusRS.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.List;
import com.MuhammadBillieElianJBusRS.Algorithm;
import com.MuhammadBillieElianJBusRS.Price;
import com.MuhammadBillieElianJBusRS.Predicate;
import java.sql.Timestamp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/bus")
public class BusController implements BasicGetController<Bus>{

    @JsonAutowired(value = Bus.class, filepath = "D:\\Kuliah\\Semester 3\\OOP\\JBus\\JBus\\src\\main\\java\\com\\MuhammadBillieElianJBusRS\\json\\bus.json")
    public static JsonTable<Bus> busTable;

    //@Override
    public JsonTable<Bus> getJsonTable() {
        return busTable;
    }

    @PostMapping("/create")
    public BaseResponse<Bus> create(
            @RequestParam int accountId,
            @RequestParam String name,
            @RequestParam int capacity,
            @RequestParam List<Facility> facilities,
            @RequestParam BusType busType,
            @RequestParam int price,
            @RequestParam int stationDepartureId,
            @RequestParam int stationArrivalId
    ) {
        Account account = Algorithm.<Account>find(AccountController.accountTable, pred-> pred.id == accountId);
        Station stationDeparture = Algorithm.<Station>find(StationController.stationTable, pred -> pred.id == stationDepartureId);
        Station stationArrival = Algorithm.<Station>find(StationController.stationTable, pred -> pred.id == stationArrivalId);


        if(account == null){
            return new BaseResponse<>(false, "Akun tidak dapat ditemukan", null);
        }
        if(stationDeparture == null || stationArrival == null) {
            return new BaseResponse<>(false, "Stasiun tidak dapat ditemukan", null);
        }

        Bus bus = new Bus(accountId, name, facilities, new Price(price), capacity, busType, stationDeparture, stationArrival);
        busTable.add(bus);

        return new BaseResponse<>(true, "Bus berhasil dibuat", bus);
    }

    @PostMapping("/addSchedule")
    public BaseResponse<Bus> addSchedule(
            @RequestParam int busId,
            @RequestParam String time
    ){
            Predicate<Bus> s = (val) -> val.id == busId;
            Bus tmp = Algorithm.find(busTable,s);
            if (tmp != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");
                java.sql.Timestamp departureDate = Timestamp.valueOf(time);
                tmp.addSchedule(departureDate);
                return new BaseResponse<>(true, "Berhasil addSchedule", tmp);

            }
            return new BaseResponse<>(false, "Gagal addSchedule", null);
        }
}
