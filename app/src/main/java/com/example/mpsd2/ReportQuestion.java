package com.example.mpsd2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Toast;

public class ReportQuestion extends AppCompatActivity implements View.OnClickListener {

    private CheckedTextView questionOne,questionThree;
    private EditText questionTwo,questionFour,questionFive;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_question);

        questionOne = (CheckedTextView) findViewById(R.id.questionText1);
        questionTwo = findViewById(R.id.questionText2);
        questionThree = (CheckedTextView) findViewById(R.id.questionText3);
        questionFour = findViewById(R.id.questionText4);
        questionFive = findViewById(R.id.questionText5);

        submit = findViewById(R.id.submitBttn);
        submit.setOnClickListener(this);

        questionOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionOne.toggle();
                if (questionOne.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Checked", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        questionThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionThree.toggle();
                if (questionThree.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Checked", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submitBttn:
                if (questionTwo.getText().toString().isEmpty() & questionFour.getText().toString().isEmpty() & questionFive.getText().toString().isEmpty()){
                    questionTwo.setError("You haven't completed this question!");
                    questionFour.setError("You haven't completed this question!");
                    questionFive.setError("You haven't completed this question!");
                }else{
                    if (questionOne.isChecked() & questionThree.isChecked()){
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        Toast.makeText(this, "Received Report", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this, "The question is not fully answered.", Toast.LENGTH_SHORT).show();
                    }
                }


        }
    }
}