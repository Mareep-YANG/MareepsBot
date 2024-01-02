package org.mareep.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpUtil {

    public static JSONObject httpGet(String url) {
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                if (response.toString().startsWith("{")) {
                    return JSON.parseObject(response.toString());
                } else {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("data", response.toString());
                    return jsonObject;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static JSONObject httpPost(String url, String data) {
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
            con.setDoOutput(true);

            con.getOutputStream().write(data.getBytes(StandardCharsets.UTF_8));

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                return JSON.parseObject(response.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
