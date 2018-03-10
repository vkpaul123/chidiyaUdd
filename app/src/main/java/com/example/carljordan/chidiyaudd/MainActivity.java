package com.example.carljordan.chidiyaudd;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button buttonExit, buttonSettings, buttonStart;
    MediaPlayer mpInGameMusic;
    String musicStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        ConstraintLayout cl = (ConstraintLayout) findViewById (R.id.cons);
        cl.setBackgroundColor (Color.WHITE);
        enableImmersiveMode(getWindow().getDecorView());

        buttonExit = (Button) findViewById(R.id.buttonExit);
        buttonSettings = (Button) findViewById(R.id.buttonSettings);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.menutap);
        final MediaPlayer mp2 = MediaPlayer.create(this, R.raw.gamestart);
        mpInGameMusic = MediaPlayer.create(this, R.raw.chidiyauddgamemusic);

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();

                finish();
                System.exit(0);
            }
        });

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();

                if(mpInGameMusic.isPlaying()) {
                    mpInGameMusic.pause();
                    SharedPreferences sharedpreferences = getSharedPreferences(getResources().getString(R.string.SharedPreferencesFileName), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString("musicEnable", "disabled");
                    editor.apply();
                    buttonSettings.setBackground(getResources().getDrawable(R.drawable.mute));
                } else {
                    mpInGameMusic.start();
                    SharedPreferences sharedpreferences = getSharedPreferences(getResources().getString(R.string.SharedPreferencesFileName), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString("musicEnable", "enabled");
                    editor.apply();
                    buttonSettings.setBackground(getResources().getDrawable(R.drawable.music));
                }
            }
        });

        buttonStart = (Button) findViewById(R.id.button6);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp2.start();

                Intent i = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(i);
                finish();

                if(mpInGameMusic.isPlaying())
                    mpInGameMusic.stop();
            }
        });

        toggleGameMenuMusic();
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

    public void toggleGameMenuMusic() {
//        Toast.makeText(getApplicationContext(), getResources().getString(R.string.SharedPreferencesFileName), Toast.LENGTH_SHORT).show();
        SharedPreferences sharedpreferences = getSharedPreferences(getResources().getString(R.string.SharedPreferencesFileName), Context.MODE_PRIVATE);
        musicStatus = sharedpreferences.getString("musicEnable", "");

        if(musicStatus.equals("")){
            SharedPreferences.Editor editor = sharedpreferences.edit();

            editor.putString("musicEnable", "enabled");
            editor.apply();
            mpInGameMusic.start();
        } else if(musicStatus.equals("enabled"))
            mpInGameMusic.start();
        else {
            buttonSettings.setBackground(getResources().getDrawable(R.drawable.mute));
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // do something on back.
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if(musicStatus.equals("enabled"))
            if(!hasFocus)
                mpInGameMusic.pause();
            else
                mpInGameMusic.start();
    }
}
