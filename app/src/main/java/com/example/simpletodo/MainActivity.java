package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTD;
    Button btnAdd;
    Button btnClear;
    Button btnDelete;
    ListView lvToDo;
    Spinner spnAddDelete;
    ArrayList<String> alToDo;
    ArrayAdapter<String> aaToDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTD = findViewById(R.id.editTextTD);
        btnAdd = findViewById(R.id.buttonAddItems);
        btnClear = findViewById(R.id.buttonClearItems);
        btnDelete = findViewById(R.id.buttonDeleteItems);
        lvToDo = findViewById(R.id.listViewTD);
        spnAddDelete = findViewById(R.id.spinner);

        alToDo = new ArrayList<>();

        aaToDo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alToDo);
        lvToDo.setAdapter(aaToDo);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etTD.getText().toString();
                alToDo.add(task);
                aaToDo.notifyDataSetChanged();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etTD.setText("");
                alToDo.clear();
                aaToDo.notifyDataSetChanged();
            }
        });

        spnAddDelete.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        etTD.setText("");
                        etTD.setHint("Type in a new task");
                        break;

                    case 1:
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        etTD.setText("");
                        etTD.setHint("Type in the index of the task to be removed");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(etTD.getText().toString());

                if(alToDo.size() > 0) {
                    if (pos + 1 > alToDo.size()) {
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                    } else {
                        alToDo.remove(pos);
                        aaToDo.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}