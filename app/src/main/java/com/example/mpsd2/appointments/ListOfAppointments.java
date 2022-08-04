package com.example.mpsd2.appointments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mpsd2.CheckIn;
import com.example.mpsd2.HomeActivity;
import com.example.mpsd2.R;
import com.example.mpsd2.ReportCase;
import com.example.mpsd2.doc_details.DoctorPsychological;
import com.example.mpsd2.education.HealthEducation;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class ListOfAppointments extends AppCompatActivity implements View.OnClickListener{

    SharedPreferences newPreferences;
    Intent newIntent;

    BottomNavigationView bottomNavigationView;

    EditText editRoom;
    Button joinBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_appointments);

        Button backBtn;

        backBtn = findViewById(R.id.back);
        backBtn.setOnClickListener(this);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.homePage){
                    //Toast.makeText(ListOfAppointments.this, "homePage is clicked", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    return true;
                }
                else if (item.getItemId() == R.id.reportPage){
                    //Toast.makeText(ListOfAppointments.this, "reportPage is clicked", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), ReportCase.class));
                    return true;
                }
                else if (item.getItemId() == R.id.checkinPage){
                    //Toast.makeText(ListOfAppointments.this, "checkinPage is clicked", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), CheckIn.class));
                    return true;
                }
                else if (item.getItemId() == R.id.appointmentPage){
                    //Toast.makeText(ListOfAppointments.this, "appointmentPage is clicked", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), AddAppointment.class));
                    return true;
                }
                else if (item.getItemId() == R.id.educationPage){
                    //Toast.makeText(ListOfAppointments.this, "educationPage is clicked", Toast.LENGTH_LONG).show();
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

        editRoom = findViewById(R.id.EditRoom);
        joinBtn = findViewById(R.id.JoinBtn);

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editRoom.getText().toString().isEmpty()){
                    Toast.makeText(ListOfAppointments.this, "Please Enter a Room ID", Toast.LENGTH_LONG).show();
                }else {
                    try {
                        JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                                .setServerURL(new URL("https://meet.jit.si"))
                                .setRoom(editRoom.getText().toString())
                                .setAudioOnly(true)
                                .build();
                        JitsiMeetActivity.launch(ListOfAppointments.this,options);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void onClick(View v) {
        switch(v.getId()){
            case R.id.back:
                startActivity(new Intent(this, HomeActivity.class));
                break;
        }
    }

}