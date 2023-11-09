package com.MuhammadBillieElianJBusRS;

import java.util.*;


public class Validate
{
    public Validate(){
    }
    
    public static ArrayList filter(Price[] list, int value, boolean less){
        ArrayList res = new ArrayList();
        if (less == true){
            for(Price price : list){
                if(price.price <= value){
                    res.add(price.price);
                }
            }
        } else if(less == false){
            for(Price price : list){
                if(price.price >value){
                    res.add(price.price);
                }
            }
        }
        return res;
    }
}
