package com.example.renthouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // declare main menu components
    ImageView image;
    Button btn;
    TextView txt1;
    TextView txt2;
    Animation top,bottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setProgress(false);
        // remove actionBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // add tween animation
        top= AnimationUtils.loadAnimation(this,R.anim.topanimation);
        bottom= AnimationUtils.loadAnimation(this,R.anim.bottomanimation);

        image=(ImageView) findViewById(R.id.imageView);
        btn=(Button) findViewById(R.id.button);
        txt1=(TextView) findViewById(R.id.textView);
        txt2=(TextView) findViewById(R.id.textView2);

        //Tween animation
        image.startAnimation(top);
        btn.setAnimation(bottom);
        txt1.setAnimation(bottom);
        txt2.setAnimation(bottom);

        //Button action
      btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              ConnectionAsyncTask connectionAsyncTask = new ConnectionAsyncTask(MainActivity.this);
              connectionAsyncTask.execute("https://run.mocky.io/v3/b7e67327-8e8b-4ee1-a6c3-23f36cc105af");



          }

      });


    }

    public void Connected(boolean valid) {
        if (valid) {
            Intent intent = new Intent(MainActivity.this, Entry.class);
            startActivity(intent);
            finish();
        }
        else {
        showToast("Not connected,please try again later ");
        }
    }
    public void showToast(String Message){
        Toast toast =Toast.makeText(MainActivity.this, Message,Toast.LENGTH_SHORT);
        toast.show();
    }
    public void setProgress(boolean progress) {
        ProgressBar progressBar = (ProgressBar)
                findViewById(R.id.progressBar);
        if (progress) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }



}