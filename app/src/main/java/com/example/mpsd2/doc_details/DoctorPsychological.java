package com.example.mpsd2.doc_details;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.mpsd2.HealthcareProfessional;
import com.example.mpsd2.appointments.AddAppointment;
import com.example.mpsd2.CheckIn;
import com.example.mpsd2.education.HealthEducation;
import com.example.mpsd2.ProfileActivity;
import com.example.mpsd2.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DoctorPsychological extends FragmentActivity implements View.OnClickListener, OnMapReadyCallback {

    BottomNavigationView bottomNavigationView;
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_psychological);
        Button backToHomeBtn, bookBtn;

        backToHomeBtn = findViewById(R.id.back);
        backToHomeBtn.setOnClickListener(this);

        bookBtn = findViewById(R.id.book);
        bookBtn.setOnClickListener(this);

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


        //map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.back:
                startActivity(new Intent(this, HealthcareProfessional.class));
                break;
            case R.id.book:
                startActivity(new Intent(this, AddAppointment.class));
                break;
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;

        LatLng KualaLumpur = new LatLng(3.127507, 101.682253);
        map.addMarker(new MarkerOptions().position(KualaLumpur).title("KualaLumpur"));
        map.moveCamera(CameraUpdateFactory.newLatLng(KualaLumpur));
    }
}