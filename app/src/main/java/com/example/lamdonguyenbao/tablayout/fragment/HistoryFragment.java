package com.example.lamdonguyenbao.tablayout.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.lamdonguyenbao.tablayout.model.History;
import com.example.lamdonguyenbao.tablayout.model.User;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HistoryFragment extends Fragment implements UserAdapter.OnItemClickListener {
    View v;
    Dialog myDialog;
    List<History> history;
    TextView tv_history_date;
    TextView tv_hisotry_name;
    TextView tv_history_medicaltest;
    TextView tv_history_treatment;
    TextView tv_history_drug;
    TextView tv_history_reexamination;
    ImageView dialog_history_image_avatar;
    Button dialog_button_call;
    RecyclerView rvUsers;
    UserAdapter mAdpter;
    SharedPreferences sharedPreferences;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public ArrayList<History> createData(){
        ArrayList<History> history = new ArrayList<>();
        sharedPreferences = getActivity().getSharedPreferences("mypre", Context.MODE_PRIVATE);
        String arrayMedical = sharedPreferences.getString("medicalHistory","");
        try {
            JSONArray medicalHistory = new JSONArray(arrayMedical);
            for(int i =0; i< medicalHistory.length();i++){
                JSONObject exam = medicalHistory.getJSONObject(i);
                String name = exam.getString("name");
                String treatment = exam.getString("treatment");
                String medicaltest = exam.getString("medicaltest");
                String drug = exam.getString("drug");
                JSONObject time = exam.getJSONObject("time");
                JSONObject retime = exam.getJSONObject("reexamination");
                Date examDate = convertTimestamptoDate(time.getInt("_seconds"));
                Date reExamDate = convertTimestamptoDate(retime.getInt("_seconds"));
                history.add(new History(name,treatment,medicaltest,drug,examDate.toString(),reExamDate.toString()));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return history;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_history, container, false);
        rvUsers = (RecyclerView) v.findViewById(R.id.rv_users);
        rvUsers.setLayoutManager(new LinearLayoutManager(getContext()));
        history = createData();
        mAdpter = new UserAdapter(history, getContext());
        mAdpter.setOnItemClickListener(this);
        rvUsers.setAdapter(mAdpter);


        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.dialog_hospital);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        tv_history_date = (TextView) myDialog.findViewById(R.id.tv_history_date);
        tv_hisotry_name = (TextView) myDialog.findViewById(R.id.tv_hisotry_name);
        tv_history_medicaltest = (TextView) myDialog.findViewById(R.id.tv_history_medicaltest);
        tv_history_treatment = (TextView) myDialog.findViewById(R.id.tv_history_treatment);
        tv_history_drug = (TextView) myDialog.findViewById(R.id.tv_history_drug);
        tv_history_reexamination = (TextView) myDialog.findViewById(R.id.tv_history_reexamination);
        dialog_history_image_avatar = (ImageView) myDialog.findViewById(R.id.dialog_history_image_avatar);
        return v;
    }

    @Override
    public void onItemClick(int position) {
        tv_history_date.setText(history.get(position).getTime()+"");
        tv_hisotry_name.setText(history.get(position).getName()+"");
        tv_history_medicaltest.setText(history.get(position).getMedicaltest()+"");
        tv_history_treatment.setText(history.get(position).getTreatment()+"");
        tv_history_drug.setText(history.get(position).getDrug()+"");
        tv_history_reexamination.setText(history.get(position).getReexamination()+"");

        dialog_history_image_avatar.setImageResource(R.drawable.hospital);
        myDialog.show();
    }
    public Date convertTimestamptoDate(int time){
        Timestamp stamp = new Timestamp(time);
        Date date = new Date(stamp.getTime());
        return date;
    }
}
