package com.example.lamdonguyenbao.tablayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLOutput;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;

    public static final String mypreference  = "mypre";
    public static final String token1 = "abcd";

    private TextView tvRegister;
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.edt_username);
        password = (EditText) findViewById(R.id.edt_password);

        tvRegister = (TextView) findViewById(R.id.tvRegister);

        sharedpreferences =  getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        if(sharedpreferences.contains(token1)){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }

    }

    public void onClick(View view) {
        if(!validate()){
            postRequest();
        }
    }

    public void postRequest(){

        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                OkHttpClient client = new OkHttpClient();

                RequestBody requestBody = new FormBody.Builder()
                        .add("username", username.getText().toString())
                        .add("password", password.getText().toString())
                        .build();

                Request request = new Request.Builder()
                        .url("https://hraapi.herokuapp.com/login")
                        .post(requestBody)
                        .build();
                try{
                    Response response = client.newCall(request).execute();

                    return  response.body().string();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                try {
                    JSONArray array = new JSONArray("["+o+"]");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        //tvRegister.setText(object.getString("success"));
                        String str = object.getString("success");
                        String token = object.getString("token");
                        if(str == "true"){
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString(token1,token);
                            editor.commit();
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        }.execute();
    }


    private boolean validate() {

        if(username.getText().toString().trim().length() <= 0){
            Toast.makeText(LoginActivity.this,"Please Enter User Name",Toast.LENGTH_SHORT).show();
            return true;
        }else  if(password.getText().toString().trim().length() <= 0){
            Toast.makeText(LoginActivity.this,"Please Enter Password",Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public void onClickRegister(View view) {
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
        LoginActivity.this.finish();
    }


}
