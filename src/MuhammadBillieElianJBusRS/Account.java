package MuhammadBillieElianJBusRS;



public class Account extends Serializable {
    
    public String email;
    public String name;
    public String password;
    
    public Account (int id, String name, String email, String password){ 
        super();
        this.name=name;
        this.email=email;
        this.password=password;
    }
    

    
    public String toString() {
        return "id=" + super.id +", name=" + name + ", email=" + email +", password=" + password ;
    }
    
}

