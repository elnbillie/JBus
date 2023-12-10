package com.MuhammadBillieElianJBusRS.controller;


/**
 * Kelas generik untuk memberikan respon dasar pada operasi yang dilakukan.
 * Kelas ini menyediakan struktur untuk mengirimkan status berhasil atau gagal, pesan, dan payload data.
 *
 * @param <T> Tipe data dari payload yang akan dikirim sebagai bagian dari respon.
 */
public class BaseResponse<T> {
    public boolean success;
    public String message;
    public T payload;

    /**
     * Konstruktor untuk membuat objek BaseResponse.
     *
     * @param success Status keberhasilan operasi (true/false).
     * @param message Pesan yang menyertai respon.
     * @param payload Payload data yang akan dikirimkan sebagai bagian dari respon.
     */
    public BaseResponse(boolean success, String message, T payload) {
        this.success = success;
        this.message = message;
        this.payload = payload;
    }
}
