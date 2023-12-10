package com.MuhammadBillieElianJBusRS.dbjson;

import java.util.HashMap;
/**
 * Kelas dasar untuk objek yang dapat diserialisasi.
 * Kelas ini menyediakan identitas unik untuk setiap instance melalui ID dan metode untuk mengelola ID tersebut.
 */
public class Serializable implements Comparable<Serializable> {
    public final int id;
    private static HashMap<Class<?>, Integer> mapCounter = new HashMap<Class <?>, Integer>();
    /**
     * Konstruktor untuk membuat instance Serializable.
     * Menginisialisasi ID unik berdasarkan counter yang sesuai dengan tipe kelas.
     */
    protected Serializable(){
        Integer counter = mapCounter.get(getClass());
        counter = counter == null ? 0 : counter + 1;
        mapCounter.put(getClass(), counter);
        this.id = counter;
    }

    /**
     * Mendapatkan ID terakhir yang diberikan untuk kelas tertentu.
     *
     * @param <T> Tipe kelas.
     * @param getter Kelas untuk mendapatkan ID terakhir.
     * @return ID terakhir yang diberikan untuk kelas tersebut.
     */
    public static <T> Integer getLastAssignedId(Class<T> getter ){
        return mapCounter.get(getter);
    }

    /**
     * Mengatur ID terakhir yang diberikan untuk kelas tertentu.
     *
     * @param <T> Tipe kelas.
     * @param setter Kelas untuk mengatur ID terakhir.
     * @param number ID terakhir yang akan diset.
     * @return ID terakhir yang diset untuk kelas tersebut.
     */
    public static <T> Integer setLastAssignedId(Class<T> setter, int number){
        return mapCounter.put(setter, number);
    }
    /**
     * Membandingkan objek Serializable ini dengan objek lain berdasarkan ID.
     *
     * @param temp Objek Serializable lain untuk dibandingkan.
     * @return Hasil perbandingan ID.
     */
    public int compareTo(Serializable temp){
        return ((Integer)this.id).compareTo(temp.id);
    }
    /**
     * Mengecek kesamaan objek Serializable ini dengan objek lain berdasarkan ID.
     *
     * @param temp Objek Serializable lain.
     * @return true jika kedua objek memiliki ID yang sama.
     */
    public boolean equals(Serializable temp){
        return temp.id == this.id;
    }
    /**
     * Mengecek kesamaan objek Serializable ini dengan objek lain.
     *
     * @param object Objek lain.
     * @return true jika objek lain merupakan instance Serializable dan memiliki ID yang sama.
     */
    public boolean equals(Object object){
        return object instanceof Serializable && ((Serializable) object).id == this.id;
    }

}
