package com.fahadcoder.p11.View.ForAdmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fahadcoder.p11.DAO.AdminMember;
import com.fahadcoder.p11.R;
import com.fahadcoder.p11.model.Member;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminUpdateMember extends AppCompatActivity {

    //Reference for the UI elements
    EditText edtusername,edtemail,edtpassword;
    TextView txv_id;

    //Reference to the Firebase realtime database
    private FirebaseDatabase database;

    //Reference to a specific node in the database
    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_member);

        txv_id = (TextView)findViewById(R.id.txv_idbknum);
        edtusername = (EditText)findViewById(R.id.edtpknum);
        edtemail = (EditText)findViewById(R.id.edtid);
        edtpassword = (EditText)findViewById(R.id.edt_numtic);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("members");

        //receive from admin member
        Intent intent = getIntent();
        final long id = intent.getLongExtra("idMemeber",0);
        txv_id.setText(String.valueOf(id));

        // Retrieve the car by its plate number

        reference.child(String.valueOf(id)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists())
                {
                    String username = dataSnapshot.child("username").getValue(String.class);
                    String email = dataSnapshot.child("email").getValue(String.class);
                    String password = dataSnapshot.child("password").getValue(String.class);


                    //Display car details
                    edtusername.setText(username);
                    edtemail.setText(email);
                    edtpassword.setText(password);
                }
                else
                {
                    String message = "No Member Found";
                    displayToast(message);

                    //Empty the edit text boxes
                    edtusername.setText("");
                    edtemail.setText("");
                    edtpassword.setText("");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

                        /************************* ON CLICK *************************/
                        /**
                         * updateMember
                         * displayToast(String message)
                         * Back
                         **/
    public void updateMember(View view)
    {
        // 1 - get all inputs
        final long id = Long.parseLong(txv_id.getText().toString());
        String username = edtusername.getText().toString();
        String email = edtemail.getText().toString();
        String password = edtpassword.getText().toString();

        // Retrieve the car by its plate number

        reference.child(String.valueOf(id)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists())
                {
/*                    String make = etMake.getText().toString();
                    String model = etModel.getText().toString();
                    int year = Integer.parseInt(etYear.getText().toString());
                    int price = Integer.parseInt(etPrice.getText().toString());*/

                    //Car car = new Car(Integer.parseInt(plate),make,model,year,price);
                    Member member = new Member(id,username,email,password);
                    reference.child(String.valueOf(id)).setValue(member).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            String message = "Member updated";
                            displayToast(message);
                        }
                    }); //update the car


                }
                else
                {
                    String message = "No Member Found";
                    displayToast(message);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    private void displayToast(String message)
    {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(AdminUpdateMember.this,message,duration);
        toast.setGravity(Gravity.TOP,100,500);
        toast.show();
    }
    //back to admin member
    public void Back(View view)
    {
        Intent intent = new Intent(this,AdminMember.class);
        startActivity(intent);
    }
}