package com.application.don.orm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.application.don.orm.model.Person;
import com.application.don.orm.R;

import java.util.List;

/**
 * Created by Don on 5/20/16.
 */
public class PersonAdapter extends ArrayAdapter<Person> {

    private List<Person> lstPerson;

    public PersonAdapter(Context context, List<Person> list) {
        super(context, 0, list);
        lstPerson = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Person person = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list_layout, parent, false);

        TextView itemName = (TextView) convertView.findViewById(R.id.txtName);
        TextView itemAge = (TextView) convertView.findViewById(R.id.txtAge);
        TextView itemJob = (TextView) convertView.findViewById(R.id.txtJob);

        itemName.setText(person.getName());
        itemAge.setText(String.valueOf(person.getAge()));
        itemJob.setText(person.getJob());
        return convertView;
    }
}
