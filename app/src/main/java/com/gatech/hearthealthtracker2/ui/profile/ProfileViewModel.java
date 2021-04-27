package com.gatech.hearthealthtracker2.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gatech.hearthealthtracker2.MainActivity;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<String> name;
    private MutableLiveData<String> age;
    private MutableLiveData<String> height;
    public ProfileViewModel() {
        name = new MutableLiveData<>();
        name.setValue(MainActivity.profile.getName());

        age = new MutableLiveData<>();
        age.setValue(MainActivity.profile.getAge().toString());

        height = new MutableLiveData<>();
        height.setValue(MainActivity.profile.getHeight().toString());
    }

    public LiveData<String> getName() {
        return name;
    }

    public LiveData<String> getAge() {
        return age;
    }

    public LiveData<String> getHeight() {
        return height;
    }
}
