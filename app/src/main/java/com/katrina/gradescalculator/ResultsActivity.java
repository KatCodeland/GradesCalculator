package com.katrina.gradescalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {

    private ArrayList<String> resultGrades;
    private int resultAverage;
    private int resultMin;
    private int resultMax;

    private static final String TAG  = "GradesCalculator";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() method called");
        setContentView(R.layout.activity_results);

        //extract info from intent
        resultGrades = getIntent().getStringArrayListExtra("GRADES");
        resultAverage = getIntent().getIntExtra("AVERAGE", 0);
        resultMin = getIntent().getIntExtra("MIN", 0);
        resultMax = getIntent().getIntExtra("MAX", 0);

        //inflate TextViews & set text
        TextView androidTV = (TextView) findViewById(R.id.view_android);
        androidTV.setText(resultGrades.get(0));

        TextView javaTV = (TextView) findViewById(R.id.view_java);
        javaTV.setText(resultGrades.get(1));

        TextView xmlTV = (TextView) findViewById(R.id.view_xml);
        xmlTV.setText(resultGrades.get(2));

        TextView oopTV = (TextView) findViewById(R.id.view_oop);
        oopTV.setText(resultGrades.get(3));

        if (resultAverage != 0){
            TextView calcResultTV = (TextView) findViewById(R.id.view_calcResult);
            calcResultTV.setText("Average: " + resultAverage);
        }
        if (resultMin != 0){
            TextView calcResultTV = (TextView) findViewById(R.id.view_calcResult);
            calcResultTV.setText("Min: " + resultMin);
        }
        if (resultMax != 0){
            TextView calcResultTV = (TextView) findViewById(R.id.view_calcResult);
            calcResultTV.setText("Max: " + resultMax);
        }
    }



}
