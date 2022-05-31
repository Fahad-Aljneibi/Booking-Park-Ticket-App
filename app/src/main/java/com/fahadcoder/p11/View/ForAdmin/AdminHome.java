package com.fahadcoder.p11.View.ForAdmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fahadcoder.p11.DAO.AdminBooking;
import com.fahadcoder.p11.DAO.AdminMember;
import com.fahadcoder.p11.View.Login;
import com.fahadcoder.p11.R;

public class AdminHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
    }


                    /*********************** ON CLICK ***********************/
                    /**
                     * member
                     * park
                     * booking
                     * exitApp
                     **/

    public void member(View view)
    {
        Intent intent = new Intent(AdminHome.this, AdminMember.class);
        startActivity(intent);
    }



    public void booking(View view) {
        Intent intent = new Intent(AdminHome.this, AdminBooking.class);
        startActivity(intent);
    }

    public void exitApp(View view)
    {
        Intent intent = new Intent(this, Login.class);
        intent.putExtra("LOGOUT","logout");
        startActivity(intent);
    }
}