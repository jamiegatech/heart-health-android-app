package com.gatech.hearthealthtracker2.ui.dashboard;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.gatech.hearthealthtracker2.MainActivity;
import com.gatech.hearthealthtracker2.R;
import com.gatech.hearthealthtracker2.TrendGenerator;

import static com.gatech.hearthealthtracker2.TrendGenerator.*;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView trend_bmi = root.findViewById(R.id.trend_bmi);
        final TextView trend_blood_pressure = root.findViewById(R.id.trend_blood_pressure);
        final TextView trend_sleep = root.findViewById(R.id.trend_sleep);
        final TextView trend_activity = root.findViewById(R.id.trend_activity);
        final TextView trend_food = root.findViewById(R.id.trend_food);
        dashboardViewModel.getTrendBmi().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                trend_bmi.setText(bmiLabel(getFirst(MainActivity.metrics)));
                trend_bmi.setTextColor(bmiColor(MainActivity.metrics));
            }
        });
        dashboardViewModel.getTrendBp().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                trend_blood_pressure.setText(bpLabel(getFirst(MainActivity.metrics)));
                trend_blood_pressure.setTextColor(bpColor(MainActivity.metrics));
            }
        });
        dashboardViewModel.getTrendSleep().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                trend_sleep.setText(sleepLabel(getFirst(MainActivity.metrics)));
                trend_sleep.setTextColor(sleepColor(MainActivity.metrics));
            }
        });
        dashboardViewModel.getTrendActivity().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                trend_activity.setText(activityLabel(getFirst(MainActivity.metrics)));
                trend_activity.setTextColor(activityColor(MainActivity.metrics));
            }
        });
        dashboardViewModel.getTrendFood().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                trend_food.setText(foodLabel(getFirst(MainActivity.metrics)));
                trend_food.setTextColor(foodColor(MainActivity.metrics));
            }
        });
        return root;
    }
}