package com.MuhammadBillieElianJBusRS;

import com.MuhammadBillieElianJBusRS.dbjson.Serializable;

/**
 * Mewakili akun dalam sistem.
 */
public class Account extends Serializable {

    public String email;
    public String name;
    public String password;
    public Renter company;
    public double balance;

    // Ekspresi reguler untuk validasi email dan password.
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z_]+?\\.[a-zA-Z.]+[a-zA-Z]+$";
    public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{8,}$";

    /**
     * Membangun Akun baru dengan nama, email, dan password yang ditentukan.
     * Menginisialisasi saldo menjadi 0.0.
     *
     * @param name Nama pemilik akun.
     * @param email Alamat email pemilik akun.
     * @param password Password untuk akun tersebut.
     */
    public Account(String name, String email, String password) {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = 0.0;
    }

    /**
     * Memvalidasi email dan password akun dengan ekspresi reguler yang ditentukan.
     *
     * @return true jika email dan password valid, false jika tidak.
     */
    public boolean validate() {
        return email.matches(REGEX_EMAIL) && password.matches(REGEX_PASSWORD);
    }

    /**
     * Mengembalikan representasi string dari akun.
     *
     * @return Representasi string dari akun termasuk id, nama, email, dan password.
     */
    @Override
    public String toString() {
        return "id=" + super.id + ", name=" + name + ", email=" + email + ", password=" + password;
    }

    /**
     * Menambahkan saldo akun dengan jumlah tertentu.
     * Tidak mengizinkan nilai negatif untuk ditambahkan.
     *
     * @param total Jumlah yang akan ditambahkan ke saldo.
     * @return true jika operasi berhasil, false jika jumlahnya negatif.
     */
    public boolean topUp(double total) {
        if (total < 0) return false;
        balance += total;
        return true;
    }
}
