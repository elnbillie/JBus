package com.MuhammadBillieElianJBusRS;


import com.MuhammadBillieElianJBusRS.dbjson.Serializable;

/**
 * Write a description of class Review here.
 *
 * @author Muhammad Billie Elian
 * @version (a version number or a date)
 */

/**
 * Kelas yang merepresentasikan ulasan dari pengguna terhadap layanan.
 * Kelas ini menyimpan tanggal dan deskripsi ulasan.
 */
public class Review extends Serializable
{
    public String date;
    public String desc;

    /**
     * Konstruktor untuk membuat objek ulasan.
     *
     * @param id ID ulasan.
     * @param date Tanggal ulasan dibuat.
     * @param desc Deskripsi atau isi dari ulasan.
     */
    
    public Review(int id, String date, String desc){
        super();
        this.date=date;
        this.desc=desc;
    }
    /**
     * Mengembalikan representasi string dari objek ulasan.
     *
     * @return Representasi string yang berisi ID, tanggal, dan deskripsi ulasan.
     */
    public String toString() {
        return "id=" + super.id + ", date=" + date + ", description=" + desc;
    }
}
