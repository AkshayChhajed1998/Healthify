package com.example.workstation.healthify;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDb;
    private EditText EMAIL;
    private EditText PASS;
    private Button SIGNUP;
    private TextView SIGNIN;
    private  TextView fname,lname,uname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        mDb = FirebaseDatabase.getInstance();
        final DatabaseReference ref = mDb.getReference();

        if(mAuth.getCurrentUser()!=null)
        {
            Intent I = new Intent(this,dashboard.class);
            startActivity(I);
        }
        else
        {
            EMAIL = (EditText) findViewById(R.id.EMAIL);
            PASS = (EditText) findViewById(R.id.PASS);
            SIGNUP = (Button) findViewById(R.id.SIGNUP);
            SIGNIN = (TextView) findViewById(R.id.SIGNIN);
            fname = (EditText) findViewById(R.id.fname);
            lname = (EditText) findViewById(R.id.lname);
            uname = (EditText) findViewById(R.id.username);
            SIGNIN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent I1 = new Intent(getApplicationContext(),signin.class);
                    startActivity(I1);
                }
            });
            SIGNUP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String email = EMAIL.getText().toString();
                    String pass = PASS.getText().toString();
                    if(email.isEmpty())
                    {
                        EMAIL.setError("Provide ur EmailID");
                        EMAIL.requestFocus();
                    }
                    else if(pass.isEmpty())
                    {
                        PASS.setError("Provide ur Password");
                        PASS.requestFocus();
                    }
                    else if((email.isEmpty() && pass.isEmpty()))
                    {
                        Toast.makeText(getApplicationContext(),"Fill the Form",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful())
                                {
                                    Exception e = task.getException();
                                    Toast.makeText(getApplicationContext(),"Registration Unsuccessful :"+e.toString(),Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    User u = new User(user.getUid(),user.getEmail(),uname.getText().toString(),fname.getText().toString(),lname.getText().toString());
                                    Fitness f = new Fitness(user.getUid(),"20000","20.5");
                                    ref.child("users").child(user.getUid()).setValue(u);
                                    ref.child("fitness").child(user.getUid()).setValue(f);
                                    Log.i("USER:==>", user.getEmail());
                                    Toast.makeText(getApplicationContext(),"USER:"+user.getEmail(),Toast.LENGTH_LONG).show();
                                    Intent I = new Intent(getApplicationContext(),dashboard.class);
                                    startActivity(I);
                                }
                            }
                        });
                    }

                }
            });
        }

    }

}
