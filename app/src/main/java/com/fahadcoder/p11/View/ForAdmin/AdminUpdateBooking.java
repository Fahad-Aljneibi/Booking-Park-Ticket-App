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

import com.fahadcoder.p11.DAO.AdminBooking;
import com.fahadcoder.p11.R;
import com.fahadcoder.p11.model.ClassBooking;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminUpdateBooking extends AppCompatActivity {

    //Reference for the UI elements
    EditText edtpknum,edtid,edtnumtic,edttotal;
    TextView txvbknum;

    //Reference to the Firebase realtime database
    private FirebaseDatabase database;

    //Reference to a specific node in the database
    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_booking);

        edtpknum = findViewById(R.id.edtpknum);
        edtid = findViewById(R.id.edtid);
        edtnumtic = findViewById(R.id.edt_numtic);
        edttotal = findViewById(R.id.edt_total);
        txvbknum = findViewById(R.id.txv_idbknum);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("booking");

        //recevie data from admin booking
        Intent intent = getIntent();
        int id = intent.getIntExtra("idBooking",0);
        txvbknum.setText(String.valueOf(id));

        reference.child(String.valueOf(id)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists())
                {
                    String userid = dataSnapshot.child("id").getValue(Long.class).toString();
                    String parknum = dataSnapshot.child("park_num").getValue(Integer.class).toString();
                    String numtic = dataSnapshot.child("number_of_ticket").getValue(Integer.class).toString();
                    String total = dataSnapshot.child("total_price").getValue(Double.class).toString();


                    //Display car details
                    edtpknum.setText(parknum);
                    edtid.setText(userid);
                    edtnumtic.setText(numtic);
                    edttotal.setText(total);
                }
                else
                {
                    String message = "No Booking Found";
                    displayToast(message);

                    //Empty the edit text boxes
                    edtid.setText("");
                    edtpknum.setText("");
                    edtnumtic.setText("");
                    edttotal.setText("");
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
        Toast toast = Toast.makeText(AdminUpdateBooking.this,message,duration);
        toast.setGravity(Gravity.TOP,100,500);
        toast.show();
    }

                    /******************** BUTTON *****************/
    public void updateBooking(View view)
    {
        // 1 - get all inputs
        long bknum = Long.parseLong(txvbknum.getText().toString());
        int pknum = Integer.parseInt(edtpknum.getText().toString());
        long id = Long.parseLong(edtid.getText().toString());
        int numTic = Integer.parseInt(edtnumtic.getText().toString());
        double total = Double.parseDouble(edttotal.getText().toString());


        reference.child(String.valueOf(bknum)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists())
                {

                    ClassBooking booking = new ClassBooking(bknum,pknum,id,numTic,total);
                    reference.child(String.valueOf(bknum)).setValue(booking).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            String message = "Booking updated";
                            displayToast(message);
                            Intent intent = new Intent(AdminUpdateBooking.this, AdminBooking.class);
                            startActivity(intent);
                        }
                    }); //update the booking


                }
                else
                {
                    String message = "No Booking Found";
                    displayToast(message);
                    Intent intent = new Intent(AdminUpdateBooking.this, AdminBooking.class);
                    startActivity(intent);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    //back to home
    public void Back(View view)
    {
        Intent intent = new Intent(this,AdminBooking.class);
        startActivity(intent);
    }

}