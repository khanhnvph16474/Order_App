package com.example.order_app;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderedItemBill extends AppCompatActivity {
    //đường dẫn mysql
    ArrayList<item_bill> lsItemBill = new ArrayList<>();
    String urlGetData_Bill = "http://192.168.43.86/android_webservice/getdata_bill.php";
    String urlDeleteData_Bill = "http://192.168.43.86/android_webservice/delete.php";
    int total_bill = 0;
    private orderedItemAdapter billAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordered_item_bill);

        GetData_Bill(urlGetData_Bill);
        setUpRecyclerView();
    }

    private void setUpRecyclerView (){
        RecyclerView recyclerView = findViewById(R.id.recycleView_bill);
        billAdapter = new orderedItemAdapter(this,lsItemBill);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(billAdapter);
    }
    //code liên quan đến mysql
    public void GetData_Bill (String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
//                String a = String.valueOf(R.drawable.gxm);
//                Toast.makeText(MainActivity.this, a, Toast.LENGTH_LONG).show();
                lsItemBill.clear();
                total_bill = 0;
                for (int i =0; i < response.length(); i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        lsItemBill.add(new item_bill(
                                object.getInt("ID"),
                                object.getInt("HinhMon"),
                                object.getString("TenMon"),
                                object.getInt("GiaTien"),
                                object.getInt("SoLuong")
                        ));
                        total_bill = total_bill + object.getInt("GiaTien");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                TextView textView_price = findViewById(R.id.textView_price);
                textView_price.setText(total_bill + " VNĐ");
                billAdapter.notifyDataSetChanged();
//                Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_LONG).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(OrderedItemBill.this, error.toString(), Toast.LENGTH_LONG).show();

                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    public void DeleteItemBill(final int idItemBill){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlDeleteData_Bill,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")){
                            Toast.makeText(OrderedItemBill.this, "Xóa trong bảng bill thành công", Toast.LENGTH_LONG).show();
                            GetData_Bill(urlGetData_Bill);
                        }
                        else {
                            Toast.makeText(OrderedItemBill.this, "LỖi xóa", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(OrderedItemBill.this, "Xảy ra lỗi", Toast.LENGTH_LONG).show();

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idMon", String.valueOf(idItemBill));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
