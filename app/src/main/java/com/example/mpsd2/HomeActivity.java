package com.example.mpsd2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.mpsd2.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity{

    //BottomNavigationView bottomNavigationView;

    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.homePage:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.reportPage:
                    replaceFragment(new ReportFragment());
                    break;
                case R.id.checkinPage:
                    startActivity(new Intent(getApplicationContext(), CheckIn.class));
                    break;
                case R.id.appointmentPage:
                    replaceFragment(new ListOfAppointmentFragment());
                    break;
                case R.id.educationPage:
                    replaceFragment(new HealthEducationFragment());
                    break;
            }
            return true;
        });


    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}