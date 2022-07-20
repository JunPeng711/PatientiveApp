package com.example.mpsd2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HealthEducation extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_education);

        initWidget();
    }

    private void initWidget()
    {
        Button homeBtn, healthEducationBtn, appointmentBtn, modbtn1;
        Button filterAllBtn, filterCovidBtn, filterExerciseBtn;

        modbtn1 = findViewById(R.id.showBtn1);
        modbtn1.setOnClickListener(this);

        homeBtn = findViewById(R.id.footBtn1);
        homeBtn.setOnClickListener(this);

        appointmentBtn = findViewById(R.id.footBtn4);
        appointmentBtn.setOnClickListener(this);

        healthEducationBtn = findViewById(R.id.footBtn5);
        healthEducationBtn.setOnClickListener(this);


    }




    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.footBtn1:
                startActivity(new Intent(this, ProfileActivity.class));
                break;
            case R.id.footBtn4:
                startActivity(new Intent(this, AddAppointment.class));
                break;
            case R.id.footBtn5:
                startActivity(new Intent(this, HealthEducation.class));
                break;
            case R.id.showBtn1:
                startActivity(new Intent(this, Modules1.class));
                break;
        }
    }

}