package com.fahadcoder.p11.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fahadcoder.p11.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

                                /*************** ON CLICK *****************/
                                /**
                                 * TologinPage
                                 * ToSignupPage
                                 **/

    public void TologinPage(View view)
    {

        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
    }

    public void ToSignupPage(View view)
    {
        Intent intent = new Intent(MainActivity.this, SingUp.class);
        startActivity(intent);
    }


}