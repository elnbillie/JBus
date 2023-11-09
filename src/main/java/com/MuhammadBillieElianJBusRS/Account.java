package com.MuhammadBillieElianJBusRS;


public class Account extends Serializable {
    
    public String email;
    public String name;
    public String password;

    public static final String REGEX_EMAIL = "^[A-Za-z0-9]+@[A-Za-z]+\\.[A-Za-z]{2,}$";
    public static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

    
    public Account ( String name, String email, String password){
        super();
        this.name=name;
        this.email=email;
        this.password=password;
    }

    public boolean validate() {
        return email.matches(REGEX_EMAIL) && password.matches(REGEX_PASSWORD);
    }
    
    public String toString() {
        return "id=" + super.id +", name=" + name + ", email=" + email +", password=" + password ;
    }
    
}

