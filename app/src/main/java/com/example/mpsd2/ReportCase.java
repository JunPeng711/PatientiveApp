package com.example.mpsd2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mpsd2.appointments.ListOfAppointments;
import com.example.mpsd2.education.HealthEducation;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

public class ReportCase extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    private int CurrentProgress = 0;
    private ProgressBar progressBar;
    private Button startProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_case);



        //bottom navigator
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.homePage){
                    //Toast.makeText(ProfileActivity.this, "homePage is clicked", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    return true;
                }
                else if (item.getItemId() == R.id.reportPage){
                    //Toast.makeText(ProfileActivity.this, "reportPage is clicked", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), ReportCase.class));
                    return true;
                }
                else if (item.getItemId() == R.id.checkinPage){
                    //Toast.makeText(ProfileActivity.this, "checkinPage is clicked", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), CheckIn.class));
                    return true;
                }
                else if (item.getItemId() == R.id.appointmentPage){
                    //Toast.makeText(ProfileActivity.this, "appointmentPage is clicked", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), ListOfAppointments.class));
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

        progressBar = findViewById(R.id.progressBar);
        startProgress = findViewById(R.id.addProgress);
        TextView percentText = findViewById(R.id.percent);

        startProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentProgress = CurrentProgress + 20;
                progressBar.setProgress(CurrentProgress);
                progressBar.setMax(100);

                String percent = String.valueOf(CurrentProgress);
                if (CurrentProgress < 100)
                {
                    percentText.setText(percent);
                }
                else
                {
                    percentText.setText("100");
                }
            }
        });




    }
}