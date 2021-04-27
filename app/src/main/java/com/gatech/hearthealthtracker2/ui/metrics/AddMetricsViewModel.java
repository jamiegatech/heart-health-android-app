package com.gatech.hearthealthtracker2.ui.metrics;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddMetricsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AddMetricsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}