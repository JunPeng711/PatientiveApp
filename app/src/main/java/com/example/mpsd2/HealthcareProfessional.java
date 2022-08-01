package com.example.mpsd2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.mpsd2.doc_psychological.DoctorPsychological;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HealthcareProfessional extends AppCompatActivity implements View.OnClickListener{

    ListView listView;
    SearchView searchView;
    String [] nameList = {"Psychological", "Covid", "Dental", "Mental", "Skincare", "General"};

    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthcare_professional);

        listView = (ListView) findViewById(R.id.DocListView);
        searchView = (SearchView) findViewById(R.id.DocListSearchView);

        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,nameList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Toast.makeText(HealthcareProfessional.this, "Clicked: " + nameList[i].toString(), Toast.LENGTH_LONG).show();
                switch (i){
                    case 0:
                        //Psychological
                        startActivity(new Intent(HealthcareProfessional.this, DoctorPsychological.class));
                        break;
                    case 1:
                        //Covid
                        break;
                    case 2:
                        //Dental
                        break;
                    default:
                        //nothing
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                HealthcareProfessional.this.arrayAdapter.getFilter().filter(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                HealthcareProfessional.this.arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

        }
    }

}