package com.example.mpsd2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class ReportFragment extends Fragment implements View.OnClickListener{

    private int CurrentProgress = 0;
    private ProgressBar progressBar;
    private Button startProgress, day1Btn, day2Btn, day3Btn, day4Btn, day5Btn, EmergencyBtn,recoverButton;
    private CheckedTextView questionOne,questionThree;
    private EditText questionTwo,questionFour,questionFive;
    private Button submit;
    private TextView percentText;

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
        percentText = view.findViewById(R.id.percent);
        recoverButton = view.findViewById(R.id.Recovered);

        day1Btn = view.findViewById(R.id.day1);
        day2Btn = view.findViewById(R.id.day2);
        day3Btn = view.findViewById(R.id.day3);
        day4Btn = view.findViewById(R.id.day4);
        day5Btn = view.findViewById(R.id.day5);

        questionOne = (CheckedTextView) view.findViewById(R.id.questionText1);
        questionTwo = view.findViewById(R.id.questionText2);
        questionThree = (CheckedTextView) view.findViewById(R.id.questionText3);
        questionFour = view.findViewById(R.id.questionText4);
        questionFive = view.findViewById(R.id.questionText5);

        submit = view.findViewById(R.id.submitBttn);
        submit.setOnClickListener(this);

        recoverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentProgress = 0;
                percentText.setText("0");
                progressBar.setProgress(0);
                day1Btn.setBackgroundColor(getResources().getColor(R.color.themecol));
                day2Btn.setBackgroundColor(getResources().getColor(R.color.white));
                day3Btn.setBackgroundColor(getResources().getColor(R.color.white));
                day4Btn.setBackgroundColor(getResources().getColor(R.color.white));
                day5Btn.setBackgroundColor(getResources().getColor(R.color.white));
            }
        });

        questionOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionOne.toggle();
                if (questionOne.isChecked()) {
                    Toast.makeText(getActivity(), "Checked", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        questionThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionThree.toggle();
                if (questionThree.isChecked()) {
                    Toast.makeText(getActivity(), "Checked", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Unchecked", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submitBttn:
                if (questionTwo.getText().toString().isEmpty() && questionFour.getText().toString().isEmpty() && questionFive.getText().toString().isEmpty()){
                    questionTwo.setError("You haven't completed this question!");
                    questionFour.setError("You haven't completed this question!");
                    questionFive.setError("You haven't completed this question!");
                } if (questionOne.isChecked() && questionThree.isChecked()){
                    addProgress();
                    Toast.makeText(getActivity(), "Received Report", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "The question is not fully answered.", Toast.LENGTH_SHORT).show();
                }
        }
    }

    public void addProgress(){
        CurrentProgress = CurrentProgress + 20;
        progressBar.setProgress(CurrentProgress);
        progressBar.setMax(100);

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
}