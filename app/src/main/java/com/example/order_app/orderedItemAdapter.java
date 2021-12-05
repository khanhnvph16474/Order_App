package com.example.order_app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class orderedItemAdapter extends RecyclerView.Adapter<orderedItemAdapter.orderedItemViewHolder> {
    private OrderedItemBill orderedItemContext;
    private List<item_bill> lsItemBill;

    @NonNull
    @Override
    public orderedItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_bill,parent,false);
        return new orderedItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull orderedItemViewHolder holder, final int position) {
        holder.hinhmon.setImageDrawable(orderedItemContext.getResources().getDrawable(lsItemBill.get(position).getHinhmon()));
        holder.tenmon.setText(lsItemBill.get(position).getTenmon());
        holder.giatien.setText(String.valueOf(lsItemBill.get(position).getGiatien())+" VNĐ");
        holder.xoaItemBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XacNhanXoa(lsItemBill.get(position).getTenmon(), lsItemBill.get(position).getId());
//                Toast.makeText(orderedItemContext, lsItemBill.get(position).getTenmon(), Toast.LENGTH_LONG).show();
            }
        });

    }
    //tạo dialog để xác nhận xóa
    private void XacNhanXoa(String tenmon, final int id){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(orderedItemContext);
        dialogXoa.setMessage("Bạn có muốn xóa món " + tenmon +" không?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                orderedItemContext.DeleteItemBill(id);
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
        return lsItemBill.size();
    }

    public class orderedItemViewHolder extends RecyclerView.ViewHolder {
        ImageView hinhmon;
        TextView tenmon;
        TextView giatien;
        TextView soluong;
        ImageView ghichu;
        TextView ghichuText;
        ImageButton xoaItemBill;
        public orderedItemViewHolder(@NonNull View itemView) {
            super(itemView);
            hinhmon= itemView.findViewById(R.id.avatar_bill);
            tenmon = itemView.findViewById(R.id.tenmon_bill) ;
            giatien = itemView.findViewById(R.id.giatien_bill);
            ghichu = itemView.findViewById(R.id.ghichu_bill);
            ghichuText = itemView.findViewById(R.id.ghichutext_bill);
            xoaItemBill = itemView.findViewById(R.id.btnDeleteBill);
        }
    }
    public orderedItemAdapter(OrderedItemBill orderedItemContext,List<item_bill> lsItemBill){
        this.lsItemBill = lsItemBill;

        this.orderedItemContext = orderedItemContext;
    }
}

