package com.decisionmind.pedro.hellomobil.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.decisionmind.pedro.hellomobil.R;
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


    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<String> contacts;
    private DatabaseReference fireBase;

    public ContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        contacts = new ArrayList<>();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        listView = view.findViewById(R.id.lv_contacts);

        //Mount listview and adapter
        adapter = new ArrayAdapter(
                getActivity(),
                android.R.layout.simple_list_item_1,
                contacts
        );
        listView.setAdapter(adapter);

        //Recovering firebase contacts
        Preferences preferences = new Preferences(getActivity());
        String userID = preferences.getID();
        fireBase = configFireBase.getFireBase().child("contatos").child(userID);

        //
        fireBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clear list
                contacts.clear();

                //Listing contacts
                for (DataSnapshot data: dataSnapshot.getChildren()){
                    Contact contact = data.getValue(Contact.class);
                    contacts.add(contact.getName());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return view;
    }

}
