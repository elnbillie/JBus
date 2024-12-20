package com.MuhammadBillieElianJBusRS.dbjson;

import com.MuhammadBillieElianJBusRS.dbjson.Serializable;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Vector;

/**
 * Json Table is used to write and read JSON in every class or method that call json table
 * @param <T> generic as a universal type of data.
 * @author Rafie Amandio
 */
public class JsonTable<T> extends Vector<T> {
    private static final Gson gson = new Gson();
    public final String filepath;

    /**
     * Konstruktor untuk membuat objek JsonTable.
     *
     * @param clazz Class dari tipe data T.
     * @param filepath Lokasi file JSON yang akan dibaca atau ditulis.
     * @throws IOException Jika terjadi kesalahan dalam pembacaan atau pembuatan file.
     */

    @SuppressWarnings("unchecked")
    public JsonTable(Class<T> clazz, String filepath) throws IOException {
        this.filepath = filepath;
        try {
            Class<T[]> arrayType = (Class<T[]>) Array.newInstance(clazz, 0).getClass();
            T[] loaded = readJson(arrayType, filepath);
            if (loaded != null) {
                Collections.addAll(this, loaded);

                int lastId = 0;
                for (T item : this) {
                    if (item instanceof com.MuhammadBillieElianJBusRS.dbjson.Serializable) {

                        com.MuhammadBillieElianJBusRS.dbjson.Serializable serializableItem = (com.MuhammadBillieElianJBusRS.dbjson.Serializable) item;
                        lastId = Math.max(lastId, serializableItem.id);
                    }
                }

                Serializable.setLastAssignedId(clazz, lastId);
            }
        } catch (FileNotFoundException e) {
            File file = new File(filepath);
            File parent = file.getParentFile();
            if (parent != null)
                parent.mkdirs();
            file.createNewFile();
        }
    }

    /**
     * Menulis data dari JsonTable ke file JSON.
     *
     * @throws IOException Jika terjadi kesalahan dalam penulisan file.
     */
    public void writeJson() throws IOException
    {
        writeJson(this, this.filepath);
    }
    /**
     * Menulis objek ke file JSON.
     *
     * @param object Objek yang akan ditulis ke file JSON.
     * @param filepath Lokasi file JSON tempat objek akan ditulis.
     * @throws IOException Jika terjadi kesalahan dalam penulisan file.
     */
    public static void writeJson(Object object, String filepath) throws IOException
    {
        final FileWriter writer = new FileWriter(filepath);
        writer.write(gson.toJson(object));
        writer.close();
    }
    /**
     * Membaca data dari file JSON dan mengembalikan objek dari tipe data T.
     *
     * @param clazz Class dari tipe data T.
     * @param filepath Lokasi file JSON yang akan dibaca.
     * @return Objek dari tipe data T yang dibaca dari file JSON.
     * @throws FileNotFoundException Jika file JSON tidak ditemukan.
     */
    public static <T> T readJson(Class<T> clazz, String filepath) throws FileNotFoundException
    {
        final JsonReader reader = new JsonReader(new FileReader(filepath));
        return gson.fromJson(reader, clazz);
    }
}
