package com.example.workstation.healthify;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class signin extends AppCompatActivity {
    private EditText Email;
    private EditText Password;
    private Button Signin;
    private FirebaseAuth mAuth;
    private TextView signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null)
        {
            Intent I = new Intent(getApplicationContext(),dashboard.class);
            startActivity(I);
        }
        else
        {
            signup = (TextView) findViewById(R.id.SSIGNUP);
            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            Signin = (Button) findViewById(R.id.SSIGNIN);
            Signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Email = (EditText) findViewById(R.id.SEMAIL);
                    Password = (EditText) findViewById(R.id.SPASS);
                    String email = Email.getText().toString();
                    String pass = Password.getText().toString();
                    if(email.isEmpty() && pass.isEmpty())
                    {
                        Toast.makeText(getApplicationContext(),"Fields Required",Toast.LENGTH_SHORT).show();
                    }
                    else if(pass.isEmpty())
                    {
                        Password.setError("Password Required");
                        Password.requestFocus();
                    }
                    else if(email.isEmpty())
                    {
                        Email.setError("Email Required");
                        Email.requestFocus();
                    }
                    else
                    {
                        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(signin.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful())
                                {
                                    Exception e = task.getException();
                                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(getApplicationContext(),"Login Successful!",Toast.LENGTH_SHORT).show();
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
