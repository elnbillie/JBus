package MuhammadBillieElianJBusRS;

/**
 * Write a description of class JBus here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class JBus
{
    
    public static void main(String[] args) {
        Review testReview = new Review(1, "23 August 2023", "Bad Quality");
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
        System.out.println(testRating);
        
        
        
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
}
}
