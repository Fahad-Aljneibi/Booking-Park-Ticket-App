package com.fahadcoder.p11.View.ForMembers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fahadcoder.p11.R;
import com.fahadcoder.p11.model.Alter;

public class Booking extends AppCompatActivity {

    //Reference for the UI elements
    EditText edtcount;
    TextView userName,txvtime,txvloc,txvphone,txvprice;
    ImageView img;
    Alter alert = new Alter();

    //Variable
    private int park_num;
    private String latitude;
    private String longitude;
    private int discount;
    private int tax;




                    /************************* ON CREATE *************************/
                    /**
                     * 1- RECEIVE DATA FROM HOME
                     * 2- FOR EACH PARK CHANGE IMAGE AND GET DATA FROM DB
                     **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        edtcount = (EditText)findViewById(R.id.edt_count);
        txvtime = (TextView)findViewById(R.id.txv_time);
        txvloc = (TextView)findViewById(R.id.txv_location);
        txvphone = (TextView)findViewById(R.id.txv_phone);
        txvprice = (TextView)findViewById(R.id.txv_priceofticket);
        img = (ImageView)findViewById(R.id.imageView);

        //underline for location
        txvloc.setPaintFlags(txvloc.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

        /** 1 **/

        //receive data from home
        userName = (TextView)findViewById(R.id.txv_username);
        Intent intent = getIntent();
        String username = intent.getStringExtra("UserName");
        userName.setText(username);
        String location = intent.getStringExtra("Location");
        String time = intent.getStringExtra("Time");
        String phone = intent.getStringExtra("Phone");
        double price = intent.getDoubleExtra("Price",0);
        park_num = intent.getIntExtra("ParkNum",0);
        latitude = intent.getStringExtra("Latitude");
        longitude = intent.getStringExtra("Longitude");
        discount = intent.getIntExtra("Discount",0);
        tax = intent.getIntExtra("Tax",0);

        /** 2 **/

        //PARK 1
        if(location.matches("Sharjah Desert Park"))
        {
            //change image
            img.setImageResource(R.drawable.sharjahdesertpark);



                txvtime.setText(time);
                txvloc.setText(location);
                txvphone.setText(phone);
                txvprice.setText(String.valueOf(price));

        }

        //PARK 2
        if(location.matches("Sharjah National Park"))
        {
            //change image
            img.setImageResource(R.drawable.sharjahnationalpark);


                // display on the screen

                txvtime.setText(time);
                txvloc.setText(location);
                txvphone.setText(phone);
                txvprice.setText(String.valueOf(price));

        }


        //PARK 3
        if(location.matches("Al Montazah Parks"))
        {
            //change image
            img.setImageResource(R.drawable.almontazahparks);

                // display on the screen
                txvtime.setText(time);
                txvloc.setText(location);
                txvphone.setText(phone);
                txvprice.setText(String.valueOf(price));

        }

        //PARK 4
        if(location.matches("Al Majaz Splash Park"))
        {
            //change image
            img.setImageResource(R.drawable.almajazsplashpark);




                // display on the screen
                txvtime.setText(time);
                txvloc.setText(location);
                txvphone.setText(phone);
                txvprice.setText(String.valueOf(price));


        }



    }

                    /************************* ON CLICK *************************/
                    /**
                     * 1- map() using for txv location
                     * 2- ToBooking()
                     * 3- inccount() for increase number of ticket
                     * 4- deccount() for increase number of ticket
                     * 5- Back() for back to home
                     **/

      /** 1 **/
    public void map(View view)
    {

        String location = txvloc.getText().toString();

        // park 1
        if(location.matches("Sharjah Desert Park"))
        {
            Intent google_Map = new Intent(Intent.ACTION_VIEW);
            google_Map.setData(Uri.parse("geo:"+latitude+","+longitude+"?z=15"));
            startActivity(google_Map);
        }


        //park 2
        if(location.matches("Sharjah National Park"))
        {


            Intent google_Map = new Intent(Intent.ACTION_VIEW);
            google_Map.setData(Uri.parse("geo:"+latitude+","+longitude+"?z=15"));
            startActivity(google_Map);
        }


        //park 3
        if(location.matches("Al Montazah Parks"))
        {

            Intent google_Map = new Intent(Intent.ACTION_VIEW);
            google_Map.setData(Uri.parse("geo:"+latitude+","+longitude+"?z=18"));
            startActivity(google_Map);
        }


        //park 4
        if(location.matches("Al Majaz Splash Park"))
        {
            Intent google_Map = new Intent(Intent.ACTION_VIEW);
            google_Map.setData(Uri.parse("geo:"+latitude+","+longitude+"?z=15"));
            startActivity(google_Map);
        }

    }



    /** 2 **/
    public void ToBooking(View view)
    {

        String location = txvloc.getText().toString();

        // get data price and number of ticket
        int numOfTic = Integer.parseInt(edtcount.getText().toString());
        double Ticprice = Double.parseDouble(txvprice.getText().toString());
        String username = userName.getText().toString();


        //calculation
        double subtotal = Ticprice * numOfTic;
        double caltax = (subtotal * tax)/100;
        double totalwithtax = subtotal + caltax;
        double calDis = (subtotal*discount/100);
        double total = totalwithtax - calDis;

        //receive id member from home
        Intent intent1 = getIntent();
        int id = intent1.getIntExtra("id",0);


        //send
        Intent intent = new Intent(Booking.this,ConfirmPrice.class);
        intent.putExtra("ParkNum",park_num);
        intent.putExtra("UserName",username);
        intent.putExtra("PriceOfTic",Ticprice);
        intent.putExtra("NumOfTic",numOfTic);
        intent.putExtra("Discount",discount);
        intent.putExtra("Tax",tax);
        intent.putExtra("Total",total);
        intent.putExtra("id",id);
        intent.putExtra("Location",location);
        startActivity(intent);


    }

    /** 3 **/
    public void inccount(View view)
    {
        int count = Integer.parseInt(edtcount.getText().toString());

        count = count +1;

        edtcount.setText(String.valueOf(count));



    }

    /** 4 **/
    public void deccount(View view)
    {

        int count = Integer.parseInt(edtcount.getText().toString());


        if(count > 1)
        {
            count = count -1;

            edtcount.setText(String.valueOf(count));
        }
        else
        {

            Toast.makeText(this, "should be more than one ", Toast.LENGTH_SHORT).show();
        }



    }


    /** 5 **/
    public void Back(View view)
    {
        //receive id member from home
        Intent intent1 = getIntent();
        int id = intent1.getIntExtra("id",0);

        //send
        String username = userName.getText().toString();
        Intent intent = new Intent(Booking.this,Home.class);
        intent.putExtra("UserName",username);
        intent.putExtra("id",id);
        startActivity(intent);
    }




}