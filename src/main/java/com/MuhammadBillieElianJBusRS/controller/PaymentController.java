package com.MuhammadBillieElianJBusRS.controller;

import com.MuhammadBillieElianJBusRS.*;
import com.MuhammadBillieElianJBusRS.dbjson.JsonAutowired;
import com.MuhammadBillieElianJBusRS.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.text.SimpleDateFormat;

/**
 * Controller untuk mengelola operasi yang berkaitan dengan pembayaran.
 * Menyediakan fungsi untuk membuat pemesanan, menerima, dan membatalkan pembayaran.
 */
@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment>{

    @JsonAutowired(value = Payment.class, filepath = "D:\\Kuliah\\Semester 3\\OOP\\JBus\\JBus\\src\\main\\java\\com\\MuhammadBillieElianJBusRS\\json\\payment.json")
    public static JsonTable<Payment> paymentTable;
    /**
     * Mengembalikan JsonTable yang berisi pembayaran.
     *
     * @return JsonTable yang berisi pembayaran.
     */
    @Override
    public JsonTable<Payment> getJsonTable() {
        return PaymentController.paymentTable;
    }
    /**
     * Melakukan pemesanan.
     *
     * @param buyerId ID pembeli.
     * @param renterId ID penyewa.
     * @param busId ID bus.
     * @param busSeats Daftar kursi yang dipesan.
     * @param departureDate Tanggal keberangkatan.
     * @return BaseResponse yang berisi status dan informasi pembayaran.
     */
    @RequestMapping(value="/makeBooking", method= RequestMethod.POST)
    public BaseResponse<Payment> makeBooking(
            @RequestParam int buyerId,
            @RequestParam int renterId,
            @RequestParam int busId,
            @RequestParam List<String> busSeats,
            @RequestParam String departureDate
    )
    {
        Account buyer = Algorithm.<Account>find(new AccountController().getJsonTable(), t->t.id == buyerId);
        Bus bus = Algorithm.<Bus>find(new BusController().getJsonTable(), t->t.id == busId);

        if (buyer == null || bus == null) {
            return new BaseResponse<>(false, "Pembeli atau Bus tidak ditemukan", null);
        }

        if (buyer.balance < (bus.price.price * busSeats.size())) {
            return new BaseResponse<>(false, "Balance kurang", null);
        }

        Timestamp departureTimestamp;
        try {
            departureTimestamp = Timestamp.valueOf(LocalDateTime.parse(departureDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        } catch (DateTimeParseException e) {
            return new BaseResponse<>(false, "Invalid", null);
        }

        boolean bookingMade = Payment.makeBooking(departureTimestamp, busSeats, bus);
        if (!bookingMade) {
            return new BaseResponse<>(false, "Booking Suceed", null);
        }

        buyer.balance -= (bus.price.price * busSeats.size());

        Payment payment = new Payment(buyerId, renterId, busId, busSeats, departureTimestamp);
        payment.status = Payment.PaymentStatus.WAITING;
        paymentTable.add(payment);

        return new BaseResponse<>(true, "Booking Suceed", payment);
    }
    /**
     * Menerima pembayaran.
     *
     * @param id ID pembayaran.
     * @return BaseResponse yang berisi status dan informasi pembayaran yang diterima.
     */

    @RequestMapping(value="/{id}/accept", method=RequestMethod.POST)
    public BaseResponse<Payment> accept(@PathVariable int id){
        Payment payment = Algorithm.<Payment>find(paymentTable, p -> p.id == id);
        Account renter = Algorithm.<Account>find(new AccountController().getJsonTable(), t->t.id == payment.renterId);
        Bus bus = Algorithm.<Bus>find(BusController.busTable, b -> b.id == payment.getBusId());

        if (payment == null) {
            return new BaseResponse<>(false, "Payment tidak ditemukan", null);
        }

        if (payment.status != Payment.PaymentStatus.WAITING) {
            return new BaseResponse<>(false, "Status payment bukan waiting", null);
        }

        renter.balance += (payment.busSeats.size() * bus.price.price);
        payment.status = Payment.PaymentStatus.SUCCESS;

        return new BaseResponse<>(true, "Payment accepted successfully", payment);
    }
    /**
     * Membatalkan pembayaran.
     *
     * @param id ID pembayaran.
     * @return BaseResponse yang berisi status dan informasi pembayaran yang dibatalkan.
     */
    @RequestMapping(value="/{id}/cancel", method=RequestMethod.POST)
    public BaseResponse<Payment> cancel(@PathVariable int id){
        Payment payment = Algorithm.<Payment>find(paymentTable, p -> p.id == id);
        Account buyer = Algorithm.<Account>find(AccountController.accountTable, a -> a.id == payment.buyerId);
        Bus bus = Algorithm.<Bus>find(BusController.busTable, b -> b.id == payment.getBusId());

        if (payment == null) {
            return new BaseResponse<>(false, "Payment tidak ditemukan", null);
        }

        if (payment.status != Payment.PaymentStatus.WAITING) {
            return new BaseResponse<>(false, "Status payment bukan waiting", null);
        }

        payment.status = Payment.PaymentStatus.FAILED;
        buyer.balance += (bus.price.price * payment.busSeats.size());

        Schedule scheduleToCancel = Algorithm.<Schedule>find(bus.schedules, s -> s.departureSchedule.equals(payment.departureDate));
        if (scheduleToCancel != null) {
            for(String seat : payment.busSeats){
                scheduleToCancel.seatAvailability.put(seat, true);
            }
        }

        return new BaseResponse<>(true, "Payment telah di cancel", payment);
    }

    @GetMapping("/getRenterPayment")
    public List<Payment> getRenterPayment(@RequestParam int renterId
    ) {
        return Algorithm.<Payment>collect(getJsonTable(), b->b.renterId == renterId);
    }

    /**
     * mengembalikan nilai payment
     *
     * @param accountId ID dari akun.
     * @return List dari payment
     */
    @GetMapping("/getAccountPayment")
    public List<Payment> getBuyerPayment(@RequestParam int accountId
    ) {
        return Algorithm.<Payment>collect(getJsonTable(), b->b.buyerId == accountId);
    }
}
