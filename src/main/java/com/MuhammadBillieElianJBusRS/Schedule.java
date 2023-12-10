package com.MuhammadBillieElianJBusRS;

import java.util.*;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.Calendar;


/**
 * Kelas yang merepresentasikan jadwal keberangkatan bus serta ketersediaan kursi.
 */
public class Schedule
{
    public Timestamp departureSchedule;
    public Map<String, Boolean> seatAvailability = new LinkedHashMap<>();

    /**
     * Konstruktor untuk membuat objek jadwal dengan waktu keberangkatan dan jumlah kursi.
     *
     * @param departureSchedule Waktu keberangkatan.
     * @param numberOfSeats Jumlah total kursi pada bus.
     */
    
    public Schedule(Timestamp departureSchedule, int numberOfSeats){
        this.departureSchedule = departureSchedule;
        initializeSeatAvailability(numberOfSeats);
    }

    /**
     * Menginisialisasi ketersediaan kursi berdasarkan jumlah kursi.
     *
     * @param numberOfSeats Jumlah total kursi yang akan diinisialisasi.
     */
    private void initializeSeatAvailability(int numberOfSeats){
        for (int seatNumber = 1; seatNumber <= numberOfSeats; seatNumber++) {
            String sn = seatNumber < 10 ? "0"+seatNumber : ""+seatNumber;
            seatAvailability.put("RS" + sn, true);
        }
    }

    /**
     * Memeriksa apakah kursi tertentu tersedia.
     *
     * @param seat Kode kursi yang akan diperiksa.
     * @return true jika kursi tersedia, false jika tidak.
     */
      
    public boolean isSeatAvailable(String seat){
        return seatAvailability.containsKey(seat) && seatAvailability.get(seat);
    }

    /**
     * Memeriksa apakah sejumlah kursi tertentu tersedia.
     *
     * @param seats Daftar kode kursi yang akan diperiksa.
     * @return true jika semua kursi tersedia, false jika ada satu pun yang tidak tersedia.
     */
    public boolean isSeatAvailable(List<String> seats) {
        for (String seat : seats) {
            if (!isSeatAvailable(seat)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Melakukan pemesanan untuk satu kursi.
     *
     * @param seat Kode kursi yang akan dipesan.
     */
    public void bookSeat(String seat) {
        if (seatAvailability.containsKey(seat)) {
            seatAvailability.put(seat, false);
        }
    }

    /**
     * Melakukan pemesanan untuk beberapa kursi sekaligus.
     *
     * @param seats Daftar kode kursi yang akan dipesan.
     */
    public void bookSeat(List<String> seats) {
        for (String seat : seats) {
            bookSeat(seat);
        }
    }

    /**
     * Mencetak jadwal termasuk waktu keberangkatan dan status ketersediaan kursi.
     */
    
    public void printSchedule() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");
        String formattedDepartureSchedule = dateFormat.format(this.departureSchedule);
        System.out.println("Tanggal keberangkatan: " + formattedDepartureSchedule);
        
        System.out.println("Daftar kursi dan ketersediaan kursinya: ");
        int maxSeatsPerRow = 4; // Assuming there are 4 seats per row
        int currentSeat = 1;
        for (String seat : this.seatAvailability.keySet()) {
            String symbol = this.seatAvailability.get(seat) ? "O" : "X";
            System.out.print(seat + " : " + symbol + "\t");
            if (currentSeat % maxSeatsPerRow == 0) {
                System.out.println();
            }
            currentSeat++;
        }
        System.out.println("\n");
    }

    /**
     * Mengembalikan representasi string dari objek jadwal.
     *
     * @return Representasi string dari jadwal.
     */
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        String formattedDepartureSchedule = dateFormat.format(this.departureSchedule.getTime());
        return "Schedule\t: " + formattedDepartureSchedule + "\nOccupied\t: " + Algorithm.count(seatAvailability.values(), false) + "/" + seatAvailability.size();
    }


}
