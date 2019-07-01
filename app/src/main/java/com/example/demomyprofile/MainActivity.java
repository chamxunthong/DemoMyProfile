package com.example.demomyprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);
        
    }

    @Override
    protected void onPause() {
        super.onPause();

        String strName = etName.getText().toString();
        Float fltGPA = Float.parseFloat(etGPA.getText().toString());
        Integer genderId = rgGender.getCheckedRadioButtonId();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor preEdit = prefs.edit();

        preEdit.putString("User Name", strName);
        preEdit.putFloat("GPA", fltGPA);
        preEdit.putInt("Gender", genderId);

        preEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String name = prefs.getString("User Name", "XT");
        Float gpa = prefs.getFloat("GPA", (float) 1.0);
        Integer gender = prefs.getInt("Gender", R.id.radioButtonGenderMale);
        etName.setText(name);
        etGPA.setText(gpa.toString());
        rgGender.check(gender);
    }
}
