package com.example.mpsd2.education;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mpsd2.appointments.AddAppointment;
import com.example.mpsd2.CheckIn;
import com.example.mpsd2.ProfileActivity;
import com.example.mpsd2.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HealthEducation extends AppCompatActivity implements View.OnClickListener{

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_education);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.homePage){
                    //Toast.makeText(HealthEducation.this, "homePage is clicked", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    return true;
                }
                else if (item.getItemId() == R.id.reportPage){
                    Toast.makeText(HealthEducation.this, "reportPage is clicked", Toast.LENGTH_LONG).show();
                    return true;
                }
                else if (item.getItemId() == R.id.checkinPage){
                    //Toast.makeText(HealthEducation.this, "checkinPage is clicked", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), CheckIn.class));
                    return true;
                }
                else if (item.getItemId() == R.id.appointmentPage){
                    //Toast.makeText(HealthEducation.this, "appointmentPage is clicked", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), AddAppointment.class));
                    return true;
                }
                else if (item.getItemId() == R.id.educationPage){
                    //Toast.makeText(HealthEducation.this, "educationPage is clicked", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), HealthEducation.class));
                    return true;
                }

                return false;
            }
        });

        initWidget();
    }

    private void initWidget()
    {
        Button modBtn1, backToHomeBtn;

        modBtn1 = findViewById(R.id.showBtn1);
        modBtn1.setOnClickListener(this);


        backToHomeBtn = findViewById(R.id.home);
        backToHomeBtn.setOnClickListener(this);

    }




    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.home:
                startActivity(new Intent(this, ProfileActivity.class));
                break;

            case R.id.showBtn1:
                startActivity(new Intent(this, Modules1.class));
                break;
        }
    }

}