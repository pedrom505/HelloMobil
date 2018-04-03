package com.decisionmind.pedro.hellomobil.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.decisionmind.pedro.hellomobil.R;
import com.decisionmind.pedro.hellomobil.activity.ChatActivity;
import com.decisionmind.pedro.hellomobil.adapter.ContactsAdapter;
import com.decisionmind.pedro.hellomobil.helper.Preferences;
import com.decisionmind.pedro.hellomobil.model.Contact;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import com.decisionmind.pedro.hellomobil.config.configFireBase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment {


    private ListView listView_contacts;
    private ArrayAdapter adapter;
    private ArrayList<Contact> contacts;
    private DatabaseReference fireBase;
    private ValueEventListener eventListenerContacts;

    public ContactsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        fireBase.addValueEventListener(eventListenerContacts);
    }

    @Override
    public void onStop() {
        super.onStop();
        // When the app is finish or he fragment is not showing the event listener is stopped
        fireBase.removeEventListener(eventListenerContacts);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        contacts = new ArrayList<>();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        listView_contacts = view.findViewById(R.id.listView_contacts);

        //Mount listview and adapter
        /*adapter = new ArrayAdapter(
                getActivity(),
                android.R.layout.simple_list_item_1,
                contacts
        );*/
        adapter = new ContactsAdapter(getActivity(), contacts);

        listView_contacts.setAdapter(adapter);

        //Recovering firebase contacts
        Preferences preferences = new Preferences(getActivity());
        String userID = preferences.getID();
        fireBase = configFireBase.getFireBase().child("contatos").child(userID);

        eventListenerContacts = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clear list
                contacts.clear();

                //Listing contacts
                for (DataSnapshot data: dataSnapshot.getChildren()){
                    Contact contact = data.getValue(Contact.class);
                    contacts.add( contact );
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        listView_contacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                startActivity(intent);
            }
        });



        return view;
    }

}
