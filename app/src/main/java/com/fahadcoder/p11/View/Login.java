package com.fahadcoder.p11.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fahadcoder.p11.R;
import com.fahadcoder.p11.View.ForAdmin.AdminHome;
import com.fahadcoder.p11.View.ForMembers.Home;
import com.fahadcoder.p11.model.Alter;
import com.fahadcoder.p11.model.Member;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    // References to UI elements
    EditText etEmail, etPassword;
    private ProgressBar progressBar;

    // Reference to Firebase Auth
    private FirebaseAuth auth;

    //Reference to the Firebase realtime database
    private FirebaseDatabase database;

    //Reference to a specific node in the database
    private DatabaseReference reference;

    //Variable
    private int[] id = new int[1];
    private String[] user = new String[1];

    //Create object Alert
    Alter alert = new Alter();

                    /*************************** ON CREATE *******************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Get the UI objects from the layout
        etEmail = findViewById(R.id.edt_email);
        etPassword = findViewById(R.id.edt_password);
        progressBar = findViewById(R.id.progressBar2);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("members");


        //Get firebase auth object
        auth = FirebaseAuth.getInstance();


    }



                 /*************************** ON CLICK ***********************/
                 /**
                    * Login
                    * Signup
                  */




    public void login(View view) {

        //get input
        //etEmail = (EditText)findViewById(R.id.edt_email);
        //etPassword = (EditText)findViewById(R.id.edt_password);
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        //check input if admin or user
        if(email.matches("admin") && password.matches("admin"))
        {
            Toast.makeText(this, "Welcome Admin!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Login.this, AdminHome.class);
            startActivity(intent);
        }
        else
        {
            //validation of input
            if ((!validateEmail()) | (!validatePassword())) {


                alert.sendMsg("Error", "Fix the errors on the screen", Login.this);
            }
            else
            {


                // Sign in process in progress, so display the progress bar
                progressBar.setVisibility(View.VISIBLE);


                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot node : snapshot.getChildren()) {
                            Member member = node.getValue(Member.class);
                            //emailMembers.add(member.getEmail());
                            if (email.equals(member.getEmail())) {
                                id[0] = (int) member.getId();
                                user[0] = member.getUsername();
                                break;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        String message = "Sign in successful";

                        Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);

                        String username = Login.this.user[0];
                        int userid = id[0];

                        Intent intent = new Intent(Login.this, Home.class);
                        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("UserName", username);
                        intent.putExtra("id", userid);
                        startActivity(intent);
                        //finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String message = "Sign in failed " + e.getMessage();
                        Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);

                    }
                });




            }



        }


    }





    public void toSignup(View view)
    {
        Intent intent = new Intent(Login.this, SingUp.class);
        startActivity(intent);
    }



                        /*************************** VALIDATION *******************/



    private Boolean validateEmail() {
        String val = etEmail.getText().toString();
        String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})+$";
        if (val.isEmpty()) {
            etEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            etEmail.setError("Invalid etEmail address");
            return false;
        } else {
            etEmail.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String PasswordPattern =
                ("^" +
                        "(?=.*[0-9])" +            //at least 1 digit
                        "(?=.*[a-z])" +           //at least 1 lower case letter
                        "(?=.*[A-Z])" +           //at least 1 upper case letter
                        "(?=.*[a-zA-Z])" +       //any letter
                        "(?=.*[@#$%^&+=])" +     //at least 1 special character
                        "(?=\\S+$)" +           //no white spaces
                        ".{4,}" +               //at least 4 characters
                        "$");

        String val = etPassword.getText().toString().trim();
        if (val.isEmpty()) {
            etPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(PasswordPattern)) {
            etPassword.setError("Please, make sure your etPassword , etPassword too weak");
            return false;
        } else {
            etPassword.setError(null);
            return true;
        }
    }







}