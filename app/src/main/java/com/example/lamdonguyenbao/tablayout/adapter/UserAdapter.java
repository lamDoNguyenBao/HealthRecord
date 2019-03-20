package com.example.lamdonguyenbao.tablayout.adapter;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lamdonguyenbao.tablayout.R;
import com.example.lamdonguyenbao.tablayout.fragment.HistoryFragment;
import com.example.lamdonguyenbao.tablayout.model.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserItemViewHolder> {
    private List<User> users;
    private Context context;
    private Dialog myDialog;
    private static final int REQUEST_CALL = 1;

    public UserAdapter(List<User> users, Context c) {
        this.users = users;
        this.context = c;
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public UserItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        final UserItemViewHolder vHolder = new UserItemViewHolder(itemView);
        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.dialog_history);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final TextView dialog_name_tv = (TextView) myDialog.findViewById(R.id.dialog_name_id);
        final TextView dialog_phone_tv = (TextView) myDialog.findViewById(R.id.dialog_phone_id);
        final ImageView dialog_avatar_img = (ImageView) myDialog.findViewById(R.id.dialog_image_avatar);
        final Button dialog_button_call = (Button) myDialog.findViewById(R.id.dialog_button_call);

        dialog_button_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        vHolder.item_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_name_tv.setText(users.get(vHolder.getAdapterPosition()).getLogin());
                dialog_phone_tv.setText(users.get(vHolder.getAdapterPosition()).getId()+"");
                Picasso.with(context)
                        .load(users.get(vHolder.getAdapterPosition()).getAvatar_url())
                        .into(dialog_avatar_img);
                myDialog.show();
            }
        });
        return vHolder;
    }

    public void makePhoneCall(){
//        if(ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
//            ActivityCompat.requestPermissions(myDialog.getOwnerActivity(), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
//        else{
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:123456789"));
            context.startActivity(callIntent);
//        }
    }


    @Override
    public void onBindViewHolder(UserItemViewHolder holder, int position) {
        User u = users.get(position);
        Picasso.with(context)
                .load(u.getAvatar_url())
                .into(holder.ivAvatar);
        holder.tvLoginName.setText(u.getLogin());
        holder.tvId.setText(String.valueOf(u.getId()));
    }

    public static class UserItemViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout item_history;
        public TextView tvLoginName;
        public TextView tvId;
        public ImageView ivAvatar;

        public UserItemViewHolder(View itemView) {
            super(itemView);
            item_history = (RelativeLayout) itemView.findViewById(R.id.history_item_id);
            tvLoginName = (TextView) itemView.findViewById(R.id.tv_login_name);
            tvId = (TextView) itemView.findViewById(R.id.tv_id);
            ivAvatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
        }
    }
}
