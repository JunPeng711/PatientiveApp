package com.example.mpsd2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mpsd2.appointments.AddAppointment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment implements View.OnClickListener{

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;


    TextView welcomeTxtView;
    SharedPreferences newPreferences;
    Intent newIntent;


    public HomeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        welcomeTxtView = view.findViewById(R.id.textViewWelcome);
        Bundle bundle = getArguments();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null)
                {
                    String Welcome = userProfile.name;

                    welcomeTxtView.setText(Welcome);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Error!", Toast.LENGTH_SHORT).show();            }
        });


        ImageButton healthcareProfBtn;
        ImageButton ProfBtn;

        healthcareProfBtn = view.findViewById(R.id.HealthProfBtn);
        healthcareProfBtn.setOnClickListener(this);

        ProfBtn = view.findViewById(R.id.profileBttn);
        ProfBtn.setOnClickListener(this);



        TextView name = view.findViewById(R.id.viewName);
        TextView date = view.findViewById(R.id.viewDate);
        TextView time = view.findViewById(R.id.viewTime);

        newPreferences = this.getActivity().getSharedPreferences("add_appointment", Context.MODE_PRIVATE);
        newIntent = new Intent(getActivity(), AddAppointment.class);

        name.setText(newPreferences.getString("name", null));
        date.setText(newPreferences.getString("date", null));
        time.setText(newPreferences.getString("time", null));

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.HealthProfBtn:
                startActivity(new Intent(getActivity(), HealthcareProfessional.class));
                break;
            case R.id.profileBttn:
                startActivity(new Intent(getActivity(),UserProfile.class));
        }
    }
}