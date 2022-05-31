package com.fahadcoder.p11.View.ForMembers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fahadcoder.p11.R;

public class Congratulation extends AppCompatActivity {

    //Reference for the UI elements
    TextView userName,txvbknum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulation);

        userName = (TextView)findViewById(R.id.txv_username);
        txvbknum = (TextView)findViewById(R.id.txv_bknum);

        //receive data from confrim
        Intent intent = getIntent();
        String username = intent.getStringExtra("UserName");
        int pknum = intent.getIntExtra("ParkNum1",0);
        int id = intent.getIntExtra("id",0);
        long bknum = intent.getLongExtra("BookingNum",0);

        userName.setText(username);
        txvbknum.setText(String.valueOf(bknum));


    }


                                /************** ON CLICK *************/
                                /**
                                 * BackToHome
                                 **/

    public void BackToHome(View view)
    {
        Intent intent1 = getIntent();
        int id = intent1.getIntExtra("id",0);
        String username = userName.getText().toString();
        Intent intent = new Intent(Congratulation.this,Home.class);
        intent.putExtra("UserName",username);
        intent.putExtra("id",id);
        startActivity(intent);
    }
}