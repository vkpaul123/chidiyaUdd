package com.example.carljordan.chidiyaudd;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    Button back, back2;

    TextView tvWinner, tvReason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main2);
        ConstraintLayout cl = (ConstraintLayout) findViewById (R.id.cons);
        cl.setBackgroundColor (Color.WHITE);
        enableImmersiveMode(getWindow().getDecorView());

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.menutap);

        back = (Button) findViewById(R.id.back);
        back2 = (Button) findViewById(R.id.back2);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();

                Intent a = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(a);
                finish ();
            }
        });

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();

                Intent a = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(a);
                finish ();
            }
        });

        tvWinner = (TextView) findViewById(R.id.textView4);
        tvReason = (TextView) findViewById(R.id.textView10);

        Intent i1 = getIntent();
        String winner = i1.getStringExtra("winner");
        String reason = i1.getStringExtra("reason");

        if(winner != null)
            tvWinner.setText(winner);

        if(reason != null)
            tvReason.setText(reason);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent a = new Intent(this, MainActivity.class);
            startActivity(a);
            finish ();

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
