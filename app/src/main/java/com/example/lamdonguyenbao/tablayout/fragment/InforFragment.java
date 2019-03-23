package com.example.lamdonguyenbao.tablayout.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lamdonguyenbao.tablayout.R;
import com.example.lamdonguyenbao.tablayout.model.User;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class InforFragment extends Fragment {
    View v;
    private User user;
    TextView tv_name,tv_phone,tv_weight,tv_height,tv_birthday,tv_blood_pressure,tv_address,tv_blood_group;
    ImageView iv_avatar;
    SharedPreferences sharedpreferences;
    public static final String mypreference  = "mypre";
    public static final String USERDATA ="userData";
    public static final String MEDICALHISTORY ="medicalHistory";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_infor, container, false);
        user = getData();

        return v;
    }

    private void initView(){
        tv_name = (TextView) v.findViewById(R.id.tv_name);
        tv_phone = (TextView) v.findViewById(R.id.tv_phone);
        tv_weight = (TextView) v.findViewById(R.id.tv_weight);
        tv_height = (TextView) v.findViewById(R.id.tv_height);
        tv_birthday = (TextView) v.findViewById(R.id.tv_birthday);
        tv_blood_pressure = (TextView) v.findViewById(R.id.tv_blood_pressure);
        tv_address = (TextView) v.findViewById(R.id.tv_address);
        tv_blood_group = (TextView) v.findViewById(R.id.tv_blood_group);
        iv_avatar = (ImageView) v.findViewById(R.id.iv_avatar);
    }

    public void bindData(User user){
        tv_name.setText(user.getName());
        tv_phone.setText(user.getPhone());
        tv_weight.setText(user.getWeight()+"");
        tv_height.setText(user.getHeight()+"");
        tv_birthday.setText(user.getBirthday());
        tv_blood_pressure.setText(user.getBloodPressure()+"");
        tv_address.setText(user.getAddress());
        tv_blood_group.setText(user.getBloodGroup());
        Picasso.with(getContext())
                .load(user.getAvatar_url())
                .into(iv_avatar);
    }

    public User getData(){
        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                OkHttpClient client = new OkHttpClient.Builder()
                                                      .connectTimeout(100,TimeUnit.SECONDS)
                                                      .readTimeout(100,TimeUnit.SECONDS)
                                                      .build();
                sharedpreferences = getActivity().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                String token = sharedpreferences.getString("abcd","");
                Request rq = new  Request.Builder()
                                    .url("https://hraapi.herokuapp.com/user")
                                    .addHeader("Authorization", "Bearer "+token)
                                    .build();
                    Response res = client.newCall(rq).execute();
                    return res.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                try {
                    JSONObject jsonObject = new JSONObject(o.toString());
                    JSONObject userData = jsonObject.getJSONObject("userData");
                    JSONArray medicalHistor = jsonObject.getJSONArray("medicalHistory");
                    sharedpreferences = getActivity().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(USERDATA,userData.toString());
                    editor.putString(MEDICALHISTORY,medicalHistor.toString());
                    editor.commit();
                    initView();
                    JSONObject birthDay = userData.getJSONObject("birthday");
                    Timestamp stamp = new Timestamp(birthDay.getInt("_seconds"));
                    Date date = new Date(stamp.getTime());
                    bindData(new User(userData.getString("name"),date.toString(),userData.getString("address"), userData.getString("bloodgroup"),userData.getString("username"),"https://avatars0.githubusercontent.com/u/1?v=4",userData.getInt("weght"),userData.getInt("height"),userData.getInt("bloodpressure")));
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.execute();
        return new User("Ngô Hữu Toàn","12-02-1997","Phú Đô, Nam Từ Liêm, Hà Nội","B-","0972322864","https://avatars0.githubusercontent.com/u/1?v=4",62,175,120);
    }

}
