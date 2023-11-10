package com.MuhammadBillieElianJBusRS;


import com.MuhammadBillieElianJBusRS.dbjson.Serializable;

public class Account extends Serializable {
    
    public String email;
    public String name;
    public String password;
    public Renter company;
    public double balance;

    public static final String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z_]+?\\.[a-zA-Z.]+[a-zA-Z]+$";
    public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{8,}$";

    
    public Account ( String name, String email, String password){
        super();
        this.name=name;
        this.email=email;
        this.password=password;
        this.balance=0.0;
    }

    public boolean validate() {
        return email.matches(REGEX_EMAIL) && password.matches(REGEX_PASSWORD);
    }
    
    public String toString() {
        return "id=" + super.id +", name=" + name + ", email=" + email +", password=" + password ;
    }

    public boolean topUp(double total){
        if(total < 0) return false;
        balance+=total;
        return true;
    }
}

