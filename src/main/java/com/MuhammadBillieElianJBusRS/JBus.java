package com.MuhammadBillieElianJBusRS;


/**
 * Write a description of class JBus here.
 *
 * @author Muhammad Billie Elian
 */

import java.util.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.MuhammadBillieElianJBusRS.dbjson.JsonDBEngine;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.sql.Timestamp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Kelas utama untuk aplikasi JBus.
 * Ini adalah titik masuk untuk menjalankan aplikasi berbasis Spring Boot.
 */
@SpringBootApplication
public class JBus {
    /**
     * Metode utama yang menjalankan aplikasi.
     * Inisialisasi dan konfigurasi utama aplikasi dilakukan di sini.
     *
     * @param args Argumen baris perintah yang diteruskan ke aplikasi.
     */
    public static void main(String[] args) {
        JsonDBEngine.Run(JBus.class);
        SpringApplication.run(JBus.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()));
    }
}








