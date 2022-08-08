package com.example.mpsd2.appointments;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.autofill.AutofillValue;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mpsd2.CheckIn;
import com.example.mpsd2.HomeActivity;
import com.example.mpsd2.ListOfAppointmentFragment;
import com.example.mpsd2.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class AddAppointment extends AppCompatActivity implements View.OnClickListener{

    DatePicker simpleDatePicker;
    Button submit;
    EditText editDateText;

    EditText name, date, time, patientName, patientIC, healthIssues;
    SharedPreferences Shared_pref;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);


        ImageButton cancelBtn;

        cancelBtn = findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(this);


        ImageButton addBtn = (ImageButton) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(this);

        editDateText = findViewById(R.id.editDate);

        // initiate the date picker and a button
        simpleDatePicker = (DatePicker) findViewById(R.id.simpleDatePicker);
        submit = (Button) findViewById(R.id.submitButton);
        // perform click event on submit button
        submit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                // get the values for day of month , month and year from a date picker
                String day = "" + simpleDatePicker.getDayOfMonth();
                String month = "/" + (simpleDatePicker.getMonth() + 1);
                String year = "/" + simpleDatePicker.getYear();
                // display the values by using a toast
                //Toast.makeText(getApplicationContext(), day + "\n" + month + "\n" + year, Toast.LENGTH_LONG).show();
                editDateText.getText().toString();
                editDateText.autofill(AutofillValue.forText(day+month+year));
            }
        });


        name = findViewById(R.id.editName);
        date = findViewById(R.id.editDate);
        time = findViewById(R.id.editTime);
        patientIC = findViewById(R.id.editPatientIC);
        healthIssues = findViewById(R.id.editHealthIssues);

        Shared_pref = getSharedPreferences("add_appointment", MODE_PRIVATE);
        intent = new Intent(AddAppointment.this, HomeActivity.class);
        if(Shared_pref.contains("name") && Shared_pref.contains("date") && Shared_pref.contains("time") && Shared_pref.contains("patientIC") && Shared_pref.contains("healthIssues"))
        {
            startActivity(intent);
        }
    }

    public void AddCalendarEvent(View view) {
        Calendar calendarEvent = Calendar.getInstance();
        Intent i = new Intent(Intent.ACTION_EDIT);
        i.setType("vnd.android.cursor.item/event");
        i.putExtra("beginTime", calendarEvent.getTimeInMillis());
        i.putExtra("allDay", true);
        i.putExtra("rule", "FREQ=YEARLY");
        i.putExtra("endTime", calendarEvent.getTimeInMillis() + 60 * 60 * 1000);
        i.putExtra("title", "Calendar Event");
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancelBtn:
                startActivity(new Intent(this, HomeActivity.class));
                break;
            case R.id.addBtn:
                String name = AddAppointment.this.name.getText().toString();
                String date = AddAppointment.this.date.getText().toString();
                String time = AddAppointment.this.time.getText().toString();
                String patientIC = AddAppointment.this.patientIC.getText().toString();
                String healthIssues = AddAppointment.this.healthIssues.getText().toString();

                SharedPreferences.Editor editor = Shared_pref.edit();
                editor.putString("name", name);
                editor.putString("date", date);
                editor.putString("time", time);
                editor.putString("patientIC", patientIC);
                editor.putString("healthIssues", healthIssues);
                editor.commit();
                Toast.makeText(getApplicationContext(), "Add", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                break;
        }
    }
}