package com.example.workstation.healthify;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class dashboard extends AppCompatActivity {
    private TextView welcomemsg;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDb;
    private FirebaseUser user;
    private Button SIGNOUT,CLOSE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mAuth = FirebaseAuth.getInstance();
        mDb = FirebaseDatabase.getInstance();
        DatabaseReference ref = mDb.getReference();
        user = mAuth.getCurrentUser();
        ref.child("users").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User u = dataSnapshot.getValue(User.class);
                updateUserUI(u);
                Log.i("user:----",u.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ref.child("fitness").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Fitness f = dataSnapshot.getValue(Fitness.class);
                updateFitnessUI(f);
                Log.i("fitness:-----",f.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void updateWelUI(String msg)
    {
        TextView welc = (TextView) findViewById(R.id.welcomemsg);
        welc.setText("HellOOOOo, "+msg);
    }

    public void updateUserUI(User user)
    {
        TextView username = (TextView) findViewById(R.id.username);
        username.setText("Welcome "+user.getUsername());
    }

    public void updateFitnessUI(Fitness fitness)
    {
        TextView kmwalked = (TextView) findViewById(R.id.kmwalked);
        TextView footcounts = (TextView) findViewById(R.id.footcount);

        kmwalked.setText("Kilometre Walked : "+fitness.getKmWalked());
        footcounts.setText("Foot Steps Today : "+fitness.getStepCount());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbardb,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void signout(View v)
    {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        finish();
    }

    public void close(View v)
    {
        finishAffinity();
    }

}
