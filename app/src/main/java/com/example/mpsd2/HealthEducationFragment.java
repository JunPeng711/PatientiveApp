package com.example.mpsd2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mpsd2.education.Modules1;

public class HealthEducationFragment extends Fragment implements View.OnClickListener{

    public HealthEducationFragment() {
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
        View view = inflater.inflate(R.layout.fragment_health_education, container, false);

        Button modBtn1, backToHomeBtn;

        modBtn1 = view.findViewById(R.id.showBtn1);
        modBtn1.setOnClickListener(this);


        backToHomeBtn = view.findViewById(R.id.home);
        backToHomeBtn.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.home:
                startActivity(new Intent(getActivity(), HomeActivity.class));
                break;

            case R.id.showBtn1:
                startActivity(new Intent(getActivity(), Modules1.class));
                break;
        }
    }
}