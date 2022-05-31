package com.fahadcoder.p11.DAO;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fahadcoder.p11.View.ForAdmin.AdminHome;
import com.fahadcoder.p11.View.ForAdmin.AdminUpdateBooking;
import com.fahadcoder.p11.View.ForAdmin.AllBooking;
import com.fahadcoder.p11.model.Alter;
import com.fahadcoder.p11.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminBooking extends AppCompatActivity {


    //Reference for the UI elements
    EditText edtbknum;
    Alter alert = new Alter();

    //Reference to the Firebase realtime database
    private FirebaseDatabase database;

    //Reference to a specific node in the database
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_booking);

        edtbknum = (EditText)findViewById(R.id.edt_bknum);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("books");
    }

    //get all booking
    public void getAllBooking(View view)
    {
        Intent intent = new Intent(AdminBooking.this, AllBooking.class);
        intent.putExtra("showall","showall");
        startActivity(intent);
    }

    // find booking
    public void find(View view)
    {
        String bknum = edtbknum.getText().toString();

        if(bknum.isEmpty())
        {
            alert.sendMsg("Error","Fix the errors on the screen",AdminBooking.this);
            edtbknum.setError("Field cannot be empty");
        }
        else
        {
            Intent intent = new Intent(AdminBooking.this,AllBooking.class);
            int Bknum = Integer.parseInt(edtbknum.getText().toString());
            intent.putExtra("findbknum",Bknum);
            startActivity(intent);
        }

    }

    //delete booking
    public void deleteBookingByBkNum(View view)
    {
        String edbknum = edtbknum.getText().toString();

        if(edbknum.isEmpty())
        {
            alert.sendMsg("Error","Fix the errors on the screen",AdminBooking.this);
            edtbknum.setError("Field cannot be empty");
        }
        else
        {
                // Retrieve the member by its id number
                // 1 - get input
                long id = Long.parseLong(edtbknum.getText().toString());
                reference.child(String.valueOf(id)).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        if(dataSnapshot.exists())
                        {
                            reference.child(String.valueOf(id)).setValue(null); //Delete the Member
                            String message = "Booking Deleted";
                            displayToast(message);
                        }
                        else
                        {
                            String message = "No Booking Found";
                            displayToast(message);


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        }



    private void displayToast(String message)
    {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(AdminBooking.this,message,duration);
        toast.setGravity(Gravity.TOP,100,500);
        toast.show();
    }

    //update booking
    public void updateBookingByBknum(View view)
    {
        String edbknum = edtbknum.getText().toString();

        if(edbknum.isEmpty())
        {
            alert.sendMsg("Error","Fix the errors on the screen",AdminBooking.this);
            edtbknum.setError("Field cannot be empty");
        }
        else
        {
            int id = Integer.parseInt(edtbknum.getText().toString());
            Intent intent = new Intent(AdminBooking.this, AdminUpdateBooking.class);
            intent.putExtra("idBooking",id);
            startActivity(intent);
        }


    }

    //back to home
    public void Back(View view)
    {
        Intent intent = new Intent(this, AdminHome.class);
        startActivity(intent);
    }
}