package com.example.mpsd2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button homeBtn, healthEducationBtn, appointmentBtn, healthcareProfBtn, viewAppointmentBtn;

        homeBtn = findViewById(R.id.footBtn1);
        homeBtn.setOnClickListener(this);

        appointmentBtn = findViewById(R.id.footBtn4);
        appointmentBtn.setOnClickListener(this);

        healthEducationBtn = findViewById(R.id.footBtn5);
        healthEducationBtn.setOnClickListener(this);

        healthcareProfBtn = findViewById(R.id.HealthProfBtn);
        healthcareProfBtn.setOnClickListener(this);

        viewAppointmentBtn = findViewById(R.id.viewAppointmentBtn);
        viewAppointmentBtn.setOnClickListener(this);
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
            case R.id.HealthProfBtn:
                startActivity(new Intent(this, HealthcareProfessional.class));
                break;
            case R.id.viewAppointmentBtn:
                startActivity(new Intent(this, ListOfAppointments.class));
                break;
        }
    }
}