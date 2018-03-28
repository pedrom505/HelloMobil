package com.decisionmind.pedro.hellomobil.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.decisionmind.pedro.hellomobil.R;
import com.decisionmind.pedro.hellomobil.model.Contact;

import java.util.ArrayList;

/**
 * Created by pedro on 27/03/18.
 */

public class ContactsAdapter extends ArrayAdapter<Contact> {

    private ArrayList<Contact> contacts;
    private Context context;

    public ContactsAdapter(@NonNull Context context, @NonNull ArrayList<Contact> objects) {
        super(context, 0, objects);
        this.contacts = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = null;

        //Verifying if the list od contacts is empty
        if(contacts != null){
            //Initializing the object for mounting the view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            // Mount view from xml file
            view = inflater.inflate(R.layout.contact_adapter, parent, false);

            //recovering element to display
            TextView textView_contact = view.findViewById(R.id.textView_contact);
            TextView textView_email = view.findViewById(R.id.textView_email);
            Contact contact = contacts.get(position);
            textView_contact.setText(contact.getName());
            textView_email.setText(contact.getEmail());
        }
        return view;

    }
}
