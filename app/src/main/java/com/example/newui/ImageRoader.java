package com.example.newui;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ImageRoader {

    private final String serverUrl = "http://192.168.64.14:8080/app/resources/img/";

    public ImageRoader() {

        new ThreadPolicy();
    }

    public Bitmap getBitmapImg(String imgStr) {

        Bitmap bitmapImg = null;

        try {
            URL url = new URL(serverUrl +
                    URLEncoder.encode(imgStr, "utf-8"));
            // Character is converted to 'UTF-8' to prevent broken

            HttpURLConnection conn = (HttpURLConnection) url
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();

            InputStream is = conn.getInputStream();
            bitmapImg = BitmapFactory.decodeStream(is);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return bitmapImg;
    }
}