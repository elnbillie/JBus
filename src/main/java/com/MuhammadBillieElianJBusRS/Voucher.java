package com.MuhammadBillieElianJBusRS;


import com.MuhammadBillieElianJBusRS.dbjson.Serializable;

/**
 * Write a description of class Voucher here.
 *
 * @author Muhammad Billie Elian
 */

/**
 * Kelas yang merepresentasikan voucher dalam sistem.
 * Voucher dapat digunakan untuk mendapatkan diskon atau potongan harga.
 */
public class Voucher extends Serializable
{
    public String name;
    private boolean used;
    public double minimum;
    public double cut;
    public int code;
    public Type type;


    /**
     * Konstruktor untuk membuat objek voucher.
     *
     * @param id ID voucher.
     * @param name Nama voucher.
     * @param code Kode voucher.
     * @param type Tipe voucher (Diskon atau Potongan langsung).
     * @param minimum Harga minimum untuk dapat menggunakan voucher.
     * @param cut Nilai potongan atau persentase diskon.
     */
    public Voucher(int id, String name, int code, Type type, double minimum, double cut){
        super();
        this.name=name;
        this.code=code;
        this.type=type;
        this.minimum=minimum;
        this.cut=cut;
        used = false;
    }

    /**
     * Mengaplikasikan voucher pada harga yang diberikan.
     *
     * @param price Objek Price yang akan diterapkan voucher.
     * @return Harga setelah diterapkan voucher.
     */
    public double apply(Price price){
        used = true;
        if(type == Type.DISCOUNT ){
            return price.price - (price.price*cut/100) ;
        }else{
            return price.price-cut;
        }
    }

    /**
     * Memeriksa apakah voucher sudah digunakan.
     *
     * @return true jika voucher sudah digunakan, false jika belum.
     */
    
    public boolean isUsed(){
        return used;
    }


    /**
     * Memeriksa apakah voucher dapat diterapkan pada harga yang diberikan.
     *
     * @param price Objek Price yang akan diperiksa.
     * @return true jika voucher dapat diterapkan, false jika tidak.
     */
    public boolean canApply(Price price){
        if(price.price > minimum && used==false){
            return true;
        }else{
            return false;
        }
    }
    


}
