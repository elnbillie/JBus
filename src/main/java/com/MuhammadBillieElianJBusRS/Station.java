package com.MuhammadBillieElianJBusRS;


import com.MuhammadBillieElianJBusRS.dbjson.Serializable;


/**
 * Kelas yang merepresentasikan stasiun dalam sistem.
 * Kelas ini menyimpan informasi tentang nama stasiun, kota, dan alamat stasiun.
 */
public class Station extends Serializable
{
    public City city;
    public String stationName;
    public String address;

    /**
     * Konstruktor untuk membuat objek stasiun dengan nama stasiun, kota, dan alamat.
     *
     * @param stationName Nama stasiun.
     * @param city Objek kota tempat stasiun berada.
     * @param address Alamat stasiun.
     */
    public Station( String stationName, City city, String address){
        super();
        this.stationName=stationName;
        this.city = city;
        this.address=address;
    }

    /**
     * Mengembalikan representasi string dari objek stasiun.
     *
     * @return Representasi string yang berisi ID, nama kota, nama stasiun, dan alamat stasiun.
     */
    
    public String toString(){
    return "Id : " + id + " City : " + city + " Station Name : " + stationName + " adress" + address;
    }
}
