package com.gatech.hearthealthtracker2.ui.metrics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.gatech.hearthealthtracker2.MainActivity;
import com.gatech.hearthealthtracker2.MetricDto;
import com.gatech.hearthealthtracker2.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
//the fragment and XML that accompanies this was helped by the code found here:
//https://code.tutsplus.com/tutorials/android-essentials-creating-simple-user-forms--mobile-1758
//couldnt of done it without their instructions, especially in the beginning.
import static android.content.Context.MODE_PRIVATE;

public class AddMetricsFragment extends Fragment {

    private AddMetricsViewModel addMetricsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addMetricsViewModel =
                new ViewModelProvider(this).get(AddMetricsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_metic, container, false);

        Button saveButton = (Button) root.findViewById(R.id.add_metric_save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metricSaveButton(view);
            }
        });

//        Button clearButton = (Button) root.findViewById(R.id.add_metric_clear_button);
//
//        clearButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                metricClearButton(view);
//            }
//        });


//        final TextView textView = root.findViewById(R.id.text_notifications);
//        addMetricsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
    public void metricSaveButton(View view2) {
        View view = view2.getRootView();
        EditText activity = (EditText) view.findViewById(R.id.add_metric_activity);
        EditText diastolic = (EditText) view.findViewById(R.id.add_metric_diastolic);
        EditText systolic = (EditText) view.findViewById(R.id.add_metric_systolic);
        EditText weight = (EditText) view.findViewById(R.id.add_metric_weight);
        EditText sleep = (EditText) view.findViewById(R.id.add_metric_sleep_hrs);

        Integer sleepQ = 1;
        Integer activityQ = 1;
        Integer foodQ = 1;

        RadioButton sleepQualityLow = (RadioButton) view.findViewById(R.id.sleep_low);
        RadioButton sleepQualityNeutral = (RadioButton) view.findViewById(R.id.sleep_neutral);


        RadioButton activityQualityLow = (RadioButton) view.findViewById(R.id.activity_low);
        RadioButton activityQualityNeutral = (RadioButton) view.findViewById(R.id.activity_neutral);


        RadioButton foodQualityLow = (RadioButton) view.findViewById(R.id.food_low);
        RadioButton foodQualityNeutral = (RadioButton) view.findViewById(R.id.food_neutral);

        if(sleepQualityLow.isChecked()){
            sleepQ = 1;
        }else if(sleepQualityNeutral.isChecked()){
            sleepQ = 2;
        }else{
            sleepQ = 3;
        }

        if(foodQualityLow.isChecked()){
            foodQ = 1;
        }else if(foodQualityNeutral.isChecked()){
            foodQ = 2;
        }else{
            foodQ = 3;
        }

        if(activityQualityLow.isChecked()){
            activityQ = 1;
        }else if(activityQualityNeutral.isChecked()){
            activityQ = 2;
        }else{
            activityQ = 3;
        }

        MetricDto metricDto = new MetricDto(true);
        metricDto.setActivity(Integer.valueOf(activity.getText().toString()));
        metricDto.setDiastolic(Integer.valueOf(diastolic.getText().toString()));
        metricDto.setSystolic(Integer.valueOf(systolic.getText().toString()));
        metricDto.setWeight(Integer.valueOf(weight.getText().toString()));
        metricDto.setSleep(Integer.valueOf(sleep.getText().toString()));
        metricDto.setSleepQuality(sleepQ);
        metricDto.setFoodQuality(foodQ);
        metricDto.setActivityQuality(activityQ);
        metricDto.setUploaded(false);
        metricDto.setHeight(MainActivity.profile.getHeight());
        metricDto.setAge(MainActivity.profile.getAge());
        metricDto.setGuid(MainActivity.profile.getGuid());

        MainActivity.metrics.add(metricDto);
        saveMetrics(view2);
//        metricClearButton(view2);
    }

    public void metricClearButton(View view) {
        view = view.getRootView();
        EditText activity = (EditText) view.findViewById(R.id.add_metric_activity);
        activity.getText().clear();
        EditText diastolic = (EditText) view.findViewById(R.id.add_metric_diastolic);
        diastolic.getText().clear();
        EditText systolic = (EditText) view.findViewById(R.id.add_metric_systolic);
        systolic.getText().clear();
        EditText weight = (EditText) view.findViewById(R.id.add_metric_weight);
        weight.getText().clear();
        EditText sleep = (EditText) view.findViewById(R.id.add_metric_sleep_hrs);
        sleep.getText().clear();

        RadioButton sleepQualityLow = (RadioButton) view.findViewById(R.id.sleep_low);
        sleepQualityLow.setChecked(true);
        RadioButton sleepQualityNeutral = (RadioButton) view.findViewById(R.id.sleep_neutral);
        sleepQualityNeutral.setChecked(false);
        RadioButton sleepQualityHigh = (RadioButton) view.findViewById(R.id.sleep_high);
        sleepQualityHigh.setChecked(false);

        RadioButton activityQualityLow = (RadioButton) view.findViewById(R.id.activity_low);
        activityQualityLow.setChecked(true);
        RadioButton activityQualityNeutral = (RadioButton) view.findViewById(R.id.activity_neutral);
        activityQualityNeutral.setChecked(false);
        RadioButton activityQualityHigh = (RadioButton) view.findViewById(R.id.activity_high);
        activityQualityHigh.setChecked(false);

        RadioButton foodQualityLow = (RadioButton) view.findViewById(R.id.food_low);
        foodQualityLow.setChecked(true);
        RadioButton foodQualityNeutral = (RadioButton) view.findViewById(R.id.food_neutral);
        foodQualityNeutral.setChecked(false);
        RadioButton foodQualityHigh = (RadioButton) view.findViewById(R.id.food_high);
        foodQualityHigh.setChecked(false);
    }

    public void saveMetrics(View view) {
        Gson gson = new Gson();
        String metricsJson = gson.toJson(MainActivity.metrics, new TypeToken<ArrayList<MetricDto>>() {}.getType());
//        String metricsJsonEncrypted = AESHelper.encryption(metricsJson);
        try {
            FileOutputStream metricsOut = view.getRootView().getContext().openFileOutput("health_app_storage_metrics.txt", MODE_PRIVATE);
            metricsOut.write(metricsJson.getBytes());
            metricsOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
