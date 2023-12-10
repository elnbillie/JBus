package com.MuhammadBillieElianJBusRS.controller;

import com.MuhammadBillieElianJBusRS.Algorithm;
import com.MuhammadBillieElianJBusRS.dbjson.Serializable;
import com.MuhammadBillieElianJBusRS.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Interface untuk controller dasar yang menyediakan operasi GET standar.
 * Interface ini mendefinisikan metode untuk mendapatkan data berdasarkan ID dan memaginasikan data.
 *
 * @param <T> Tipe data yang merupakan subkelas dari Serializable.
 */

public interface BasicGetController  <T extends Serializable> {

    /**
     * Mengembalikan JsonTable yang berisi data.
     *
     * @return JsonTable dari tipe data T.
     */
    JsonTable<T> getJsonTable();

    /**
     * Mendapatkan objek berdasarkan ID.
     *
     * @param id ID dari objek yang ingin diambil.
     * @return Objek dari tipe data T dengan ID yang diberikan.
     */
    @GetMapping("/{id}")
    public default T getById(@PathVariable int id){return Algorithm.<T>find(getJsonTable(),e ->e.id == id);}

    /**
     * Memaginasikan dan mengembalikan daftar objek.
     *
     * @param page Nomor halaman yang diinginkan.
     * @param pageSize Jumlah objek per halaman.
     * @return Daftar objek pada halaman yang ditentukan.
     */
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public default List<T> getPage(
            @RequestParam(value = "page", defaultValue = "0")int page,
            @RequestParam(value = "size", defaultValue = "5")int pageSize
    ){
        return Algorithm.<T>paginate(getJsonTable(),page, pageSize, a->true);
    }

}
