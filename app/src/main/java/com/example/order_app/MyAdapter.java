package com.example.order_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {
    private Context mContext;
    private List<Person> lsPerson;
    private List<Person> lsPersonFull;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView name;
        TextView country;
        TextView dis;

        public  MyViewHolder(View view){
            super(view);
            avatar= (ImageView) view.findViewById(R.id.avatar);
            name = (TextView) view.findViewById(R.id.name) ;
            country = (TextView) view.findViewById(R.id.country);
            dis = view.findViewById(R.id.dis);

        }

    }

    public MyAdapter(Context mContext,List<Person> lsPerson){
        this.lsPerson = lsPerson;
        this.lsPersonFull = lsPerson;
        this.mContext = mContext;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        final Person person = lsPersonFull.get(position);

        holder.avatar.setImageDrawable(mContext.getResources().getDrawable(lsPerson.get(position).getAvatar()));
        holder.name.setText(lsPerson.get(position).getName());
        holder.country.setText(lsPerson.get(position).getCountry());
        holder.dis.setText(lsPerson.get(position).getDis());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, menu_quan.class);
                Bundle bundle_menu = new Bundle();
                bundle_menu.putInt("key_id", lsPerson.get(position).getId());
                bundle_menu.putInt("key_ava", lsPerson.get(position).getAvatar());
                bundle_menu.putString("key_name", lsPerson.get(position).getName());
                bundle_menu.putString("key_country", lsPerson.get(position).getCountry());
                bundle_menu.putString("key_dis", lsPerson.get(position).getDis());
                intent.putExtras(bundle_menu);
                mContext.startActivity(intent);
                Toast.makeText(mContext,lsPerson.get(position).getName(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {

        return lsPersonFull.size();
    }


    @Override
    public Filter getFilter() {
        return personFilter;
    }
    private Filter personFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String charString = constraint.toString();
            if (charString.isEmpty()){

                lsPersonFull = lsPerson;

            } else {
                List<Person> filteredList = new ArrayList<>();
                String filterPattern = charString.toLowerCase().trim();

                for (Person item : lsPerson) {
                    if (item.getName().toLowerCase().trim().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
                lsPersonFull = filteredList;
            }
            FilterResults results = new FilterResults();
            results.values = lsPersonFull;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            lsPersonFull = (ArrayList<Person>) results.values;
            notifyDataSetChanged();
        }
    };


}

