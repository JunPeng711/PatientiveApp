package com.example.mpsd2.doc_details;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mpsd2.HealthcareProfessional;
import com.example.mpsd2.appointments.AddAppointment;
import com.example.mpsd2.CheckIn;
import com.example.mpsd2.HomeActivity;
import com.example.mpsd2.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DoctorPsychological extends FragmentActivity implements View.OnClickListener, OnMapReadyCallback {

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