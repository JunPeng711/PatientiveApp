package com.example.mpsd2.doc_details;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.mpsd2.appointments.AddAppointment;
import com.example.mpsd2.CheckIn;
import com.example.mpsd2.education.HealthEducation;
import com.example.mpsd2.ProfileActivity;
import com.example.mpsd2.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DoctorPsychological extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_psychological);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.homePage){
                    //Toast.makeText(ProfileActivity.this, "homePage is clicked", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    return true;
                }
                else if (item.getItemId() == R.id.reportPage){
                    //Toast.makeText(ProfileActivity.this, "reportPage is clicked", Toast.LENGTH_LONG).show();
                    return true;
                }
                else if (item.getItemId() == R.id.checkinPage){
                    //Toast.makeText(ProfileActivity.this, "checkinPage is clicked", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), CheckIn.class));
                    return true;
                }
                else if (item.getItemId() == R.id.appointmentPage){
                    //Toast.makeText(ProfileActivity.this, "appointmentPage is clicked", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), AddAppointment.class));
                    return true;
                }
                else if (item.getItemId() == R.id.educationPage){
                    //Toast.makeText(ProfileActivity.this, "educationPage is clicked", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), HealthEducation.class));
                    return true;
                }

                return false;
            }
        });
    }
}