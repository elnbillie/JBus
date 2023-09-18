package MuhammadBillieElianJBusRS;


/**
 * Write a description of class Voucher here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Voucher
{
    String name;
    private boolean used;
    double minimum;
    double cut;
    int code;
    Type type;
    
    public Voucher(String name, int code, Type type, double minimum, double cut){
        this.name=name;
        this.code=code;
        this.type=type;
        this.minimum=minimum;
        this.cut=cut;
        used = false;
    }
    
    public double apply(Price price){
        double harga;
        harga = price.price;
        if(type == Type.DISCOUNT ){
            return harga - (harga*cut/100) ;
        }else{
            return harga-price.rebate;
        }
    }
    
    public boolean isUsed(){
        return used;
    }
    
    public boolean canApply(Price price){
        if(price.price > minimum && used==false){
            return true;
        }else{
            return false;
        }
    }
}
