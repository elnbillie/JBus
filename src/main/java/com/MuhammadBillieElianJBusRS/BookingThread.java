package com.MuhammadBillieElianJBusRS;

import java.sql.Timestamp;

/**
 * Thread yang bertanggung jawab untuk menangani proses pemesanan tempat duduk pada bus.
 */
public class BookingThread extends Thread {
    private Bus bus;
    private Timestamp timestamp;
    private String seatCode = "RS01";

    /**
     * Membuat thread pemesanan baru dengan nama, bus, dan timestamp yang ditentukan.
     *
     * @param name Nama thread.
     * @param bus Objek bus yang akan dipesan.
     * @param timestamp Waktu pemesanan.
     */
    public BookingThread(String name, Bus bus, Timestamp timestamp) {
        super(name);
        this.bus = bus;
        this.timestamp = timestamp;
        this.start();
    }

    /**
     * Metode yang dijalankan saat thread dimulai.
     * Menangani proses pemesanan tempat duduk di bus.
     */
    @Override
    public void run() {
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
