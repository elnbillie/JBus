package com.MuhammadBillieElianJBusRS;


/**
 * Write a description of class Price here.
 *
 * @author Muhammad Billie ELian
 * @version (a version number or a date)
 */

/**
 * Kelas yang merepresentasikan harga dan potongan harga (rebate) untuk suatu item atau layanan.
 */
public class Price{
    public double rebate;
    public double price;

    /**
     * Membuat objek Price dengan harga dan potongan harga yang ditentukan.
     *
     * @param price Harga asli item atau layanan.
     * @param rebate Potongan harga yang akan diterapkan.
     */


    public Price (double price, double rebate){
        this.rebate=rebate;
        this.price=price;
    }
    /**
     * Membuat objek Price tanpa potongan harga.
     *
     * @param price Harga asli item atau layanan.
     */
    public Price(double price){
        this.price=price;
    }


    /**
     * Mengembalikan representasi string dari objek Price.
     *
     * @return Representasi string yang menggambarkan harga dan potongan harga.
     */
    public String toString() {
        return "rebate=" + rebate + ", price=" + price;
    }
    

}
