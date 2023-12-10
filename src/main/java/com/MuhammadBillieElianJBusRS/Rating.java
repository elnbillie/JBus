package com.MuhammadBillieElianJBusRS;


/**
 * Write a description of class Rating here.
 *
 * @author Muhammad Billie Elian
 */

/**
 * Kelas yang merepresentasikan sistem penilaian.
 * Kelas ini menghitung rata-rata dari sejumlah rating yang diberikan.
 */
public class Rating {
    private long count;
    private long total;


    /**
     * Konstruktor untuk membuat objek Rating.
     * Inisialisasi total dan jumlah rating menjadi nol.
     */
    public Rating(){
        this.total = 0;
        this.count = 0;
    }

    /**
     * Menambahkan rating ke dalam sistem penilaian.
     *
     * @param rating Nilai rating yang akan ditambahkan.
     */
    
    public void insert(int rating){
        this.total=this.total+rating;
        this.count++;
    }

    /**
     * Menghitung dan mengembalikan rata-rata rating.
     *
     * @return Rata-rata rating. Mengembalikan nol jika tidak ada rating.
     */
    public double getAverage(){
        double rating;
        
        if(this.count == 0){
            return 0;
        }
        
        rating = this.total / this.count;
        return rating;
    }

    /**
     * Mengembalikan jumlah total rating yang telah diberikan.
     *
     * @return Jumlah rating.
     */
    public long getCount(){
        return this.count;
    }

    /**
     * Mengembalikan total dari semua rating.
     *
     * @return Total nilai rating.
     */
    public long getTotal(){
        return this.total;
    }

    /**
     * Mengembalikan representasi string dari objek Rating.
     *
     * @return Representasi string yang menunjukkan jumlah total dan jumlah rating.
     */
    public String toString(){
        return  "Count: " + count +" Total: " + total;
    }
}
