package com.application.don.orm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.application.don.orm.Adapter.PersonAdapter;
import com.application.don.orm.model.Person;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    List<Person> lstPerson;
    PersonAdapter adapter;
    TextView txtCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get all data
        lstPerson = Person.listAll(Person.class);

        txtCount = (TextView) findViewById(R.id.txtCount);
        //Count data in database
        long count = Person.count(Person.class);
        txtCount.setText(count + " items");

        listView = (ListView) findViewById(R.id.listView);
        adapter = new PersonAdapter(this, lstPerson);
        listView.setAdapter(adapter);
        //Edit data
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Share person id to EditActivity.
                Person person = adapter.getItem(position);
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("action", "editPerson");
                intent.putExtra("id", person.getId());
                startActivity(intent);
                finish();
            }
        });
        //Show label when list is empty
        listView.setEmptyView(findViewById(R.id.empty_element));
        //Set context menu for listview
        registerForContextMenu(listView);
    }

    //Show Option menu: Add new, Delete all.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    //add new data
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add_new:
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("action", "addNewPerson");
                startActivity(intent);
                finish();
                break;
            case R.id.action_delete_all:
                Person.deleteAll(Person.class);
                UpdateData();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, Menu.FIRST, 0, "Delete");
    }
    //delete data
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case Menu.FIRST:
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                Person person = adapter.getItem(info.position);
                person.delete();
                UpdateData();
                return true;
        }
        return super.onContextItemSelected(item);
    }
    //Update list
    public void UpdateData() {
        lstPerson = Person.listAll(Person.class);
        adapter = new PersonAdapter(this, lstPerson);
        listView.setAdapter(adapter);
        long count = Person.count(Person.class);
        txtCount.setText(count + " items");
    }
}
