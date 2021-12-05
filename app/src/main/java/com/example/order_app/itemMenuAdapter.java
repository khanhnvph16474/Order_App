package com.example.order_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class itemMenuAdapter extends RecyclerView.Adapter<itemMenuAdapter.itemMenuViewHolder> {

    private Context itemContext;
    private List<item_menu> lsItemMenu;
    String urlInsert = "http://192.168.43.86/android_webservice/insert.php";
    private int position;

    public class itemMenuViewHolder extends RecyclerView.ViewHolder {
        ImageView hinhmon;
        TextView tenmon;
        TextView mota;
        TextView giatien;
        Button themmon;
        public itemMenuViewHolder(@NonNull View itemView) {
            super(itemView);
            hinhmon= itemView.findViewById(R.id.avatar_food);
            tenmon = itemView.findViewById(R.id.ten_mon) ;
            mota = itemView.findViewById(R.id.description);
            giatien = itemView.findViewById(R.id.price);
            themmon = itemView.findViewById(R.id.buttonAdd);
        }
    }

    public itemMenuAdapter(Context itemContext,List<item_menu> lsItemMenu){
        this.lsItemMenu = lsItemMenu;
        this.itemContext = itemContext;


    }

    @NonNull
    @Override
    public itemMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view_item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nested_scrollview,parent,false);
        return new itemMenuViewHolder(view_item);
    }

    @Override
    public void onBindViewHolder(@NonNull itemMenuViewHolder holder, final int position) {

        holder.hinhmon.setImageDrawable(itemContext.getResources().getDrawable(lsItemMenu.get(position).getHinhmon()));
        holder.tenmon.setText(lsItemMenu.get(position).getTenmon());
        holder.mota.setText(lsItemMenu.get(position).getMota());
        holder.giatien.setText(String.valueOf(lsItemMenu.get(position).getGiatien())+" VNĐ");
        holder.themmon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themMon(urlInsert, lsItemMenu.get(position).getHinhmon(), lsItemMenu.get(position).getTenmon(), lsItemMenu.get(position).getGiatien());

            }
        });

    }

    private void themMon(String url, final int hinhmon, final String tenmon, final int giatien){

        final String hinhmon_char = String.valueOf(hinhmon);
        final String giatien_char = String.valueOf(giatien);

        RequestQueue requestQueue = Volley.newRequestQueue(itemContext);//có thể bị lỗi
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")){
                            Toast.makeText(itemContext, "thêm thành công", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(itemContext, "xảy ra lỗi", Toast.LENGTH_LONG).show();
                        Log.d("AAA", "Lỗi\n" + error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("hinhmonItem", hinhmon_char.trim());
                params.put("tenmonItem", tenmon.trim());
                params.put("giatienItem", giatien_char.trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public int getItemCount() {
        return lsItemMenu.size();
    }


}
