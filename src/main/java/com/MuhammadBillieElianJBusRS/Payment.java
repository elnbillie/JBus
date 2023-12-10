package com.MuhammadBillieElianJBusRS;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;



/**
 * Kelas yang mewakili pembayaran dalam sistem.
 * Kelas ini mengextend kelas Invoice dan menambahkan detail spesifik untuk pembayaran.
 */
public class Payment extends Invoice
{

    private int busId;
    public Timestamp departureDate;
    public List<String> busSeats;

    /**
     * Konstruktor untuk membuat objek pembayaran.
     *
     * @param buyerId ID pembeli.
     * @param renterId ID penyewa.
     * @param busId ID bus yang dipesan.
     * @param busSeat Daftar kursi bus yang dipesan.
     * @param departureDate Tanggal keberangkatan.
     */



    public Payment(int buyerId, int renterId, int busId, List<String> busSeat, Timestamp departureDate){
        super(buyerId, renterId);
        this.busId = busId;
        this.departureDate = new Timestamp(departureDate.getTime());
        this.busSeats = busSeat;
    }

    /**
     * Konstruktor alternatif untuk membuat objek pembayaran.
     *
     * @param buyer Objek akun pembeli.
     * @param renter Objek penyewa.
     * @param busId ID bus yang dipesan.
     * @param busSeat Daftar kursi bus yang dipesan.
     * @param departureDate Tanggal keberangkatan.
     */
    public Payment(Account buyer, Renter renter, int busId, List<String> busSeat, Timestamp departureDate){
        super(buyer, renter);
        this.busId = busId;
        this.departureDate = new Timestamp(departureDate.getTime());
        this.busSeats = busSeat;
    }

    /**
     * Mengembalikan informasi keberangkatan.
     *
     * @return Informasi keberangkatan dalam format string.
     */

    public String getDepartureInfo(){
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
        return  super.toString() + " Bus ID: " + busId + " Departure Date: " + sdf.format(departureDate.getTime()) +" Bus Seat: " + busSeats;
    }
    /**
     * Mengembalikan ID bus.
     *
     * @return ID bus.
     */
    public int getBusId(){
        return busId;
    }

    /**
     * Membuat pemesanan kursi tunggal pada bus untuk jadwal yang ditentukan.
     *
     * @param departureSchedule Waktu keberangkatan yang dijadwalkan.
     * @param seat Kode kursi yang akan dipesan.
     * @param bus Objek bus tempat kursi akan dipesan.
     * @return true jika pemesanan berhasil, false jika gagal.
     */
    public static boolean makeBooking(Timestamp departureSchedule, String seat, Bus bus) {
        Schedule available = availableSchedule(departureSchedule, seat, bus);
        if (available != null) {
            available.bookSeat(seat);
            return true;
        }
        return false;
    }
    /**
     * Membuat pemesanan beberapa kursi pada bus untuk jadwal yang ditentukan.
     *
     * @param departureSchedule Waktu keberangkatan yang dijadwalkan.
     * @param seats Daftar kode kursi yang akan dipesan.
     * @param bus Objek bus tempat kursi akan dipesan.
     * @return true jika pemesanan berhasil, false jika gagal.
     */
    public static boolean makeBooking(Timestamp departureSchedule, List<String> seats, Bus bus) {
        Schedule availableSchedule = availableSchedule(departureSchedule, seats, bus);
        if (availableSchedule != null) {
            availableSchedule.bookSeat(seats);
            return true;
        }
        return false;
    }
    /**
     * Mencari jadwal yang tersedia pada bus untuk kursi tunggal yang spesifik.
     *
     * @param departureSchedule Waktu keberangkatan yang dijadwalkan.
     * @param seat Kode kursi yang dicari ketersediaannya.
     * @param bus Objek bus yang akan diperiksa.
     * @return Objek Schedule yang tersedia atau null jika tidak ada jadwal yang cocok.
     */
    public static Schedule availableSchedule(Timestamp departureSchedule, String seat, Bus bus) {
        // Menggunakan method `find` dari kelas Algorithm untuk mencari jadwal yang sesuai
        Predicate<Schedule> pred = s -> s.departureSchedule.equals(departureSchedule) && s.isSeatAvailable(seat);
        return Algorithm.find(bus.schedules, pred);
    }
    /**
     * Mencari jadwal yang tersedia pada bus untuk beberapa kursi yang spesifik.
     *
     * @param departureSchedule Waktu keberangkatan yang dijadwalkan.
     * @param seats Daftar kode kursi yang dicari ketersediaannya.
     * @param bus Objek bus yang akan diperiksa.
     * @return Objek Schedule yang tersedia atau null jika tidak ada jadwal yang cocok.
     */
    public static Schedule availableSchedule(Timestamp departureSchedule, List<String> seats, Bus bus) {
        Predicate<Schedule> pred = s -> s.departureSchedule.equals(departureSchedule) && s.isSeatAvailable(seats);
        return Algorithm.find(bus.schedules, pred);
    }

}

