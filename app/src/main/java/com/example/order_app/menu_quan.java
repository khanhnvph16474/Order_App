package com.example.order_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class menu_quan extends AppCompatActivity {
    FloatingActionButton back_pre;
    FloatingActionButton fab_search;
    FloatingActionButton fab_bill;
    TextView textView; // xài lại layout nên phải set Text view khác

    EditText edtTenMonAdd;
    EditText edtMotaAdd;
    EditText edtGiatienAdd;
    //RecyclerView
    RecyclerView recyclerView_menu;
    List<item_menu> lsItemMenu = new ArrayList<>();
    //sql
    String urlGetData_plt = "http://192.168.43.86/android_webservice/getdata_menu_plt.php";
    String urlGetData_cdq = "http://192.168.43.86/android_webservice/getdata_menu_cdq.php";
    String urlInsertData_plt = "http://192.168.43.86/android_webservice/insert_mon_plt.php";
    String urlInsertData_cdq = "http://192.168.43.86/android_webservice/insert_mon_cdq.php";

    int id_quan;

    //ArrayList<item_menu> lsItemMenu = new ArrayList<>();
    private  itemMenuAdapter itemMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_quan);

        //RecyclerView
        recyclerView_menu = findViewById(R.id.recycleView_menu);
        //int k = R.drawable.oc_134;
//        lsItemMenu.add(new item_menu(1, R.drawable.oc_134, "Cơm Tấm Phúc Lộc Thọ","31-33 Lê Văn Việt, P, Thủ Đức, Hồ Chí Minh", 2));
//        //Toast.makeText(this, k, Toast.LENGTH_LONG).show();
//        lsItemMenu.add(new item_menu(2, R.drawable.gxm, "Quán Cơm Kim Yến","63 Song Hành, Thảo Điền, Quận 2, Hồ Chí Minh", 2));
//        lsItemMenu.add(new item_menu(3, R.drawable.bun_bo_hue, "Cung Đình Quán","236 Lê Thánh Tôn, Phường Bến Thành, Quận 1, Hồ Chí Minh", 2));
//        lsItemMenu.add(new item_menu(4, R.drawable.bun_rieu_cua, "Bún riêu Gánh","4 Đường Phan Bội Châu, Phường Bến Thành, Quận 1, Hồ Chí Minh", 2));
//        lsItemMenu.add(new item_menu(5, R.drawable.com_tam, "Cơm Tấm Phúc Lộc Thọ","31-33 Lê Văn Việt, P, Thủ Đức, Hồ Chí Minh", 2));

        //
        fab_bill = findViewById(R.id.bill);
        back_pre = findViewById(R.id.fab_back);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        ImageView hinhQuan = findViewById(R.id.hinh_quan);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            int value_id = bundle.getInt("key_id", 0);
            int value_ava = bundle.getInt("key_ava", 0);
            String value_name = bundle.getString("key_name", "");
            String value_country = bundle.getString("key_country", "");
            String value_dis = bundle.getString("key_dis", "");

            collapsingToolbarLayout.setTitle(value_name);//thiết đặt title
            hinhQuan.setImageResource(value_ava);
            //code mysql
            if (value_id == 1){
                id_quan = value_id;
                GetData_Menu(urlGetData_plt);
            } else if (value_id == 3) {
                id_quan = value_id;
                GetData_Menu(urlGetData_cdq);
            }
            else {
                lsItemMenu.add(new item_menu(0, 2131165282, "Chưa có", "EMPTY", 68));
            }




        }
        setUpItemMenuRecyclerView();


    }

    public void InsertItemMon(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")){
                            Toast.makeText(menu_quan.this, "Cập nhật món ăn thành công", Toast.LENGTH_LONG).show();
                            if (id_quan == 1){
                                GetData_Menu(urlGetData_plt);
                            } else if (id_quan == 3) {
                                GetData_Menu(urlGetData_cdq);
                            }
                            else {
                                lsItemMenu.add(new item_menu(0, 2131165282, "Chưa có", "EMPTY", 68));
                            }
                        }
                        else {
                            Toast.makeText(menu_quan.this, "LỖi INSERT", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(menu_quan.this, "Xảy ra lỗi đối với sql", Toast.LENGTH_LONG).show();

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("tenMonItem", edtTenMonAdd.getText().toString().trim());
                params.put("moTaItem", edtMotaAdd.getText().toString().trim());
                params.put("giaTienItem", edtGiatienAdd.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }

    private void GetData_Menu(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                lsItemMenu.clear();
                for (int i =0; i < response.length(); i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        lsItemMenu.add(new item_menu(
                                object.getInt("ID"),
                                object.getInt("HinhMon"),
                                object.getString("TenMon"),
                                object.getString("MoTa"),
                                object.getInt("GiaTien")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                itemMenuAdapter.notifyDataSetChanged();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(menu_quan.this, error.toString(), Toast.LENGTH_LONG).show();

                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    //    cài đặt recyclerview
    private void setUpItemMenuRecyclerView (){

        itemMenuAdapter = new itemMenuAdapter(this,lsItemMenu);

        recyclerView_menu.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_menu.setAdapter(itemMenuAdapter);

    }

    //trở về trang trước
    public void back_home_method(View view) {
        super.onBackPressed();

    }
    //qua activity mới
    public void open_activityBill(View view) {
        Intent intent = new Intent(this, OrderedItemBill.class);
        this.startActivity(intent);
    }

    public void dialogInsertMon(View view) {
        AlertDialog.Builder dialogInsertMon = new AlertDialog.Builder(menu_quan.this);
        LayoutInflater inflater = menu_quan.this.getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_add, null);
        edtTenMonAdd = view.findViewById(R.id.editTenQuan);
        edtMotaAdd = view.findViewById(R.id.editDiaChi);
        edtGiatienAdd = view.findViewById(R.id.editKhoangCach);
        textView = view.findViewById(R.id.textView2);
        edtTenMonAdd.setHint("Nhập tên món");
        edtMotaAdd.setHint("Nhập mô tả món");
        edtGiatienAdd.setHint("Nhập giá tiền món ăn");
        textView.setText("Thêm thông tin món ăn");
        dialogInsertMon.setView(view)
                .setPositiveButton("Thêm món", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String textEdtTenMon = edtTenMonAdd.getText().toString().trim();
                        String textEdtMota = edtMotaAdd.getText().toString().trim();
                        String textEdtGiaTien = edtGiatienAdd.getText().toString().trim();
                        if(textEdtTenMon.matches("") || textEdtGiaTien.matches("")){
                            Toast.makeText(menu_quan.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            if(id_quan == 1) {
                                InsertItemMon(urlInsertData_plt);
                            }
                            if(id_quan == 3) {
                                InsertItemMon(urlInsertData_cdq);
                            }

                        }
                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        dialogInsertMon.show();
    }

}