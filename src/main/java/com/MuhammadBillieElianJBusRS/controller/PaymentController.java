package com.MuhammadBillieElianJBusRS.controller;

import com.MuhammadBillieElianJBusRS.Price;
import com.MuhammadBillieElianJBusRS.Account;
import com.MuhammadBillieElianJBusRS.Bus;
import com.MuhammadBillieElianJBusRS.Payment;
import com.MuhammadBillieElianJBusRS.dbjson.JsonAutowired;
import com.MuhammadBillieElianJBusRS.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;
import com.MuhammadBillieElianJBusRS.Algorithm;
import java.util.List;
import java.text.SimpleDateFormat;
import com.MuhammadBillieElianJBusRS.Renter;

@RestController
@RequestMapping("/makeBooking")
public class PaymentController implements BasicGetController<Payment>{

    public static @JsonAutowired(value = Payment.class, filepath = "\\src\\main\\java\\com\\MuhammadBillieElianJBusRS\\json\\payment.json") JsonTable<Payment> paymentTable;
    @Override
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }
    @RequestMapping(value="/makeBooking",method= RequestMethod.POST)
    public BaseResponse<Payment> makeBooking(
            @RequestParam int buyerId,
            @RequestParam int renterId,
            @RequestParam int busId,
            @RequestParam List<String> busSeats,
            @RequestParam String departureDate

    ){
        Account buyer = Algorithm.<Account>find(AccountController.accountTable, pred-> pred.id == buyerId);
        Bus bus = Algorithm.<Bus>find(BusController.busTable, pred-> pred.id == busId);
        Account renterAccount = Algorithm.<Account>find(AccountController.accountTable, pred-> pred.id == renterId);
        Renter renter = renterAccount.company;


        if (buyer == null || bus == null) {
            return new BaseResponse<>(false, "Buyer or Bus cannot be null", null);
        }

        if (bus.price.price > buyer.balance) {
            return new BaseResponse<>(false, "Insufficient balance", null);
        }

        // Check if there is a schedule for the given departure date
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");
        java.sql.Timestamp departureTimestamp;
        try {
            departureTimestamp = java.sql.Timestamp.valueOf(departureDate);
        } catch (IllegalArgumentException e) {
            return new BaseResponse<>(false, "Invalid departure date format", null);
        }

        boolean bookingMade = Payment.makeBooking(departureTimestamp, busSeats, bus);
        if (!bookingMade) {
            return new BaseResponse<>(false, "Booking failed", null);
        }

        buyer.balance -= bus.price.price;

        Payment payment = new Payment(buyerId, buyer, renter, busId, busSeats, departureTimestamp);
        payment.status = Payment.PaymentStatus.WAITING;
        paymentTable.add(payment);

        return new BaseResponse<>(true, "Booking Suceed", payment);
    }

    @RequestMapping(value="/{id}/accept", method=RequestMethod.POST)
    public BaseResponse<Payment> accept(@PathVariable int id){
        Payment payment = Algorithm.<Payment>find(paymentTable, p -> p.id == id);
        if (payment == null) {
            return new BaseResponse<>(false, "Payment can't be found", null);
        }
        if (payment.status != Payment.PaymentStatus.WAITING) {
            return new BaseResponse<>(false, "Status payment isn't waiting", null);
        }
        payment.status = Payment.PaymentStatus.SUCCESS;
        return new BaseResponse<>(true, "Payment accepted successfully", payment);
    }

    @RequestMapping(value="/{id}/cancel", method=RequestMethod.POST)
    public BaseResponse<Payment> cancel(@PathVariable int id) {
        Payment payment = Algorithm.<Payment>find(paymentTable, p -> p.id == id);
        if (payment == null) {
            return new BaseResponse<>(false, "Payment isn't found", null);
        }
        if (payment.status != Payment.PaymentStatus.WAITING) {
            return new BaseResponse<>(false, "Payment status isn't waiting", null);
        }
        payment.status = Payment.PaymentStatus.FAILED;
        return new BaseResponse<>(true, "Payment has been canceled", payment);
    }
}
