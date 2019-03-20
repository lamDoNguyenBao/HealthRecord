package com.example.lamdonguyenbao.tablayout.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lamdonguyenbao.tablayout.R;
import com.example.lamdonguyenbao.tablayout.model.Doctor;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.HospitalItemViewHolder> {
    private List<Doctor> doctors;
    private Context context;
    private HospitalAdapter.OnItemClickListener mListener;

    public HospitalAdapter(List<Doctor> doctors, Context context) {
        this.doctors = doctors;
        this.context = context;
    }

    @Override
    public HospitalItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView= inflater.inflate(R.layout.item_doctor,parent,false);
        return new HospitalAdapter.HospitalItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HospitalItemViewHolder holder, int position) {
        Doctor doctor = doctors.get(position);
        Picasso.with(context)
                .load(doctor.getAvatar_url())
                .into(holder.iv_doctor_avatar);
        holder.tv_doctor_name.setText(doctor.getName());
        holder.tv_doctor_phone.setText(String.valueOf(doctor.getPhone()));
    }
    public void setOnItemClickListener(@NonNull HospitalAdapter.OnItemClickListener listener){
        this.mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    public class HospitalItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tv_doctor_name;
        public TextView tv_doctor_phone;
        public ImageView iv_doctor_avatar;

        public HospitalItemViewHolder(View itemView) {
            super(itemView);
            tv_doctor_name = (TextView) itemView.findViewById(R.id.tv_doctor_name);
            tv_doctor_phone = (TextView) itemView.findViewById(R.id.tv_doctor_phone);
            iv_doctor_avatar = (ImageView) itemView.findViewById(R.id.iv_doctor_avatar);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if(mListener != null){
                mListener.onItemClick(getAdapterPosition());
            }
        }
    }
}
