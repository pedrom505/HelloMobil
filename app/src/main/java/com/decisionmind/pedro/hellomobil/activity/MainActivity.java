package com.decisionmind.pedro.hellomobil.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.decisionmind.pedro.hellomobil.R;
import com.decisionmind.pedro.hellomobil.adapter.TabAdapter;
import com.decisionmind.pedro.hellomobil.helper.Base64Custom;
import com.decisionmind.pedro.hellomobil.helper.Preferences;
import com.decisionmind.pedro.hellomobil.helper.SlidingTabLayout;
import com.decisionmind.pedro.hellomobil.model.Contact;
import com.decisionmind.pedro.hellomobil.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.decisionmind.pedro.hellomobil.config.configFireBase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar_main;
    private FirebaseAuth authentication;

    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;
    private String contactID;
    private DatabaseReference fireBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        authentication = configFireBase.getFireBaseAuth();

        toolbar_main = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar_main);

        slidingTabLayout = findViewById(R.id.stl_tabs);
        viewPager = findViewById(R.id.vp_page);

        //Configuring sliding tabs
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.colorHighlight));

        //Configuring adapter
        TabAdapter tabAdapter = new TabAdapter( getSupportFragmentManager() );
        viewPager.setAdapter( tabAdapter );

        slidingTabLayout.setViewPager( viewPager );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_add:
                AddContactWindow();
                return true;
            case R.id.item_search:
                return true;
            case R.id.item_about:
                OpenAboutWindow();
                return true;
            case R.id.item_settings:
                return true;
            case R.id.item_exit:
                UserLogout();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void AddContactWindow(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialog_AppCompat_);

        //Configuring the Dialog
        alertDialog.setTitle("Novo contato");
        alertDialog.setMessage("E-mail do usuário");
        alertDialog.setCancelable(false);

        final EditText editText = new EditText(MainActivity.this);
        alertDialog.setView(editText);

        alertDialog.setPositiveButton("Cadastrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String email = editText.getText().toString();


                if(email.isEmpty()){
                    Toast.makeText(MainActivity.this, "Preencha o e-mail!", Toast.LENGTH_LONG).show();
                }else{
                    //verify if the user already is register in system
                    contactID = Base64Custom.CodeBase64(email);
                    fireBase = configFireBase.getFireBase();
                    fireBase = fireBase.child("usuarios").child(contactID);
                    fireBase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            if(dataSnapshot.getValue() != null){

                                //Recovering contact data
                                User userContact = dataSnapshot.getValue(User.class);

                                //Recovering user ID
                                Preferences preferences = new Preferences(MainActivity.this);
                                String userID = preferences.getID();

                                fireBase = configFireBase.getFireBase();
                                fireBase = fireBase.child("contatos").child(userID).child(contactID);

                                Contact contact = new Contact();
                                contact.setUserID(contactID);
                                contact.setEmail(userContact.getEmail());
                                contact.setName(userContact.getName());

                                fireBase.setValue(contact);
                            }else{
                                Toast.makeText(MainActivity.this, "Este usuário não possui cadastro!", Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }
            }
        });

        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.create();
        alertDialog.show();

    }

    public void UserLogout(){
        authentication.signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void OpenAboutWindow(){
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);
    }
}
