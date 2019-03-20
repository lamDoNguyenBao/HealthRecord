package com.example.lamdonguyenbao.tablayout.fragment;

import android.content.Context;
import android.net.Uri;
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


public class InforFragment extends Fragment {
    View v;
    private User user;
    TextView tv_name,tv_phone,tv_weight,tv_height,tv_birthday,tv_blood_pressure,tv_address,tv_blood_group;
    ImageView iv_avatar;

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
        initView();
        bindData(user);
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
        return new User("Ngô Hữu Toàn","12-02-1997","Phú Đô, Nam Từ Liêm, Hà Nội","B-","0972322864","https://avatars0.githubusercontent.com/u/1?v=4",62,175,120);
    }
}
