package com.example.carljordan.chidiyaudd;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_splash_screen);
        ConstraintLayout cl = (ConstraintLayout) findViewById (R.id.cons);
        cl.setBackgroundColor (Color.WHITE);
        enableImmersiveMode(getWindow().getDecorView());
        Thread timer=new Thread ()
        {
            public void run()
            {
                try {
                    sleep(4000);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace ();
                }finally
                {
                    Intent intent=new Intent (getApplicationContext (),MainActivity.class);
                    startActivity (intent);
                    finish();
                }
            }
        };
        timer.start();


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // do something on back.
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }



    public static void enableImmersiveMode(final View decorView) {
        decorView.setSystemUiVisibility(setSystemUiVisibility());
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                    decorView.setSystemUiVisibility(setSystemUiVisibility());
                }
            }
        });
    }
    public static int setSystemUiVisibility() {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
    }

}
