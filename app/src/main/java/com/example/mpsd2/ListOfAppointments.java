package com.example.mpsd2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ListOfAppointments extends AppCompatActivity implements View.OnClickListener{

    SharedPreferences newPreferences;
    Intent newIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_appointments);

        Button homeBtn, healthEducationBtn, appointmentBtn;

        homeBtn = findViewById(R.id.footBtn1);
        homeBtn.setOnClickListener(this);

        appointmentBtn = findViewById(R.id.footBtn4);
        appointmentBtn.setOnClickListener(this);

        healthEducationBtn = findViewById(R.id.footBtn5);
        healthEducationBtn.setOnClickListener(this);

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
            case R.id.footBtn1:
                startActivity(new Intent(this, ProfileActivity.class));
                break;
            case R.id.footBtn4:
                startActivity(new Intent(this, AddAppointment.class));
                break;
            case R.id.footBtn5:
                startActivity(new Intent(this, HealthEducation.class));
                break;
        }
    }

}