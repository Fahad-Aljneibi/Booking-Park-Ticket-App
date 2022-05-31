package com.fahadcoder.p11.View.ForMembers;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.fahadcoder.p11.model.Park;
import com.fahadcoder.p11.R;
import com.fahadcoder.p11.View.Login;
import com.fahadcoder.p11.model.Alter;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

public class Home extends AppCompatActivity {



    //Reference for the UI elements
    TextView userName;
    TextView txverror;
    //location
    TextView txvlocation1,txvlocation2,txvlocation3,txvlocation4;
    //time
    TextView txvtpone,txvtptwo,txvtpthree,txvtpfour;
    //txv_tp_two,txv_tp_three,txv_tp_four;
    //phone
    TextView txvphpone,txvphptwo,txvphpthree,txvphpfour;
    //txv_php_one,txv_php_two,txv_php_three,txv_php_four
    TextView txvd1,txvd2,txvd3,txvd4;
    Alter alert = new Alter();
    ImageView img;


    //Variable

    //park num
    private int p1,p2,p3,p4;
    //description
    private String des1,des2,des3,des4;

    //latitude and longitude
    private String la1,la2,la3,la4;
    private String lo1,lo2,lo3,lo4;

    //price of ticket
    private double t1,t2,t3,t4;


    //discoutn
    private int dis1,dis2,dis3,dis4;


    //tax
    private int tax1,tax2,tax3,tax4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //receive from login
        userName = (TextView)findViewById(R.id.txv_username);
        Intent intent = getIntent();
        String username = intent.getStringExtra("UserName");
        userName.setText(username);

        //txv time
        txvtpone = findViewById(R.id.txv_tp_one);
        txvtptwo = findViewById(R.id.txv_tp_two);
        txvtpthree = findViewById(R.id.txv_tp_three);
        txvtpfour = findViewById(R.id.txv_tp_four);

        //txv location
        txvlocation1 = (TextView)findViewById(R.id.txv_lp_one);
        txvlocation2 = (TextView)findViewById(R.id.txv_lp_two);
        txvlocation3 = (TextView)findViewById(R.id.txv_lp_three);
        txvlocation4 = (TextView)findViewById(R.id.txv_lp_four);

        //txv phone
        txvphpone = (TextView)findViewById(R.id.txv_php_one);
        txvphptwo = (TextView)findViewById(R.id.txv_php_two);
        txvphpthree = (TextView)findViewById(R.id.txv_php_three);
        txvphpfour = (TextView)findViewById(R.id.txv_php_four);


