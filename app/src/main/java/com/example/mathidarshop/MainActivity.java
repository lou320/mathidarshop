package com.example.mathidarshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_caculate, btn_addToDB, btn_show_all;
    EditText ammount,ammount2,ammount3,ammount4,kunar;
    Spinner spinner2;
    ListView showListview;
    TextView nameView,totalView;

    DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_caculate = findViewById(R.id.btn_caculate);
        btn_addToDB = findViewById(R.id.btn_addToDB);
        btn_show_all = findViewById(R.id.btn_showDatas);
        ammount = findViewById(R.id.ammount);
        ammount2 = findViewById(R.id.ammount2);
        ammount3 = findViewById(R.id.ammount3);
        ammount4  = findViewById(R.id.ammount4);
        kunar = findViewById(R.id.kunar);
        spinner2 = findViewById(R.id.spinner2);
        showListview = findViewById(R.id.showListview);
        nameView = findViewById(R.id.nameView);
//        totalView = findViewById(R.id.TotalView);

        showAllInListView(dataBaseHelper);

        btn_caculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkerValue workerValue;
                try {
                    workerValue = new WorkerValue(-1,spinner2.getSelectedItem().toString(),Float.parseFloat(ammount.getText().toString()), Float.parseFloat(ammount2.getText().toString()),Float.parseFloat(ammount3.getText().toString()),Float.parseFloat(ammount4.getText().toString()),Float.parseFloat(kunar.getText().toString()));
                    nameView.setText(workerValue.getName() +"\n" + String.valueOf(workerValue.caculate()));
                } catch (Exception e){
                    Toast.makeText(MainActivity.this, "Calculation fail please check back arguments not to be empty.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_addToDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkerValue workerValue;
                boolean success = false;
                try {
                    workerValue = new WorkerValue(-1,spinner2.getSelectedItem().toString(),Float.parseFloat(ammount.getText().toString()), Float.parseFloat(ammount2.getText().toString()),Float.parseFloat(ammount3.getText().toString()),Float.parseFloat(ammount4.getText().toString()),Float.parseFloat(kunar.getText().toString()));
                    success = dataBaseHelper.addWorkerValue(workerValue);
                } catch (Exception e){
                    Toast.makeText(MainActivity.this, "Calculation fail please check back arguments not to be empty.", Toast.LENGTH_SHORT).show();
                }
                if (success){
                    Toast.makeText(MainActivity.this, "Added to database.", Toast.LENGTH_SHORT).show();
                    showAllInListView(dataBaseHelper);
                } else{
                    Toast.makeText(MainActivity.this, "Adding to database fail.", Toast.LENGTH_SHORT).show();
                }
                
            }
        });
        btn_show_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAllInListView(dataBaseHelper);
            }
        });
        showListview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String workerValue = (String) adapterView.getItemAtPosition(i);
                dataBaseHelper.deleteOneValue(workerValue);
                showAllInListView(dataBaseHelper);
                Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }


    private void showAllInListView(DataBaseHelper dataBaseHelper2) {
        List<String> showAllWork = dataBaseHelper2.getEveryOne();
        ArrayAdapter workerValueArrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, showAllWork);
        showListview.setAdapter(workerValueArrayAdapter);
    }
}