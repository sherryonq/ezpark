package com.example.sherry.ezpark;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    Switch notificationSwitch;
    Spinner themeSpinner;
    int initialSelectedItemPos;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    public static final String APP_SETTINGS = "APP_SETTINGS";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Settings");
        View v = getView();

        //get the settingsFile
        sharedPref = getActivity().getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
        String appTheme = sharedPref.getString("App_Theme", "AppTheme");

        notificationSwitch = v.findViewById(R.id.switchNotification);
        themeSpinner = v.findViewById(R.id.spinnerTheme);

        setThemeSpinnerState(themeSpinner, appTheme);

        boolean notificationActivated = sharedPref.getBoolean("Notification_Status", true);
        setNotificationSwitchState(notificationSwitch, notificationActivated);

        editor = sharedPref.edit();
        notificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (notificationSwitch.isChecked()) {
                    editor.putBoolean("Notification_Status", true);
                } else {
                    editor.putBoolean("Notification_Status", false);
                }
                editor.apply();
            }
        });
        initialSelectedItemPos = themeSpinner.getSelectedItemPosition();
        themeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0){
                    editor.putString("App_Theme", "AppTheme");
                    restartOrNot(initialSelectedItemPos, position);

                } else if (position == 1){
                    editor.putString("App_Theme", "LightTheme");
                    restartOrNot(initialSelectedItemPos, position);
                }
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                editor.putString("App_Theme", "AppTheme");
                editor.apply();
            }

            private void restartOrNot(int init, int pos) {
                if (init != pos) {
                    getActivity().recreate();
                }
            }
        });
    }

    public void setThemeSpinnerState(Spinner themeSpinner, String appTheme){
        if (appTheme.equals("AppTheme")){
            themeSpinner.setSelection(0);
        } else if (appTheme.equals("LightTheme")){
            themeSpinner.setSelection(1);
        }
    }

    public void setNotificationSwitchState(Switch notificationSwitch, Boolean notificationActivated){
        if (notificationActivated){
            notificationSwitch.setChecked(true);
        } else {
            notificationSwitch.setChecked(false);
        }
    }
}