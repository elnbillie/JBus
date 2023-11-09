package com.MuhammadBillieElianJBusRS;


/**
 * Write a description of class Voucher here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Voucher extends Serializable
{
    public String name;
    private boolean used;
    public double minimum;
    public double cut;
    public int code;
    public Type type;
    
    public Voucher(int id, String name, int code, Type type, double minimum, double cut){
        super();
        this.name=name;
        this.code=code;
        this.type=type;
        this.minimum=minimum;
        this.cut=cut;
        used = false;
    }
    
    public double apply(Price price){
        used = true;
        if(type == Type.DISCOUNT ){
            return price.price - (price.price*cut/100) ;
        }else{
            return price.price-cut;
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
