package com.example.movielibrary;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LifecycleActivity extends AppCompatActivity {
    public static final String TAG = "LifecycleActivity";

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");
    }

    @Override
 /*   protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState");
    }*/


    protected void onStart(){
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
 /*   protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState");
    }*/

    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume");
    }

    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
      /*  String aName = nameEditText.getText().toString();
        SharedPreferences.Editor editor = sP.edit();
        editor.putString(KEY_NAME, aName);
        editor.apply();*/

    }


    protected void onStop(){
        super.onStop();
        Log.d(TAG, "onStop");
    }



    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy");
    }

}
