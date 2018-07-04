package com.example.shubham.aashra.session;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by shubham on 18-06-2018.
 */

public class session {
    private String phone_no;
    Context context;
    public static final String MY_PREF="MyPrefs";
    public session(Context context){
        this.context=context;
    }

    public void setphone_no( String phone_no) {
        this.phone_no = phone_no;
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("phone_no", phone_no);
        editor.apply();
    }

    public
    String getphone_no() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREF, Context.MODE_PRIVATE);
        phone_no = sharedPreferences.getString("phone_no", null);
        if (phone_no != null) {
            return phone_no;
        }
        return null;
    }
}
