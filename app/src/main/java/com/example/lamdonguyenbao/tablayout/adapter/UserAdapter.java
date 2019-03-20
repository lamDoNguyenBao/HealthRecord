package com.example.lamdonguyenbao.tablayout.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lamdonguyenbao.tablayout.R;
import com.example.lamdonguyenbao.tablayout.model.Doctor;
import com.example.lamdonguyenbao.tablayout.model.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserItemViewHolder> {
    private List<Doctor> doctors;
    private Context context;
    private OnItemClickListener mListener;

    public UserAdapter(List<Doctor> doctors, Context c) {
        this.doctors = doctors;
        this.context = c;
    }


    @Override
    public int getItemCount() {
        return doctors.size();
    }

    @Override
    public UserItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView= inflater.inflate(R.layout.item_user,parent,false);
        return new UserItemViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(UserItemViewHolder holder,final int position) {
        Doctor doctor = doctors.get(position);
        System.out.println(doctor.getAvatar_url());
        Picasso.with(context)
                .load(doctor.getAvatar_url())
                .into(holder.ivAvatar);
        holder.tvLoginName.setText(doctor.getName());
        holder.tvId.setText(String.valueOf(doctor.getPhone()));
    }

    public void setOnItemClickListener(@NonNull OnItemClickListener listener){
        this.mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class UserItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tvLoginName;
        public TextView tvId;
        public ImageView ivAvatar;

        public UserItemViewHolder(View itemView) {
            super(itemView);
            tvLoginName = (TextView) itemView.findViewById(R.id.tv_login_name);
            tvId = (TextView) itemView.findViewById(R.id.tv_id);
            ivAvatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if(mListener != null){
                mListener.onItemClick(getAdapterPosition());
                Log.d("check", "onLongClick: "+getAdapterPosition());
            }
        }
    }
}