        //underline for location
        txvlocation1.setPaintFlags(txvlocation1.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        txvlocation2.setPaintFlags(txvlocation2.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        txvlocation3.setPaintFlags(txvlocation3.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        txvlocation4.setPaintFlags(txvlocation4.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);


        //txv description
        txvd1 = (TextView)findViewById(R.id.txvd1);
        txvd2 = (TextView)findViewById(R.id.txvd2);
        txvd3 = (TextView)findViewById(R.id.txvd3);
        txvd4 = (TextView)findViewById(R.id.txvd4);




        //underline for description
        txvd1.setPaintFlags(txvd1.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        txvd2.setPaintFlags(txvd2.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        txvd3.setPaintFlags(txvd3.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        txvd4.setPaintFlags(txvd4.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);



        img = (ImageView)findViewById(R.id.icon_user);
        //img = (ImageView)findViewById(R.id.icon_user);

        //txv error
        txverror = findViewById(R.id.txv_error);

        //json
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        final String url = "https://jsonkeeper.com/b/LZQZ ";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                this::handleResponse,
                this::handleError
        ); //end JsonArrayRequest

        requestQueue.add(jsonArrayRequest);

    }

    private void handleError(VolleyError error) {

        txverror.setText(error.toString());
    }


    private void handleResponse(JSONArray response) {
        Gson gson = new Gson();

        //parse the Json response using GSON.fromJson()
        Park park = null;
        for (int i = 0; i < response.length(); i++) {
            try {
                park = gson.fromJson(response.getJSONObject(i).toString(),
                        Park.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (i == 0) {
                //park id
                p1 = park.getPark_num();
                //time
                txvtpone.setText(park.getTime());
                //location
                txvlocation1.setText(park.getLocation_name());
                //phone number
                txvphpone.setText(park.getPhone());
                //description
                des1 = park.getDescription();
                //latitude and longitude
                la1= park.getLatitude();
                lo1 = park.getLongitude();
                //price of ticket
                t1=park.getPrice_of_ticket();
                //discount
                dis1 = park.getDiscount();
                //tax
                tax1 = park.getTax();
            } else if (i == 1) {
                //park id
                p2 = park.getPark_num();
                //time
                txvtptwo.setText(park.getTime());
                //location
                txvlocation2.setText(park.getLocation_name());
                //phone number
                txvphptwo.setText(park.getPhone());
                //description
                des2 = park.getDescription();
                //latitude and longitude
                la2= park.getLatitude();
                lo2 = park.getLongitude();
                //price of ticket
                t2=park.getPrice_of_ticket();
                //discount
                dis2 = park.getDiscount();
                //tax
                tax2 = park.getTax();
            } else if (i == 2) {
                //park id
                p3 = park.getPark_num();
                //time
                txvtpthree.setText(park.getTime());
                //location
                txvlocation3.setText(park.getLocation_name());
                //phone number
                txvphpthree.setText(park.getPhone());
                //description
                des3 = park.getDescription();
                //latitude and longitude
                la3= park.getLatitude();
                lo3 = park.getLongitude();
                //price of ticket
                t3=park.getPrice_of_ticket();
                //discount
                dis3 = park.getDiscount();
                //tax
                tax3 = park.getTax();
            } else if (i == 3) {
                //park id
                p4 = park.getPark_num();
                //time
                txvtpfour.setText(park.getTime());
                //location
                txvlocation4.setText(park.getLocation_name());
                //phone number
                txvphpfour.setText(park.getPhone());
                //description
                des4 = park.getDescription();
                //latitude and longitude
                la4= park.getLatitude();
                lo4 = park.getLongitude();
                //price of ticket
                t4=park.getPrice_of_ticket();
                //discount
                dis4 = park.getDiscount();
                //tax
                tax4 = park.getTax();
            } else {
                break;
            }


        }

    } //finish handleResponse
        //park = gson.fromJson(response.getJSONArray(1), Park.class);



    //}

    /***************** ON CLICK ******************/
                                    /**
                                     * CHECK CARD HAVE 3 ON CLICK :
                                     * booking()
                                     * description()
                                     * map()
                                     * ********
                                     * exitApp3
                                     **/


    //img
    public void imgB(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 1);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            img.setImageBitmap(imageBitmap);
/*            try {
                createImageFile();
                galleryAddPic();
            }catch (Exception e)
            {
                e.printStackTrace();
            }*/
/*            Intent intent = new Intent(Home.this,Booking.class);
            Uri i = data.getData();
            intent.putExtra("image",i);
            startActivity(intent);*/

        }
    }


    // for card 1
    public void booking1(View view)
    {
        //recive id member from login
        Intent intent1 = getIntent();
        int id = intent1.getIntExtra("id",0);

        //take from GUI home
        String username = userName.getText().toString();
        String location = txvlocation1.getText().toString();
        String time = txvtpone.getText().toString();
        String phone = txvphpone.getText().toString();

        //send username and location and id member
        Intent intent = new Intent(Home.this,Booking.class);
        intent.putExtra("ParkNum",p1);
        intent.putExtra("UserName",username);
        intent.putExtra("Location",location);
        intent.putExtra("Time",time);
        intent.putExtra("Phone",phone);
        intent.putExtra("Latitude",la1);
        intent.putExtra("Longitude",lo1);
        intent.putExtra("Price",t1);
        intent.putExtra("Discount",dis1);
        intent.putExtra("Tax",tax1);
        intent.putExtra("id",id);
        startActivity(intent);



    }




    public void description1(View view)
    {

        alert.sendMsg("Description",des1,Home.this);

    }

    public void map1(View view)
    {



        String latitude = la1;
        String longitude = lo1;

        if (latitude != null && longitude != null) {
            // display map
            Intent google_Map = new Intent(Intent.ACTION_VIEW);
            google_Map.setData(Uri.parse("geo:"+latitude+","+longitude+"?z=15"));
            startActivity(google_Map);

        } else
        {

            alert.sendMsg("Erorr","Don't have latitude and longitude",this);
        }



    }


    // for card 2

    public void booking2(View view)
    {
        //recive id member
        Intent intent1 = getIntent();
        int id = intent1.getIntExtra("id",0);

        String username = userName.getText().toString();
        String location = txvlocation2.getText().toString();
        String time = txvtptwo.getText().toString();
        String phone = txvphptwo.getText().toString();

        //send username and location and id member
        Intent intent = new Intent(Home.this,Booking.class);
        intent.putExtra("ParkNum",p2);
        intent.putExtra("UserName",username);
        intent.putExtra("Location",location);
        intent.putExtra("Time",time);
        intent.putExtra("Phone",phone);
        intent.putExtra("Latitude",la2);
        intent.putExtra("Longitude",lo2);
        intent.putExtra("Price",t2);
        intent.putExtra("Discount",dis2);
        intent.putExtra("Tax",tax2);
        intent.putExtra("id",id);
        startActivity(intent);



    }


    public void description2(View view)
    {

        alert.sendMsg("Description",des2+"321",Home.this);
    }

    public void map2(View view)
    {

//        //call the database helper class to get data of park
//        MyDBHelper myhelper = new MyDBHelper(this);
//        Park p  = myhelper.getDataParkByPkNum(2);
//
//
//        String latitude = p.getLatitude();
//        String longitude = p.getLongitude();

        String latitude = la2;
        String longitude = lo2;

        if (latitude != null && longitude != null) {
            // display map
            Intent google_Map = new Intent(Intent.ACTION_VIEW);
            google_Map.setData(Uri.parse("geo:"+latitude+","+longitude+"?z=15"));
            startActivity(google_Map);

        } else
        {

            alert.sendMsg("Erorr","Don't have latitude and longitude",this);
        }



    }


    // for card 3

    public void booking3(View view)
    {

        //recive id member
        Intent intent1 = getIntent();
        int id = intent1.getIntExtra("id",0);

        String username = userName.getText().toString();
        String location = txvlocation3.getText().toString();
        String time = txvtpthree.getText().toString();
        String phone = txvphpthree.getText().toString();

        //send username and location and id member
        Intent intent = new Intent(Home.this,Booking.class);
        intent.putExtra("ParkNum",p3);
        intent.putExtra("UserName",username);
        intent.putExtra("Location",location);
        intent.putExtra("Time",time);
        intent.putExtra("Phone",phone);
        intent.putExtra("Latitude",la3);
        intent.putExtra("Longitude",lo3);
        intent.putExtra("Price",t3);
        intent.putExtra("Discount",dis3);
        intent.putExtra("Tax",tax3);
        intent.putExtra("id",id);
        startActivity(intent);


    }


    public void description3(View view)
    {

        alert.sendMsg("Description",des3+"321",Home.this);
    }

    public void map3(View view)
    {

//        //call the database helper class to get data of park
//        MyDBHelper myhelper = new MyDBHelper(this);
//        Park p  = myhelper.getDataParkByPkNum(3);
//
//
//        String latitude = p.getLatitude();
//        String longitude = p.getLongitude();

        String latitude = la3;
        String longitude = lo3;
        if (latitude != null && longitude != null) {
            // display map
            Intent google_Map = new Intent(Intent.ACTION_VIEW);
            google_Map.setData(Uri.parse("geo:"+latitude+","+longitude+"?z=18"));
            startActivity(google_Map);

        } else
        {

            alert.sendMsg("Erorr","Don't have latitude and longitude",this);
        }



    }

    // for card 4
    public void booking4(View view)
    {

        //recive id member
        Intent intent1 = getIntent();
        int id = intent1.getIntExtra("id",0);


        String username = userName.getText().toString();
        String location = txvlocation4.getText().toString();
        String time = txvtpfour.getText().toString();
        String phone = txvphpfour.getText().toString();

        //send username and location and id member
        Intent intent = new Intent(Home.this,Booking.class);
        intent.putExtra("ParkNum",p4);
        intent.putExtra("UserName",username);
        intent.putExtra("Location",location);
        intent.putExtra("Time",time);
        intent.putExtra("Phone",phone);
        intent.putExtra("Latitude",la4);
        intent.putExtra("Longitude",lo4);
        intent.putExtra("Price",t4);
        intent.putExtra("Discount",dis4);
        intent.putExtra("Tax",tax4);
        intent.putExtra("id",id);
        startActivity(intent);

    }


    public void description4(View view)
    {

        alert.sendMsg("Description",des4+"321",Home.this);
    }

    public void map4(View view)
    {

//        //call the database helper class to get data of park
//        MyDBHelper myhelper = new MyDBHelper(this);
//        Park p  = myhelper.getDataParkByPkNum(4);
//
//
//        String latitude = p.getLatitude();
//        String longitude = p.getLongitude();

        String latitude = la4;
        String longitude = lo4;

        if (latitude != null && longitude != null) {
            // display map
            Intent google_Map = new Intent(Intent.ACTION_VIEW);
            google_Map.setData(Uri.parse("geo:"+latitude+","+longitude+"?z=15"));
            startActivity(google_Map);

        } else
        {

            alert.sendMsg("Erorr","Don't have latitude and longitude",this);
        }



    }

    public void exitApp3(View view)
    {
        Intent intent = new Intent(Home.this, Login.class);
        intent.putExtra("LOGOUT","logout");
        startActivity(intent);
        finish();
    }


}