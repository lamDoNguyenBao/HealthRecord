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
import com.example.lamdonguyenbao.tablayout.model.History;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public ArrayList<History> createData(){
        ArrayList<History> history = new ArrayList<>();
        history.add(new History("Đau dạ dày","Sử dụng thuốc","Nội soi","gastropulghe, panacetamol","03-02-2019","03-06-2019"));
        history.add(new History("Đau đầu","Uống thuốc","Chuẩn đoán triệu chứng","pnaldo,genstrimano","20-1-2019","20-5-2019"));
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

        Picasso.with(getContext())
                .load("data:image/png;base64,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")
                .into(dialog_history_image_avatar);
        myDialog.show();
    }
}
