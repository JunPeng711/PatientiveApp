package com.example.mpsd2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class HealthEducation extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_education);

        initWidget();
    }

    private void initWidget()
    {
        Button homeBtn, healthEducationBtn, btn1;
        Button filterAllBtn, filterCovidBtn, filterExerciseBtn;

        homeBtn = findViewById(R.id.footBtn1);
        homeBtn.setOnClickListener(this);

        healthEducationBtn = findViewById(R.id.footBtn5);
        healthEducationBtn.setOnClickListener(this);

        btn1 = findViewById(R.id.showBtn1);
        btn1.setOnClickListener(this);


    }




    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.footBtn1:
                startActivity(new Intent(this, ProfileActivity.class));
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