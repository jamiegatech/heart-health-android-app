package com.gatech.hearthealthtracker2.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> trendBmi;
    private MutableLiveData<String> trendBp;
    private MutableLiveData<String> trendSleep;
    private MutableLiveData<String> trendActivity;
    private MutableLiveData<String> trendFood;

    public DashboardViewModel() {
        trendBmi = new MutableLiveData<>();
        trendBmi.setValue("12");

        trendBp = new MutableLiveData<>();
        trendBp.setValue("140/90");

        trendSleep = new MutableLiveData<>();
        trendSleep.setValue("8 hr (high)");

        trendActivity = new MutableLiveData<>();
        trendActivity.setValue("30 min (low)");

        trendFood = new MutableLiveData<>();
        trendFood.setValue("low quality");
    }

    public LiveData<String> getTrendBmi() {
        return trendBmi;
    }

    public LiveData<String> getTrendBp() {
        return trendBp;
    }

    public LiveData<String> getTrendSleep() {
        return trendSleep;
    }

    public LiveData<String> getTrendActivity() {
        return trendActivity;
    }

    public LiveData<String> getTrendFood() {
        return trendFood;
    }
}