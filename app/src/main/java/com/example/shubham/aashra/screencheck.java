package com.example.shubham.aashra;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by shubham on 28-06-2018.
 */

public class screencheck extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"scrren",Toast.LENGTH_LONG).show();
    }
}
