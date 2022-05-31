package com.fahadcoder.p11.View.ForMembers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fahadcoder.p11.R;
import com.fahadcoder.p11.model.ClassBooking;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ConfirmPrice extends AppCompatActivity {

    //Reference for the UI elements
    TextView userName,txvprice,txvnumtic,txvdiscount,txvtax,txvtotal;
    ImageView img;

    //Variable
    private int numtic;
    private double total;

    //Reference to the Firebase realtime database
    private FirebaseDatabase database;

    //Reference to a specific node in the database
    private DatabaseReference reference;
    long maxid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_price);

        userName = (TextView)findViewById(R.id.txv_username);
        txvprice = (TextView)findViewById(R.id.txv_priceticket);
        txvnumtic = (TextView)findViewById(R.id.txv_numtic);
        txvdiscount = (TextView)findViewById(R.id.txv_discount);
        txvtax = (TextView)findViewById(R.id.txv_tax);
        txvtotal = (TextView)findViewById(R.id.txv_total);
        img = (ImageView)findViewById(R.id.imageView);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("booking");

        //here step 1 count number of booking in database and put it in variable maxid
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

        // receive data from booking
        Intent intent = getIntent();
        String username = intent.getStringExtra("UserName");
        String location = intent.getStringExtra("Location");
        double price = intent.getDoubleExtra("PriceOfTic",0);
        numtic = intent.getIntExtra("NumOfTic",0);
        int discount = intent.getIntExtra("Discount",0);
        int tax = intent.getIntExtra("Tax",0);
        total = intent.getDoubleExtra("Total",0);

        // put data to textView which you receive from booking
        userName.setText(username);
        txvprice.setText(String.valueOf(price));
        txvnumtic.setText(String.valueOf(numtic));
        txvdiscount.setText(String.valueOf(discount));
        txvtax.setText(String.valueOf(tax));
        txvtotal.setText(String.valueOf(total));



        // change image depend on variable location

        //park 1
        if(location.matches("Sharjah Desert Park"))
        {
            img.setImageResource(R.drawable.sharjahdesertpark);
        }

        //park 2
        if(location.matches("Sharjah National Park"))
        {
            img.setImageResource(R.drawable.sharjahnationalpark);
        }

        //park 3
        if(location.matches("Al Montazah Parks"))
        {
            img.setImageResource(R.drawable.almontazahparks);
        }

        //park 4
        if(location.matches("Al Majaz Splash Park"))
        {
            img.setImageResource(R.drawable.almajazsplashpark);
        }


    }

                        /****************** ON CLICK ****************/
                        /**
                         * confrim
                         * back
                         **/

    public void confirm(View view)
    {
        String username = userName.getText().toString();

        //receive from booking
        Intent intent = getIntent();
        int pknum = intent.getIntExtra("ParkNum",0);
        int id = intent.getIntExtra("id",0);




        //here step 2 to create object
        final long bookingnum = maxid + 1;
        final ClassBooking booking = new ClassBooking(bookingnum,pknum,id,numtic,total);

        reference.child(String.valueOf(bookingnum)).setValue(booking).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                String message = "Booking sucessfully";
                Toast.makeText(ConfirmPrice.this, message, Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(ConfirmPrice.this,Congratulation.class);
                intent1.putExtra("UserName",username);
                intent1.putExtra("BookingNum",bookingnum);
                startActivity(intent1);

            }
        });

    }

    //back
    public void Back(View view)
    {
        String username = userName.getText().toString();

        //receive from booking
        Intent intent1 = getIntent();
        String location = intent1.getStringExtra("Location");
        int id = intent1.getIntExtra("id",0);

        //send
        Intent intent = new Intent(ConfirmPrice.this,Booking.class);
        intent.putExtra("UserName",username);
        intent.putExtra("Location",location);
        intent.putExtra("id",id);
        startActivity(intent);
    }
}