package MuhammadBillieElianJBusRS;

/**
 * Write a description of class JBus here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class JBus
{
    public static void main (String [] args){
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
