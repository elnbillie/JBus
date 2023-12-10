package com.MuhammadBillieElianJBusRS.controller;


import com.MuhammadBillieElianJBusRS.City;
import com.MuhammadBillieElianJBusRS.Station;
import com.MuhammadBillieElianJBusRS.dbjson.JsonAutowired;
import com.MuhammadBillieElianJBusRS.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller untuk mengelola operasi yang berkaitan dengan stasiun.
 * Menyediakan fungsi untuk membuat dan mendapatkan informasi stasiun.
 */
@RestController
@RequestMapping("/station")
public class StationController implements BasicGetController<Station> {
    public static @JsonAutowired(value = Station.class, filepath = "D:\\Kuliah\\Semester 3\\OOP\\JBus\\JBus\\src\\main\\java\\com\\MuhammadBillieElianJBusRS\\json\\station.json") JsonTable<Station> stationTable;
    /**
     * Mengembalikan JsonTable yang berisi stasiun.
     *
     * @return JsonTable yang berisi stasiun.
     */
    @Override
    public JsonTable<Station> getJsonTable() {
        return stationTable;
    }
    /**
     * Membuat stasiun baru.
     *
     * @param stationName Nama stasiun.
     * @param city Nama kota tempat stasiun berada.
     * @param address Alamat stasiun.
     * @return BaseResponse yang berisi status dan informasi stasiun yang dibuat.
     */
    @PostMapping("/create")
    public BaseResponse<Station> createStation(
            @RequestParam String stationName,
            @RequestParam String city,
            @RequestParam String address
    ) {
        try {
            // Validate parameters
            if (stationName.isBlank() || city.isBlank() || address.isBlank()) {
                return new BaseResponse<>(false, "Parameter values cannot be blank or null", null);
            }

            // Validate city as a valid enum value
            City cityEnum = City.valueOf(city.toUpperCase());

            // Create a new station using the provided details
            Station newStation = new Station(stationName, cityEnum, address);

            // Add the new station to the stationTable
            stationTable.add(newStation);

            //Success response message
            return new BaseResponse<>(true, "Station added successfully", newStation);
        } catch (IllegalArgumentException e) {
            // Handle invalid enum value
            return new BaseResponse<>(false, "Invalid city value", null);
        } catch (Exception e) {
            // Handle other unexpected errors
            return new BaseResponse<>(false, "An error occurred while adding the station", null);
        }
    }
    /**
     * Mendapatkan semua stasiun.
     *
     * @return Daftar semua stasiun yang ada.
     */
    @GetMapping("/getAll")
    public List<Station> getAllStation() { return getJsonTable();}
}
