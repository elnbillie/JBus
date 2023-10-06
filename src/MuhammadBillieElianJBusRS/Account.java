package MuhammadBillieElianJBusRS;



public class Account extends Serializable implements FileParser{
    
    public String email;
    public String name;
    public String password;
    
    public Account (int id, String name, String email, String password){ 
        super();
        this.name=name;
        this.email=email;
        this.password=password;
    }
    
    @Override
    public Object write() {
        return this;  
    }
    
    @Override
    public boolean read(String input) {
        return true;  
    }
    
    public String toString() {
        return "id=" + super.id +", name=" + name + ", email=" + email +", password=" + password ;
    }
    
}

