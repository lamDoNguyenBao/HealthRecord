package com.example.lamdonguyenbao.tablayout.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lamdonguyenbao.tablayout.R;
import com.example.lamdonguyenbao.tablayout.adapter.UserAdapter;
import com.example.lamdonguyenbao.tablayout.model.Doctor;
import com.example.lamdonguyenbao.tablayout.model.User;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HistoryFragment extends Fragment implements UserAdapter.OnItemClickListener {
    View v;
    Dialog myDialog;
    List<Doctor> doctors;
    TextView dialog_name_tv;
    TextView dialog_phone_tv;
    ImageView dialog_avatar_img;
    Button dialog_button_call;
    RecyclerView rvUsers;
    UserAdapter mAdpter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public ArrayList<Doctor> createData(){
        ArrayList<Doctor> doctor = new ArrayList<>();
        for(int i=0; i<18; i++) {
            doctor.add(new Doctor("mojombo", "0123456789", "https://avatars0.githubusercontent.com/u/1?v=4"));
        }
        return doctor;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_history, container, false);
        rvUsers = (RecyclerView) v.findViewById(R.id.rv_users);
        rvUsers.setLayoutManager(new LinearLayoutManager(getContext()));
        doctors = createData();
        mAdpter = new UserAdapter(doctors, getContext());
        mAdpter.setOnItemClickListener(this);
        rvUsers.setAdapter(mAdpter);


        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.dialog_history);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_name_tv = (TextView) myDialog.findViewById(R.id.dialog_name_id);
        dialog_phone_tv = (TextView) myDialog.findViewById(R.id.dialog_phone_id);
        dialog_avatar_img = (ImageView) myDialog.findViewById(R.id.dialog_image_avatar);
        dialog_button_call = (Button) myDialog.findViewById(R.id.dialog_button_call);
        dialog_button_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+ dialog_phone_tv.getText()));
                startActivity(callIntent);
            }
        });
        return v;
    }

    @Override
    public void onItemClick(int position) {
            dialog_name_tv.setText(doctors.get(position).getName());
            dialog_phone_tv.setText(doctors.get(position).getPhone()+"");
            Picasso.with(getContext())
                    .load(doctors.get(position).getAvatar_url())
                    .into(dialog_avatar_img);
            myDialog.show();
    }
}
