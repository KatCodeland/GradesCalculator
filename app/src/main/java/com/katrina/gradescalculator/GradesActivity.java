package com.katrina.gradescalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;



public class GradesActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText androidText;
    private EditText javaText;
    private EditText xmlText;
    private EditText oopText;
    private View calculate;
    private View about;
    private int averageGrade;
    private int minGrade;
    private int maxGrade;
    private ArrayList<String> grades = new ArrayList<>();
    private String TAG;
    private String[] colleges = {
        "Seneca Valley College",
        "Hummingbird Academy",
        "Harrington High", "Georgian Bay College",
        "Saracuse Academy",
        "St-Vincent College",
        "Seneca College",
        "Seneca York",
        "Seneca North"
    };
    String LOGTAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i(TAG, "Katrina Copeland");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);

        //Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, colleges);

        //inflate views
        AutoCompleteTextView collegeSearch = (AutoCompleteTextView) findViewById(R.id.college_actv);
        //collegeSearch.setThreshold(3);
        collegeSearch.setAdapter(adapter);

        androidText = (EditText) findViewById(R.id.text_android);
        javaText = (EditText) findViewById(R.id.text_java);
        xmlText = (EditText) findViewById(R.id.text_xml);
        oopText = (EditText) findViewById(R.id.text_oop);
        calculate = (Button) findViewById(R.id.button_calc);
        calculate.setOnClickListener(this);
        Button btn_min = (Button) findViewById(R.id.min_button);
        btn_min.setOnClickListener(this);
        Button btn_max = (Button) findViewById(R.id.max_button);
        btn_max.setOnClickListener(this);
        Button btn_avg = (Button) findViewById(R.id.avg_button);
        btn_avg.setOnClickListener(this);
        about = (Button) findViewById(R.id.button_about);
        about.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        //inflate & return menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.about_menu:
                Intent aboutIntent = new Intent(GradesActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
                break;

            case R.id.min_menu:
                minGrade = calculateMin();
                createIntent("MIN", minGrade, ResultsActivity.class);
                break;

            case R.id.max_menu:
                maxGrade = calculateMax();
                createIntent("MAX", maxGrade, ResultsActivity.class);
                break;
            case R.id.avg_menu:
                averageGrade = calculateAverage();
                createIntent("AVERAGE", averageGrade, ResultsActivity.class);
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "onClickMethod called");
        //grab user inputs
        grades.add(androidText.getText().toString());
        grades.add(javaText.getText().toString());
        grades.add(xmlText.getText().toString());
        grades.add(oopText.getText().toString());
        //if all edit texts are empty
//            if(grades.get(i) == ""){
//                grades.get(i)= 0;
//            }

        if (null != v) {
            switch (v.getId()){
                case R.id.button_calc:
                    averageGrade = calculateAverage();
//                    Intent calcIntent =  new Intent(GradesActivity.this, ResultsActivity.class);
//                    //add extra to intent, extras will be grades array and final grades int
//                    calcIntent.putExtra("GRADES", grades);
//                    calcIntent.putExtra("AVERAGE", averageGrade);
//                    startActivity(calcIntent);
                    createIntent("AVERAGE", averageGrade, ResultsActivity.class);
                    break;

                case R.id.button_about:
                    Intent aboutIntent = new Intent(GradesActivity.this, AboutActivity.class);
                    startActivity(aboutIntent);
                    break;

                case R.id.min_button:
                    minGrade = calculateMin();
                    createIntent("MIN", minGrade, ResultsActivity.class);
                    break;

                case R.id.max_button:
                    maxGrade = calculateMax();
                    createIntent("MAX", maxGrade, ResultsActivity.class);
                    break;
                case R.id.avg_button:
                    averageGrade = calculateAverage();
                    createIntent("AVERAGE", averageGrade, ResultsActivity.class);
                    break;
            }
        }
    }


    public void createIntent(String key, int data, Class context){
        Intent myIntent =  new Intent(GradesActivity.this, context);
        //add extra to intent, extras will hold data to pass
        myIntent.putExtra("GRADES", grades);
        myIntent.putExtra(key, data);
        startActivity(myIntent);
    }

    public int calculateAverage(){
        int gradeSum = 0;
        int answer = 0;
        for (int i = 0; i < grades.size(); i++){

            gradeSum += Integer.parseInt(grades.get(i));
        }
            answer = (int) gradeSum / grades.size();
        return answer;
    }


    //needs functional programming to improve
    public int calculateMin(){
        int min = Integer.parseInt(grades.get(0));
        int temp = 0;
        for (int i = 1; i < grades.size(); i++){
            temp = Integer.parseInt(grades.get(i));
            if (temp < min){
                min = temp;
            }
        }
        return min;
    }

    public int calculateMax(){
        int max = Integer.parseInt(grades.get(0));
        int temp = 0;
        for (int i = 1; i < grades.size(); i++){
            temp = Integer.parseInt(grades.get(i));
            if (temp > max){
                max = temp;
            }
        }
        return max;
    }


}