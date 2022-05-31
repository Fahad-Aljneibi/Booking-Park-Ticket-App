package com.fahadcoder.p11.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fahadcoder.p11.model.Alter;
import com.fahadcoder.p11.R;
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

import java.util.ArrayList;
import java.util.List;

public class SingUp extends AppCompatActivity {

    //Reference for the UI elements
    EditText edtusername, edtemail, edtpassword;
    private ProgressBar progressBar;

    //create object Alter
    Alter alert = new Alter();

    //Reference to the Firebase realtime database
    private FirebaseDatabase database;

    //Reference to a specific node in the database
    private DatabaseReference reference;

    // Reference to Firebase Auth
    private FirebaseAuth auth;

    //Variable to count number of object in database
    long maxid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        edtusername = findViewById(R.id.edt_username);
        edtemail = findViewById(R.id.edt_email);
        edtpassword = findViewById(R.id.edt_password);
        progressBar = findViewById(R.id.progressBar3);


        database = FirebaseDatabase.getInstance();
        reference = database.getReference("members");

        //Get firebase auth object
        auth = FirebaseAuth.getInstance();


        //to generate id number of members
        //1- count number of members in database and put it in variable maxid
        //2- when create object put id as maxid +1


        //here step 1 count number of members in database and put it in variable maxid
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    maxid = (dataSnapshot.getChildrenCount());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

                            /********************** ON CLICK *******************/
                            /**
                             * signup
                             * signin
                             **/

    public void signup(View view) {

        // Validate the input
        if ((!validateUsername()) | (!validateEmail()) | (!validatePassword())) {


            alert.sendMsg("Error", "Fix the errors on the screen", SingUp.this);
        } else {


            // get all inputs
            String username = edtusername.getText().toString();
            String email = edtemail.getText().toString();
            String password = edtpassword.getText().toString();

            progressBar.setVisibility(View.VISIBLE);

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    //to sing up follow many step
                    //1- get all emails in database and put it in array list
                    //2- compare email text with each email in array list
                    //3- if don't find same email in database application  will sign up

                    //here step 1 to get all emails
                    List<String> emailMembers = new ArrayList<>();
                    for (DataSnapshot node : snapshot.getChildren()) {
                        Member member = node.getValue(Member.class);
                        emailMembers.add(member.getEmail());
                    }

                    //here step 2 to compare
                    boolean newRegister = true;
                    for (int n = 0; n < emailMembers.size(); n++) {
                        String e = emailMembers.get(n);
                        if (email.equals(e)) {
                            newRegister = false;
                            break;
                        }
                    }


                    // here application will sign up if don't find same email in database
                    if (!newRegister) {
                        String message = "a member with same email already exsits";
                        Toast.makeText(SingUp.this, message, Toast.LENGTH_SHORT).show();
                    } else {
                        //here step 2 to create object
                        final long id = maxid + 1;
                        final Member member = new Member(id, username, email, password);

                        reference.child(String.valueOf(id)).setValue(member).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                edtusername.setText("");
                                edtemail.setText("");
                                edtpassword.setText("");
                                String message = "Member Addded sucessfully";
                                Toast.makeText(SingUp.this, message, Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    String message = "Registration successful";
                    Toast.makeText(SingUp.this, message, Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);



                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    String message = "Registration failed. " + e.getMessage();
                    Toast.makeText(SingUp.this, message, Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            });
        }


    }


    public void signin(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }


                                         /***************************** VLIDATION ***************************/

    private Boolean validateEmail() {
        String val = edtemail.getText().toString();
        String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})+$";
        if (val.isEmpty()) {
            edtemail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            edtemail.setError("Invalid email address");
            return false;
        } else {
            edtemail.setError(null);
            return true;
        }
    }

    private Boolean validateUsername() {
        String val = edtusername.getText().toString();
        String noWhiteSpace = "\\A\\w{4,10}\\z";
        if (val.isEmpty()) {
            edtusername.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 10) {
            edtusername.setError("Username too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            edtusername.setError("White Spaces are not allowed");
            return false;
        } else {
            edtusername.setError(null);
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

        String val = edtpassword.getText().toString().trim();
        if (val.isEmpty()) {
            edtpassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(PasswordPattern)) {
            edtpassword.setError("Password too weak");
            return false;
        } else {
            edtpassword.setError(null);
            return true;
        }
    }







}