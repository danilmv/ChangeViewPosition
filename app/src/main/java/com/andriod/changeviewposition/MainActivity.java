package com.andriod.changeviewposition;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private static final String TAG = "@MainActivity@";
    private float dX, dY;
    private View v1, v2;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        v1 = findViewById(R.id.text_view_movable);
        v2 = findViewById(R.id.text_view_movable2);

        v1.setOnTouchListener(this);
        v2.setOnTouchListener(this);

        if (savedInstanceState != null)
            restore(savedInstanceState);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                dX = v.getX() - event.getRawX();
                dY = v.getY() - event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:

                v.animate()
                        .x(event.getRawX() + dX)
                        .y(event.getRawY() + dY)
                        .setDuration(0)
                        .start();
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

//        outState.putInt("v1.width", v1.getLayoutParams().width);
//        outState.putInt("v1.height", v1.getLayoutParams().height);
        outState.putFloat("v1.x", v1.getX());
        outState.putFloat("v1.y", v1.getY());
        Log.d(TAG, String.format(Locale.getDefault(), "onSaveInstanceState() v1: x=%.2f y=%.2f", v1.getX(), v1.getY()));

//        outState.putInt("v2.width", v2.getLayoutParams().width);
//        outState.putInt("v2.height", v2.getLayoutParams().height);
        outState.putFloat("v2.x", v2.getX());
        outState.putFloat("v2.y", v2.getY());
        Log.d(TAG, String.format(Locale.getDefault(), "onSaveInstanceState() v2: x=%.2f y=%.2f", v2.getX(), v2.getY()));

//        int[] position = new int[2];
//        v1.getLocationOnScreen(position);
//        outState.putInt("v1.x", position[0]);
//        outState.putInt("v1.y", position[1]);


    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        restore(savedInstanceState);
    }

    private void restore(Bundle savedInstanceState) {
        float x = savedInstanceState.getFloat("v1.x");
        float y = savedInstanceState.getFloat("v1.y");
        Log.d(TAG, String.format(Locale.getDefault(), "restore() v1: x=%.2f y=%.2f", x, y));
        v1.setX(x);
        v1.setY(y);

        x = savedInstanceState.getFloat("v2.x");
        y = savedInstanceState.getFloat("v2.y");
        Log.d(TAG, String.format(Locale.getDefault(), "restore() v2: x=%.2f y=%.2f", x, y));

        v2.setX(x);
        v2.setY(y);
    }


}