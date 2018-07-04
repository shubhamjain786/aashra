package com.example.shubham.aashra;

import android.*;
import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.example.shubham.aashra.session.session;

public class Splash_screen extends AppCompatActivity {
    TelephonyManager tm;
    session ss = new session(this);
    private int IMEI_PERMISSION = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if ((ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE) ==
                PackageManager.PERMISSION_GRANTED) && (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) && (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) && (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.INTERNET)
                == PackageManager.PERMISSION_GRANTED) && (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_NETWORK_STATE)
                == PackageManager.PERMISSION_GRANTED)&& (ActivityCompat.checkSelfPermission(this, Manifest.permission.WAKE_LOCK)
                == PackageManager.PERMISSION_GRANTED)) {

            String phone_no = tm.getLine1Number();
            Log.d("phone", "onCreate: "+phone_no);
            ss.setphone_no(phone_no);

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    Intent i =new Intent(Splash_screen.this,Login_Screen.class);
                    startActivity(i);
                }
            }, 5000);
        }else{
            requestPhontatePermission();
        }
    }
    private void requestPhontatePermission() {
        if ((ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_PHONE_STATE))
                && (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE))
                && (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE))
                && (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.INTERNET))
                && (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_NETWORK_STATE))
                && (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WAKE_LOCK)))
        {
            new AlertDialog.Builder(this)
                    .setTitle("Permission Needed")
                    .setMessage("Permission is need to run this app")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(Splash_screen.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.ACCESS_NETWORK_STATE, android.Manifest.permission.READ_PHONE_STATE, android.Manifest.permission.INTERNET, Manifest.permission.WAKE_LOCK}, IMEI_PERMISSION);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .create().show();

        } else {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.ACCESS_NETWORK_STATE, android.Manifest.permission.READ_PHONE_STATE, android.Manifest.permission.INTERNET, Manifest.permission.WAKE_LOCK}, IMEI_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        /////////////////Permission Assign///////////////////
        if (requestCode == IMEI_PERMISSION) {

            if ((ActivityCompat.checkSelfPermission(Splash_screen.this,
                    android.Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)
                    && (ActivityCompat.checkSelfPermission(Splash_screen.this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                    && (ActivityCompat.checkSelfPermission(Splash_screen.this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                    && (ActivityCompat.checkSelfPermission(Splash_screen.this,
                    android.Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED)
                    && (ActivityCompat.checkSelfPermission(Splash_screen.this,
                    Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED)
                    && (ActivityCompat.checkSelfPermission(Splash_screen.this,
                    Manifest.permission.WAKE_LOCK) == PackageManager.PERMISSION_GRANTED)
                    )
            {
                String phone_no = tm.getLine1Number();
                Log.d("phone", "onCreate: "+phone_no);
                ss.setphone_no(phone_no);


                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        Intent i =new Intent(Splash_screen.this,Login_Screen.class);
                        startActivity(i);
                    }
                }, 5000);

            }
            else{
                finish();
            }
        }
        else{
            finish();
        }
        /////////////////Permission Assign Finish///////////////////
    }
}
