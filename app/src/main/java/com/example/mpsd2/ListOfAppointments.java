package com.example.mpsd2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ListOfAppointments extends AppCompatActivity {

    SharedPreferences newPreferences;
    Intent newIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_appointments);

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
                startActivity(newIntent);
            }
        });
    }
}