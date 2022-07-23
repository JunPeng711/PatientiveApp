package com.example.mpsd2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

public class ListOfAppointments extends AppCompatActivity implements View.OnClickListener{

    SharedPreferences newPreferences;
    Intent newIntent;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_appointments);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.homePage){
                    Toast.makeText(ListOfAppointments.this, "homePage is clicked", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    return true;
                }
                else if (item.getItemId() == R.id.reportPage){
                    Toast.makeText(ListOfAppointments.this, "reportPage is clicked", Toast.LENGTH_LONG).show();
                    return true;
                }
                else if (item.getItemId() == R.id.checkinPage){
                    Toast.makeText(ListOfAppointments.this, "checkinPage is clicked", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), CheckIn.class));
                    return true;
                }
                else if (item.getItemId() == R.id.appointmentPage){
                    Toast.makeText(ListOfAppointments.this, "appointmentPage is clicked", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), AddAppointment.class));
                    return true;
                }
                else if (item.getItemId() == R.id.educationPage){
                    Toast.makeText(ListOfAppointments.this, "educationPage is clicked", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), HealthEducation.class));
                    return true;
                }

                return false;
            }
        });


        TextView name = findViewById(R.id.viewName);
        TextView date = findViewById(R.id.viewDate);
        TextView time = findViewById(R.id.viewTime);
        TextView patientName = findViewById(R.id.viewPatientName);
        TextView patientIC = findViewById(R.id.viewPatientIC);
        TextView healthIssues = findViewById(R.id.viewHealthIssues);

        newPreferences = getSharedPreferences("add_appointment", MODE_PRIVATE);
        newIntent = new Intent(ListOfAppointments.this, AddAppointment.class);

        name.setText(newPreferences.getString("name", null));
        date.setText(newPreferences.getString("date", null));
        time.setText(newPreferences.getString("time", null));
        patientName.setText(newPreferences.getString("patientName", null));
        patientIC.setText(newPreferences.getString("patientIC", null));
        healthIssues.setText(newPreferences.getString("healthIssues", null));

        Button clear = findViewById(R.id.deleteBtn);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor edit = newPreferences.edit();
                edit.clear();
                edit.commit();
                Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_SHORT).show();
                startActivity(newIntent);
            }
        });
    }

    public void onClick(View v) {
        switch(v.getId()){

        }
    }

}