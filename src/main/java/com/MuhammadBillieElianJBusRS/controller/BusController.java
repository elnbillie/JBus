package com.MuhammadBillieElianJBusRS.controller;

import com.MuhammadBillieElianJBusRS.*;
import com.MuhammadBillieElianJBusRS.dbjson.JsonAutowired;
import com.MuhammadBillieElianJBusRS.dbjson.JsonTable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.sql.Timestamp;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;


/**
 * Controller untuk mengelola operasi yang berkaitan dengan bus.
 * Menyediakan fungsi untuk membuat bus, menambahkan jadwal, dan operasi lainnya.
 */
@RestController
@RequestMapping("/bus")
public class BusController implements BasicGetController<Bus>{

    @JsonAutowired(value = Bus.class, filepath = "D:\\Kuliah\\Semester 3\\OOP\\JBus\\JBus\\src\\main\\java\\com\\MuhammadBillieElianJBusRS\\json\\bus.json")
    public static JsonTable<Bus> busTable;
    /**
     * Mengembalikan JsonTable yang berisi bus.
     *
     * @return JsonTable yang berisi bus.
     */
    //@Override
    public JsonTable<Bus> getJsonTable() {
        return busTable;
    }
    /**
     * Membuat bus baru.
     *
     * @param accountId ID akun pembuat bus.
     * @param name Nama bus.
     * @param capacity Kapasitas bus.
     * @param facilities Fasilitas yang tersedia di bus.
     * @param busType Tipe bus.
     * @param price Harga bus.
     * @param stationDepartureId ID stasiun keberangkatan.
     * @param stationArrivalId ID stasiun kedatangan.
     * @return BaseResponse yang berisi status dan informasi bus yang dibuat.
     */
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
    /**
     * Menambahkan jadwal ke bus.
     *
     * @param busId ID bus yang akan ditambahkan jadwal.
     * @param time Waktu keberangkatan.
     * @return BaseResponse yang berisi status dan informasi bus.
     */
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
    /**
     * Mendapatkan bus yang dimiliki oleh akun tertentu.
     *
     * @param accountId ID akun pemilik bus.
     * @return Daftar bus yang dimiliki oleh akun tersebut.
     */
    @GetMapping("/getMyBus")
    public List<Bus> getMyBus(@RequestParam int accountId) {
        return Algorithm.<Bus>collect(getJsonTable(), b->b.accountId==accountId);
    }
    /**
     * Menghitung total jumlah bus yang ada.
     *
     * @return Jumlah total bus.
     */
    @GetMapping("/total")
    public Integer numberOfBuses() {
        return busTable.size();
    }
    /**
     * Mendapatkan daftar bus berdasarkan halaman.
     *
     * @param page Nomor halaman.
     * @param pageSize Jumlah bus per halaman.
     * @return Daftar bus pada halaman yang ditentukan.
     */
    public List<Bus> getBus(@RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value = "size", defaultValue = "10") int pageSize) {
        return Algorithm.<Bus>paginate(getJsonTable(), page, pageSize, b -> b.capacity != 0 && b.schedules != null);
    }
    /**
     * Mendapatkan informasi ketersediaan kursi pada bus untuk tanggal tertentu.
     *
     * @param busId ID bus.
     * @param date Tanggal untuk mengecek ketersediaan kursi.
     * @return Map yang menunjukkan ketersediaan kursi.
     */
    @GetMapping("/seats")
    public Map<String, Boolean> getSeats(@RequestParam int busId, @RequestParam String date){
        Bus bus = Algorithm.<Bus>find(busTable, b -> b.id == busId);
        Timestamp time;
        try {
            time = Timestamp.valueOf(LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid");
        }
        for (Schedule schedule : bus.schedules) {
            if (schedule.departureSchedule.equals(time)) {
                return schedule.seatAvailability;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No schedule found for the given date");
    }
}
