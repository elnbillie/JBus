package MuhammadBillieElianJBusRS;

public class Renter extends Serializable {

    public static final String REGEX_PHONE = "^\\d{9,12}$";
    public static final String REGEX_NAME = "^[A-Z][a-zA-Z0-9_]{4,19}$";

    public String address;
    public String companyName;
    public int phoneNumber;

    public Renter(int id, String companyName) {
        super();
        this.companyName = companyName;
        this.phoneNumber = 0;
        this.address = "";
    }

    public Renter(int id, String companyName, String address) {
        super();
        this.companyName = companyName;
        this.address = address;
        this.phoneNumber = 0;
    }

    public Renter(int id, String companyName, int phoneNumber) {
        super();
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = "";
    }

    public Renter(int id, String companyName, int phoneNumber, String address) {
        super();
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public boolean validate() {
        String phoneString = Integer.toString(phoneNumber);
        return companyName.matches(REGEX_NAME) && phoneString.matches(REGEX_PHONE);
    }
}