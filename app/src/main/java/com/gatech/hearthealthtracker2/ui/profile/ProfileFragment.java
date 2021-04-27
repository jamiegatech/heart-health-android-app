package com.gatech.hearthealthtracker2.ui.profile;

import android.content.ContextWrapper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.gatech.hearthealthtracker2.MainActivity;
import com.gatech.hearthealthtracker2.ProfileDto;
import com.gatech.hearthealthtracker2.R;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        Button btn = (Button) root.findViewById(R.id.profile_save);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileSaveButton(view);
            }
        });

//        EditText name = (EditText) root.getRootView().findViewById(R.id.profile_name);
//        name.setText(MainActivity.profile.getName());
//        EditText age = (EditText) root.getRootView().findViewById(R.id.profile_age);
//        age.setText(MainActivity.profile.getAge());
//        EditText height = (EditText) root.getRootView().findViewById(R.id.profile_height);
//        height.setText(MainActivity.profile.getHeight());

        final TextView textView = root.findViewById(R.id.profile_name);
        profileViewModel.getName().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        final TextView textView1 = root.findViewById(R.id.profile_age);
        profileViewModel.getAge().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView1.setText(s);
            }
        });

        final TextView textView2 = root.findViewById(R.id.profile_height);
        profileViewModel.getHeight().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView2.setText(s);
            }
        });

        super.onCreate(savedInstanceState);


        return root;
    }

    public void profileSaveButton(View view) {
        EditText name = (EditText) view.getRootView().findViewById(R.id.profile_name);
        MainActivity.profile.setName(name.getText().toString());

        EditText  age = (EditText) view.getRootView().findViewById(R.id.profile_age);
        MainActivity.profile.setAge(Integer.valueOf(age.getText().toString()));

        EditText height = (EditText) view.getRootView().findViewById(R.id.profile_height);
        MainActivity.profile.setHeight(Integer.valueOf(height.getText().toString()));

        saveProfile(view);
    }

    public void saveProfile(View view) {
        Gson gson = new Gson();
        String profileJson = gson.toJson(MainActivity.profile, ProfileDto.class);
//        String profileJsonEncrypted = AESHelper.encryption(profileJson);

        try {
            FileOutputStream profileOut = view.getRootView().getContext().openFileOutput("health_app_storage_profile.txt", MODE_PRIVATE);
            profileOut.write(profileJson.getBytes());
            profileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}