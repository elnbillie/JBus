package MuhammadBillieElianJBusRS;

/**
 * Write a description of class JBus here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class JBus
{
    
    public static void main(String[] args) {
        
        Price[] unfilteredArray = new Price[5];
        
        for(int i=0; i < unfilteredArray.length; i++){
            int j =5000;
            unfilteredArray[i]= new Price((i+1)*j);
        }
        System.out.println("Price List");
        for(Price price : unfilteredArray){
            System.out.println(price.price);
        }
        System.out.println("Below 12000.0");
        System.out.println(Validate.filter(unfilteredArray,12000,true));
        System.out.println("Above 10000.0");
        System.out.println(Validate.filter(unfilteredArray,10000, false));
        
        Bus testBus = createBus();        
        Payment testPayment = new Payment(1, 1, 1, testBus.id, "S1");
        System.out.println(testPayment.getDepartureInfo());
        System.out.println(testPayment.getTime());
        // Tes Schedule
        Calendar schedule1 = Calendar.getInstance();
        testBus.addSchedule(schedule1);
        Calendar schedule2 = Calendar.getInstance();
        schedule2.add(Calendar.DAY_OF_MONTH, 3);
        testBus.addSchedule(schedule2);
            for(Schedule s: testBus.schedules){
            testBus.printSchedule(s);
        }
    }
    
    public static Bus createBus(){
        // Bus bus = new Bus(1, "Bus", Facility.AC, newprice, 50);
        Station testDeparture = new Station(2, "Depok Terminal", City.DEPOK, "Jl. Margonda Raya");
        Station testArrival = new Station(3, "Halte UI", City.JAKARTA, "Universitas Indonesia");
        Price price = new Price(100000, 20000);
        Bus testBus = new Bus(1, "Busway", Facility.AC, price, 25, BusType.REGULER, City.DEPOK, testDeparture, testArrival);       
        return testBus;
    }
}
        
        
        
        
        
        
        
        
        
        
        
        /*Review testReview = new Review(1, "23 August 2023", "Bad Quality");
        Price testPrice = new Price(100000, 20000);
        Station testDeparture = new Station(2, "Depok Terminal", City.DEPOK, "JL. Margonda Raya");
        Station testArrival = new Station(3, "Halte UI", City.JAKARTA, "Universitas Indonesia");
        Bus testBus = new Bus(1, "Busway", Facility.AC, testPrice, 50, BusType.REGULER, City.DEPOK, testDeparture, testArrival);
        Account testAccount = new Account(1, "Bob", "bob@gmail.com", "bob");
        Rating testRating = new Rating();
        System.out.println(testReview);
        System.out.println(testBus);
        System.out.println(testAccount);
        System.out.println(testPrice);
        System.out.println(testRating);*/
        
        
        
    /*public static void main (String [] args){
        Bus testBus = createBus();
        System.out.println(testBus.name);
        System.out.println(testBus.facility);
        System.out.println(testBus.price.price);
        System.out.println(testBus.capacity);

    }
    
    public static Bus createBus(){
        Price price = new Price(750000, 5);
        Bus bus = new Bus("Netlab Bus",Facility.LUNCH, price, 25);
        return bus;
    }
    
    /*
    public static int getBusId(){
        return 0;
    }
    
    public static String getBusName(){
        return "Bus";
    }
    
    public static boolean isDiscount (){
        return true;
    }
    
    public static float getDiscountedPrice(int price, float discountPercentage){
        float harga;
        harga = (float)price * discountPercentage;
        return harga;
    }
    
    public static float getDiscountPercentage(int beforeDiscount, int afterDiscount){
        float temp;
        if (afterDiscount == beforeDiscount){
            return 0.0f;
        }
        else if (beforeDiscount < afterDiscount){
            return 0.0f;
        }else {
            temp = 100-(((float)afterDiscount/beforeDiscount)*100);
            return temp;
        }
    }
    
    public static int getOriginalPrice(int discountedPrice, float discountPercentage){
        float hasil;
        hasil = (100/(100-discountPercentage))*discountedPrice;
        return (int)hasil;
    }
    
    public static float getAdminFeePercentage(){
        return 0.05f;
    }
    
    public static int getAdminFee(int price){
        return (int)((float)price*getAdminFeePercentage());
    }
    
    public static int getTotalPrice(int price, int numberOfSeat){
        int temp;
        temp = (int)((float)(price*numberOfSeat)+getAdminFeePercentage());
        return temp;
        
    }*/


