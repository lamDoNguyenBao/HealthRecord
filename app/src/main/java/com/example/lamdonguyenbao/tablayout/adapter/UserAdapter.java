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
import com.example.lamdonguyenbao.tablayout.model.History;
import com.example.lamdonguyenbao.tablayout.model.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserItemViewHolder> {
    private List<History> history;
    private Context context;
    private OnItemClickListener mListener;

    public UserAdapter(List<History> history, Context c) {
        this.history = history;
        this.context = c;
    }


    @Override
    public int getItemCount() {
        return history.size();
    }

    @Override
    public UserItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView= inflater.inflate(R.layout.item_user,parent,false);
        return new UserItemViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(UserItemViewHolder holder,final int position) {
        History historyItem = history.get(position);
        holder.ivAvatar.setImageResource(R.drawable.hospital);
        holder.tvLoginName.setText(historyItem.getName());
        holder.tvId.setText(String.valueOf(historyItem.getTime()));
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
            }
        }
    }
}


