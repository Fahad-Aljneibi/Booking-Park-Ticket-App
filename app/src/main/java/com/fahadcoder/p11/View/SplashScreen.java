package com.fahadcoder.p11.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.fahadcoder.p11.model.Park;
import com.fahadcoder.p11.R;
import com.fahadcoder.p11.model.Member;

/*****
 * GOAL OF THIS PAGE:
 * INSERT DATA TO TABLES
 */
public class SplashScreen extends AppCompatActivity {

    //Reference for the UI elements
    TextView txv;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Get object from the layout
        txv = (TextView)findViewById(R.id.txv_welcome);
        animation = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        txv.setAnimation(animation);


                          /***************************** ANIMATION ************************/

        // to wait few time then going next page
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 6000  ); //Time for progressbar (Loading)
    }
}