package com.gatech.hearthealthtracker2;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
//this code was entirely a product of the stack overflow post, with my own alterations on top. giving them credit for it. 
//https://stackoverflow.com/questions/15496278/httpurlconnection-is-throwing-exception
public class UploadTask extends AsyncTask<Void, Void, Void>
{
    @Override
    protected Void doInBackground(Void... params) {
        Gson gson = new Gson();
        URL url = null;
        try {
//            url = new URL("http://10.0.2.2:8080/heart-health/datapoint");
            url = new URL("https://afternoon-taiga-96123.herokuapp.com/heart-health/datapoint");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection)url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            con.setRequestMethod("POST");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        List<MetricDto> newMetrics = new ArrayList<MetricDto>();
        for(MetricDto metricDto: MainActivity.metrics){
            if(!metricDto.isUploaded()){
                newMetrics.add(metricDto);
                metricDto.setUploaded(true);
                break;
            }
        }
        if(newMetrics.size() > 0) {
            String jsonInputString = gson.toJson(newMetrics.get(0));
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        con.disconnect();
        long totalSize = 0;
//        return totalSize;
        return null;
    }
}
