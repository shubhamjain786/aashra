package com.example.shubham.aashra;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaCas;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shubham.aashra.session.session;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    public static String URL_NAHAR_PUNCH = "http://122.15.29.230:8080/Ashra/registeraApi.php";
    session session = new session(this);
EditText Email,name,phone,password,confirmpassword;
Button register;
String textemail,textname,textphone,textpassword,textconfirmpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        Email=(EditText)findViewById(R.id.email);
        name=(EditText)findViewById(R.id.name);
        phone=(EditText)findViewById(R.id.phone_no);
        password=(EditText)findViewById(R.id.password);
        confirmpassword=(EditText)findViewById(R.id.confirmpassword);
        register=(Button)findViewById(R.id.register);
        phone.setText(session.getphone_no());
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             textemail=Email.getText().toString();
             textname=name.getText().toString();
                textphone=phone.getText().toString();
                textpassword=password.getText().toString();
                Log.d("pass", "onClick: "+textpassword);
                textconfirmpassword=confirmpassword.getText().toString();
                Log.d("pass", "onClick: "+textconfirmpassword);
                if (textemail.equals("") || textpassword.equals("") || textconfirmpassword.equals("")){
                    Email.setError("Mandatory");
                    password.setError("Mandatory");
                    password.setError("Mandatory");
                }else if (!textpassword.equals(textconfirmpassword)){
                    confirmpassword.setError("password Does not match please check again");
                }else{
                    try {
                        registervolley();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    private void registervolley() throws JSONException {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL_NAHAR_PUNCH,
                new Response.Listener<String>() {
                    @Override
                    public
                    void onResponse(String response) {
                        Log.d("Arvind", "onResponse2: "+response);
                        try {
                            JSONObject jobjet = new JSONObject(response);
                            String result1 = String.valueOf(jobjet.get("error"));


                            if (result1.equals("1")){
                                Intent i = new Intent(Register.this,MainActivity.class);
                                startActivity(i);
                            }else{
                                AlertDialog alertDialog = new AlertDialog.Builder(
                                        Register.this).create();

                                alertDialog.setTitle("Alert Dialog");

                                alertDialog.setMessage("");




                                // Showing Alert Message
                                alertDialog.show();
                            }


                        } catch (JSONException e) {

                            e.printStackTrace();

                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public
            void onErrorResponse(VolleyError error) {
                Log.d("shubham", "onResponse3: "+error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("User_Name",textname);
                params.put("Email",textemail);
                params.put("Phone_Number",textphone);
                params.put("Password",textpassword);
                params.put("Privacy","1");

                return  params;

            }


        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
