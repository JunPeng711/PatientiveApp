package com.example.mpsd2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class ReportFragment extends Fragment {

    private int CurrentProgress = 0;
    private ProgressBar progressBar;
    private Button startProgress, day1Btn, day2Btn, day3Btn, day4Btn, day5Btn, EmergencyBtn;

    public ReportFragment() {
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
        View view = inflater.inflate(R.layout.fragment_report, container, false);

        //progressBar + color for dayBtn
        progressBar = view.findViewById(R.id.progressBar);
        startProgress = view.findViewById(R.id.addProgress);
        TextView percentText = view.findViewById(R.id.percent);

        day1Btn = view.findViewById(R.id.day1);
        day2Btn = view.findViewById(R.id.day2);
        day3Btn = view.findViewById(R.id.day3);
        day4Btn = view.findViewById(R.id.day4);
        day5Btn = view.findViewById(R.id.day5);

        startProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentProgress = CurrentProgress + 20;
                progressBar.setProgress(CurrentProgress);
                progressBar.setMax(100);
                startActivity(new Intent(getActivity(),ReportQuestion.class));

                String percent = String.valueOf(CurrentProgress);
                if (CurrentProgress < 100)
                {
                    percentText.setText(percent);
                }
                else
                {
                    percentText.setText("100");
                }

                //change button color when user click addbtn
                if (CurrentProgress <= 20)
                {
                    day1Btn.setBackgroundColor(getResources().getColor(R.color.themecol));
                }
                else if (CurrentProgress > 20 && CurrentProgress <= 40)
                {
                    day1Btn.setBackgroundColor(getResources().getColor(R.color.white));
                    day2Btn.setBackgroundColor(getResources().getColor(R.color.themecol));
                }
                else if (CurrentProgress > 40 && CurrentProgress <= 60)
                {
                    day2Btn.setBackgroundColor(getResources().getColor(R.color.white));
                    day3Btn.setBackgroundColor(getResources().getColor(R.color.themecol));
                }
                else if (CurrentProgress > 60 && CurrentProgress <= 80)
                {
                    day3Btn.setBackgroundColor(getResources().getColor(R.color.white));
                    day4Btn.setBackgroundColor(getResources().getColor(R.color.themecol));
                }
                else if (CurrentProgress > 80 && CurrentProgress <= 100)
                {
                    day4Btn.setBackgroundColor(getResources().getColor(R.color.white));
                    day5Btn.setBackgroundColor(getResources().getColor(R.color.themecol));
                }
            }
        });


        EmergencyBtn = view.findViewById(R.id.Emergency);
        EmergencyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Emergency.class));
            }
        });

        return view;
    }
}