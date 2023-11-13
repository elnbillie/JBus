package com.MuhammadBillieElianJBusRS;


import com.MuhammadBillieElianJBusRS.dbjson.Serializable;

public class Station extends Serializable
{
    public City city;
    public String stationName;
    public String address;
    
    public Station( String stationName, City city, String address){
        super();
        this.stationName=stationName;
        this.city = city;
        this.address=address;
    }
    
    public String toString(){
    return "Id : " + id + " City : " + city + " Station Name : " + stationName + " adress" + address;
    }
}
