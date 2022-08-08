package com.example.mpsd2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mpsd2.appointments.AddAppointment;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class ListOfAppointmentFragment extends Fragment implements View.OnClickListener{

    SharedPreferences newPreferences;
    Intent newIntent;

    EditText editRoom;
    ImageButton joinBtn;

    public ListOfAppointmentFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_of_appointment, container, false);

        ImageButton backBtn;

        backBtn = view.findViewById(R.id.back);
        backBtn.setOnClickListener(this);

        TextView name = view.findViewById(R.id.viewName);
        TextView date = view.findViewById(R.id.viewDate);
        TextView time = view.findViewById(R.id.viewTime);
        TextView patientIC = view.findViewById(R.id.viewPatientIC);
        TextView healthIssues = view.findViewById(R.id.viewHealthIssues);

        newPreferences = this.getActivity().getSharedPreferences("add_appointment", Context.MODE_PRIVATE);
        newIntent = new Intent(getActivity(), AddAppointment.class);

        name.setText(newPreferences.getString("name", null));
        date.setText(newPreferences.getString("date", null));
        time.setText(newPreferences.getString("time", null));
        patientIC.setText(newPreferences.getString("patientIC", null));
        healthIssues.setText(newPreferences.getString("healthIssues", null));

        ImageButton clear = view.findViewById(R.id.deleteBtn);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor edit = newPreferences.edit();
                edit.clear();
                edit.commit();
                Toast.makeText(getActivity().getApplicationContext(),"Deleted",Toast.LENGTH_SHORT).show();
                startActivity(newIntent);
            }
        });

        editRoom = view.findViewById(R.id.EditRoom);
        joinBtn = view.findViewById(R.id.JoinBtn);

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editRoom.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Please Enter a Room ID", Toast.LENGTH_LONG).show();
                }else {
                    try {
                        JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                                .setServerURL(new URL("https://meet.jit.si"))
                                .setRoom(editRoom.getText().toString())
                                .setAudioOnly(true)
                                .build();
                        JitsiMeetActivity.launch(getActivity(),options);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        return view;
    }

    public void onClick(View v) {
        switch(v.getId()){
            case R.id.back:
                startActivity(new Intent(getActivity(), HomeActivity.class));
                break;
        }
    }
}