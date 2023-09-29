package MuhammadBillieElianJBusRS;

import java.util.*;

public class Schedule
{
    public Calendar departureSchedule;
    public Map<String, Boolean> seatAvailability;
    
    
    public Schedule(Calendar departureSchedule, int numberOfSeats){
        this.departureSchedule = departureSchedule;
        initializeSeatAvailability(numberOfSeats);
    }
    
    private void initializeSeatAvailability(int numberOfSeats){
        seatAvailability = new LinkedHashMap<>();
        
        for (int seatNumber = 1; seatNumber <= numberOfSeats; seatNumber++) {
            seatAvailability.put("RS" + seatNumber, true);
        }
    }
}
