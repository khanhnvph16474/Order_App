package com.example.order_app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter_Setting extends RecyclerView.Adapter<MyAdapter_Setting.MySettingViewHolder> {
    //    private Context mContext;
    private MainActivity_Setting mainActSettingContext;
    private List<Person> lsPerson;

    public class MySettingViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView name;
        TextView country;
        TextView dis;
        ImageButton btn_change;
        ImageButton btn_delete;
        public MySettingViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar= (ImageView) itemView.findViewById(R.id.avatar);
            name = (TextView) itemView.findViewById(R.id.name) ;
            country = (TextView) itemView.findViewById(R.id.country);
            dis = itemView.findViewById(R.id.dis);
            btn_change = itemView.findViewById(R.id.btnChangeQuan);
            btn_delete = itemView.findViewById(R.id.btnDeleteQuan);
        }
    }

    public MyAdapter_Setting(MainActivity_Setting mainActSettingContext,List<Person> lsPerson){
        this.lsPerson = lsPerson;
        this.mainActSettingContext = mainActSettingContext;
    }

    @NonNull
    @Override
    public MySettingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_setting,parent,false);
        return new MySettingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MySettingViewHolder holder, final int position) {
        holder.avatar.setImageDrawable(mainActSettingContext.getResources().getDrawable(lsPerson.get(position).getAvatar()));
        holder.name.setText(lsPerson.get(position).getName());
        holder.country.setText(lsPerson.get(position).getCountry());
        holder.dis.setText(lsPerson.get(position).getDis());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainActSettingContext, menu_quan.class);
                Bundle bundle_menu = new Bundle();
                bundle_menu.putInt("key_id", lsPerson.get(position).getId());
                bundle_menu.putInt("key_ava", lsPerson.get(position).getAvatar());
                bundle_menu.putString("key_name", lsPerson.get(position).getName());
                bundle_menu.putString("key_country", lsPerson.get(position).getCountry());
                bundle_menu.putString("key_dis", lsPerson.get(position).getDis());
                intent.putExtras(bundle_menu);
                mainActSettingContext.startActivity(intent);
                Toast.makeText(mainActSettingContext,lsPerson.get(position).getName(),Toast.LENGTH_LONG).show();
            }
        });
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XacNhanXoa(lsPerson.get(position).getName(), lsPerson.get(position).getId());
            }
        });
        holder.btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActSettingContext.dialogUpdateQuan(
                        lsPerson.get(position).getId(),
                        lsPerson.get(position).getName(),
                        lsPerson.get(position).getCountry(),
                        lsPerson.get(position).getDis());
            }
        });
    }

    //tạo dialog để xác nhận xóa
    private void XacNhanXoa(String tenquan, final int id){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(mainActSettingContext);
        dialogXoa.setMessage("Bạn có muốn xóa món " + tenquan +" không?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mainActSettingContext.DeleteItemQuan(id);
            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogXoa.show();
    }

    @Override
    public int getItemCount() {
        return lsPerson.size();
    }


}
