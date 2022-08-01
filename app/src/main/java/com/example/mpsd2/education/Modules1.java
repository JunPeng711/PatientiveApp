package com.example.mpsd2.education;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mpsd2.R;
import com.example.mpsd2.education.HealthEducation;

public class Modules1 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules1);

        Button backToHomeBtn;

        backToHomeBtn = findViewById(R.id.back);
        backToHomeBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.back:
                startActivity(new Intent(this, HealthEducation.class));
                break;

        }
    }
}