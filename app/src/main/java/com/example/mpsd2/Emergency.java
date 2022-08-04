package com.example.mpsd2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Emergency extends AppCompatActivity implements View.OnClickListener{

    private TextView doc_num;
    private Button contactDocBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        contactDocBtn = (Button) findViewById(R.id.contactDocBtn);
        doc_num = (TextView) findViewById(R.id.doc_num1);

        contactDocBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.contactDocBtn:
                String phone = doc_num.getText().toString();
                if (phone.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please Enter a Number", Toast.LENGTH_SHORT).show();
                }else {
                    String s = "tel:" + phone;
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(s));
                    startActivity(intent);
                }
                break;
        }
    }
}