package MuhammadBillieElianJBusRS;


/**
 * Write a description of class Price here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
class Price{
    double rebate;
    double price;
    int discount;

    public Price(double price){
        this.price = price;
        this.discount = 0;
        this.rebate = 0;
    }

    public Price(double price, int discount){
        this.price = price;
        this.discount = discount;
        this.rebate = 0;
    }
    
    public Price(double price, double rebate){
        this.price = price;
        this.discount = 0;
        this.rebate = rebate;
    }
    
    private double getDiscountedPrice(){
        if(discount>100.0){
            return 0.0;
        }else if(discount==100.0){
            return 0.0;
        }else{
            price=price-(price*discount);
            return price;
            //temp_disc=(int)this.price-((int)this.price*this.discount);
            //return temp_disc;
        }
    }
    
    private double getRebatedPrice(){
        //double temp_rebate;
        //temp_rebate=this.price-this.rebate;
        price=price-rebate;
        if (rebate>price){
            return 0;
        }else{
            return price;
        }
    }
}
