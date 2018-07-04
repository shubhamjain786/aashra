package com.example.shubham.aashra;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login_Screen extends AppCompatActivity {
    public static String URL_NAHAR_PUNCH = "http://122.15.29.230:8080/Ashra/login.php";
Button login;
EditText Email, password;
TextView register;
String Emailtext,passwordtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login__screen);
        Email=(EditText)findViewById(R.id.phone_no);
        password=(EditText)findViewById(R.id.password);
        register = (TextView)findViewById(R.id.register);
        login=(Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Emailtext=Email.getText().toString();
                passwordtext=password.getText().toString();
                if ((Emailtext.equals("")) ||(passwordtext.equals("")) ){
                    Email.setError("Mandatory");
                    password.setError("Mandatory");
                }else{
                    try {
                        loginvolley();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login_Screen.this,Register.class);
                startActivity(i);
            }
        });

    }

    private void loginvolley() throws JSONException {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL_NAHAR_PUNCH,
                new Response.Listener<String>() {
                    @Override
                    public
                    void onResponse(String response) {
                        Log.d("shubham", "onRes: "+response);
                        try {
                            JSONObject jobjet = new JSONObject(response);
                            String result1 = String.valueOf(jobjet.get("error"));


                            if (result1.equals("1")){
                                Intent i = new Intent(Login_Screen.this,MainActivity.class);
                                startActivity(i);
                            }else{
                                AlertDialog.Builder aa = new AlertDialog.Builder(Login_Screen.this);
                                aa.setMessage("please register First");
                                aa.show();

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
                params.put("Email",Emailtext);
                params.put("Password",passwordtext);
                return  params;

            }


        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}
