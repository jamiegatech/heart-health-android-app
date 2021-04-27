package com.gatech.hearthealthtracker2;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<MetricDto> metrics;
    public static ProfileDto profile;
    public static String guid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        createStorage();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_profile, R.id.navigation_dashboard, R.id.navigation_add_metric)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        Integer counter = 0;
        while(unUploadedNumber() > 0 && counter < 5){
            counter++;
            new UploadTask().execute();
        }
    }

    public Integer unUploadedNumber(){
        Integer counter = 0;
        for(MetricDto metricDto: metrics){
            if(!metricDto.isUploaded()){
                counter++;
            }
        }
        return counter;
    }

    public void createStorage() {
        FileOutputStream profileOut;
        FileOutputStream metricsOut;
        FileInputStream profileInput;
        FileInputStream metricsInput;
        boolean profileFound = true;
        boolean metricsFound = true;
        try {
            profileInput = openFileInput("health_app_storage_profile.txt");
            profileInput.close();
        } catch (FileNotFoundException e) {
            profileFound = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            metricsInput = openFileInput("health_app_storage_metrics.txt");
            metricsInput.close();

        } catch (FileNotFoundException e) {
            metricsFound = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!profileFound) {
            try {
                profileOut = openFileOutput("health_app_storage_profile.txt", MODE_PRIVATE);
                profileOut.close();
                guid = UUID.randomUUID().toString();
                profile = new ProfileDto("Jane Doe", 170, 30, guid);
                saveProfile();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!metricsFound) {
            try {
                metricsOut = openFileOutput("health_app_storage_metrics.txt", MODE_PRIVATE);
                metricsOut.close();
                metrics = new ArrayList<MetricDto>();
                MetricDto metric = new MetricDto(
                        60,
                        130,
                        80,
                        7,
                        30,
                        2,
                        2,
                        2,
                        profile.getAge(),
                        profile.getHeight(),
                        profile.getGuid()
                );
                metric.setUploaded(true);
                metrics.add(metric);
                saveMetrics();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (profileFound && metricsFound) {
            loadStorage();
        }
    }

    //this methods code was taken directly from:
    //https://developer.android.com/training/data-storage/app-specific
    public String loadFileToString(FileInputStream fis) throws IOException {
        InputStreamReader inputStreamReader =
                new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String line = reader.readLine();
        while (line != null) {
            stringBuilder.append(line);
            line = reader.readLine();
        }
        return stringBuilder.toString();
    }

    public void loadStorage() {
        Gson gson = new Gson();
        try {
            FileInputStream profileInput = openFileInput("health_app_storage_profile.txt");
            FileInputStream metricsInput = openFileInput("health_app_storage_metrics.txt");
            String encryptedProfile = loadFileToString(profileInput);
            String encryptedMetrics = loadFileToString(metricsInput);
//            String profileJson = AESHelper.decryption(encryptedProfile);
//            String metricsJson = AESHelper.decryption(encryptedMetrics);

            profile = gson.fromJson(encryptedProfile, ProfileDto.class);
            metrics = gson.fromJson(encryptedMetrics, new TypeToken<ArrayList<MetricDto>>() {}.getType());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveProfile() {
        Gson gson = new Gson();
        String profileJson = gson.toJson(profile, ProfileDto.class);
//        String profileJsonEncrypted = AESHelper.encryption(profileJson);

        try {
            FileOutputStream profileOut = openFileOutput("health_app_storage_profile.txt", MODE_PRIVATE);
            profileOut.write(profileJson.getBytes());
            profileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveMetrics() {
        Gson gson = new Gson();
        String metricsJson = gson.toJson(metrics, new TypeToken<ArrayList<MetricDto>>() {}.getType());
//        String metricsJsonEncrypted = AESHelper.encryption(metricsJson);
        try {
            FileOutputStream metricsOut = openFileOutput("health_app_storage_metrics.txt", MODE_PRIVATE);
            metricsOut.write(metricsJson.getBytes());
            metricsOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}