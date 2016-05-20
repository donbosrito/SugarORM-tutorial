package com.application.don.orm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.application.don.orm.model.Person;
import com.orm.SugarRecord;

public class EditActivity extends AppCompatActivity {

    Person person;
    String action;

    Button btnOK, btnCancel;
    EditText edtName, edtAge, edtJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //Add controls
        btnOK = (Button) findViewById(R.id.btnOK);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        edtName = (EditText) findViewById(R.id.edtName);
        edtAge = (EditText) findViewById(R.id.edtAge);
        edtJob = (EditText) findViewById(R.id.edtJob);

        Intent intent = getIntent();
        switch (intent.getStringExtra("action")) {
            case "addNewPerson":
                action = "Add";
                break;

            case "editPerson":
                Long id = intent.getLongExtra("id", 0);
                person = Person.findById(Person.class, id);

                //Set data for editText
                edtName.setText(person.getName());
                edtAge.setText(String.valueOf(person.getAge()));
                edtJob.setText(person.getJob());
                action = "Edit";
                break;
        }

        //Add event for controls
        AddEvents();
    }

    private void AddEvents() {
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add person
                if (action == "Add")
                {
                    person = new Person();
                    Toast.makeText(getApplicationContext(), "This Person is added", Toast.LENGTH_SHORT).show();
                }
                //Update person
                else if (action == "Edit") {
                    Toast.makeText(getApplicationContext(), "This Person is updated", Toast.LENGTH_SHORT).show();
                }
                person.setName(edtName.getText().toString());
                person.setAge(Integer.parseInt(edtAge.getText().toString()));
                person.setJob(edtJob.getText().toString());
                person.save();
                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
