package com.myapplicationdev.android.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert;
    Button btnGetTasks;
    TextView tvResults;
    ListView lvResults;
    EditText etTask;
    EditText etDate;
    boolean asc = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        btnInsert = findViewById(R.id.btnInsert);
        lvResults = findViewById(R.id.lvResults);
        etTask = findViewById(R.id.etTask);
        etDate = findViewById(R.id.etDate);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                db.insertTask(etTask.getText().toString(), etDate.getText().toString());
            }
        });
        btnGetTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                ArrayList<String> data = db.getTaskContent();
                db.close();

                DBHelper db2 = new DBHelper(MainActivity.this);
                ArrayList<Task> data2 = db.getTasks(asc);
                db2.close();
                asc = !asc;

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i+1 + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);
                ArrayAdapter aa = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,data2);
                lvResults.setAdapter(aa);
            }
        });
    }
}