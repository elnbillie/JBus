package com.MuhammadBillieElianJBusRS.controller;

import com.MuhammadBillieElianJBusRS.Predicate;
import com.MuhammadBillieElianJBusRS.Renter;
import com.MuhammadBillieElianJBusRS.Account;
import com.MuhammadBillieElianJBusRS.Algorithm;
import com.MuhammadBillieElianJBusRS.dbjson.JsonAutowired;
import com.MuhammadBillieElianJBusRS.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account>
{
    @JsonAutowired(value = Account.class, filepath = "D:\\Kuliah\\Semester 3\\OOP\\JBus\\JBus\\src\\main\\java\\com\\MuhammadBillieElianJBusRS\\json\\account_db.json")
    public static JsonTable<Account> accountTable;

    /**
     * Mengembalikan JsonTable untuk Akun.
     *
     * @return JsonTable yang berisi akun-akun.
     */
    public JsonTable<Account> getJsonTable() {
        return accountTable;
    }


    /**
     * Menampilkan halaman index untuk Account.
     *
     * @return String yang menunjukkan halaman account.
     */
    @GetMapping
    String index() { return "account page"; }


    /**
     * Melakukan registrasi akun baru.
     *
     * @param name Nama pengguna.
     * @param email Email pengguna.
     * @param password Password pengguna.
     * @return BaseResponse yang berisi status dan informasi akun yang terdaftar.
     */
    @PostMapping("/register")
    BaseResponse<Account> register
            (
                    @RequestParam String name,
                    @RequestParam String email,
                    @RequestParam String password
            )
    {

        Predicate<Account> s = (val) -> val.email.equals(email);

        String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{8,}$";
        String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z_]+?\\.[a-zA-Z.]+[a-zA-Z]+$";
        Pattern patternPassword = Pattern.compile(REGEX_PASSWORD);
        Pattern patternEmail = Pattern.compile(REGEX_EMAIL);
        Matcher matchpw = patternPassword.matcher(password);
        Matcher matchmail = patternEmail.matcher(email);

        if (name.isBlank() == false && matchpw.find() && matchmail.find() && Algorithm.exists(accountTable,s) == false) {
            String passwordToHash = password;
            String CryptedPassword = null;

            try
            {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(passwordToHash.getBytes());
                byte[] bytes = md.digest();
                StringBuilder tempString = new StringBuilder();
                for (int i = 0;i < bytes.length;i++) {
                    tempString.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(0));
                }
                CryptedPassword = tempString.toString();
            }
            catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            Account acc =  new Account(name, email, CryptedPassword);
            accountTable.addElement(acc);
            return new BaseResponse<>(true, "Register Berhasil", acc);
        }
        return new BaseResponse<>(false, "Register Gagal", null);

    }

    /**
     * Melakukan proses login.
     *
     * @param email Email pengguna.
     * @param password Password pengguna.
     * @return BaseResponse yang berisi status dan informasi akun jika login berhasil.
     */
    @PostMapping("/login")
    BaseResponse<Account> login (@RequestParam String email,
                                 @RequestParam String password) {
        String passwordToHash = password;
        String CryptedPassword = null;

        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(passwordToHash.getBytes());

            byte[] bytes = md.digest();

            StringBuilder tempString = new StringBuilder();
            for (int i = 0;i < bytes.length;i++) {
                tempString.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(0));
            }

            CryptedPassword = tempString.toString();

        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        for (Account i : accountTable) {
            if (i.password.equals(CryptedPassword) && i.email.equals(email))
                return new BaseResponse<>(true, "Berhasil login", i);
        }
        return new BaseResponse<>(false, "Gagal login ngab", null);
    }

    /**
     * Registrasi penyewa (Renter) untuk akun tertentu.
     *
     * @param id ID akun yang akan didaftarkan sebagai penyewa.
     * @param companyName Nama perusahaan penyewa.
     * @param address Alamat penyewa.
     * @param phoneNumber Nomor telepon penyewa.
     * @return BaseResponse yang berisi status dan informasi renter jika registrasi berhasil.
     */
    @PostMapping ("/{id}/registerRenter")
    BaseResponse<Renter> registerRenter (@PathVariable int id,
                                         @RequestParam String companyName,
                                         @RequestParam String address,
                                         @RequestParam String phoneNumber)
    {
        for (Account i : accountTable) {
            if (i.id == id && i.company == null){
                i.company = new Renter(companyName, phoneNumber, address);
                return new BaseResponse<>(true, "Berhasil register renter", i.company);
            }
        }
        return new BaseResponse<>(false, "Gagal register renter", null);
    }

    /**
     * Menambahkan saldo ke akun tertentu.
     *
     * @param id ID akun yang akan ditambahkan saldo.
     * @param amount Jumlah saldo yang akan ditambahkan.
     * @return BaseResponse yang berisi status dan jumlah saldo yang ditambahkan jika berhasil.
     */
    @PostMapping("/{id}/topUp")
    BaseResponse<Double> topUp(@PathVariable int id, @RequestParam double amount)
    {
        for (Account i : accountTable) {
            if (i.id == id){
                if (i.topUp(amount)){
                    return new BaseResponse<>(true, "Top up sukses", amount);
                }
            }
        }
        return new BaseResponse<>(false, "Top up gagal", null);
    }

}
