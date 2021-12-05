package com.example.order_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity_Setting extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    ArrayList<Person> lsPerson = new ArrayList<>();
    String urlGetData = "http://192.168.43.86/android_webservice/getdata.php";
    String urlDeleteData_Quan = "http://192.168.43.86/android_webservice/delete_quan.php";
    String urlUpdateData_Quan = "http://192.168.43.86/android_webservice/update_quan.php";
    String urlInsertData_Quan = "http://192.168.43.86/android_webservice/insert_quan.php";

    ImageButton btn_change;
    ImageButton btn_delete;
    EditText edtTenQuan, edtTenQuanAdd;
    EditText edtKhoangCach, edtKhoangCachAdd;
    EditText edtDiaChi, edtDiaChiAdd;
    int idItemQuan;


    private  MyAdapter_Setting adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_setting);

        //code mysql
        GetData(urlGetData);

        toolbar = findViewById(R.id.toolbar_drawer);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setOverflowIcon(getDrawable(R.drawable.ic_more_vert_black_24dp));
        setSupportActionBar(toolbar);
        //drawer menu setting
        drawerLayout = findViewById(R.id.drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        //
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        setUpRecyclerView();
    }

    private void setUpRecyclerView (){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        adapter = new MyAdapter_Setting(this,lsPerson);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu_setting,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                Toast.makeText(this, "share button clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.add_item:
                dialogAddQuan();
                Toast.makeText(this, "add button clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.home_item:
                startActivity(new Intent(MainActivity_Setting.this, MainActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.search_rest:
                Toast.makeText(this, "search button clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Toast.makeText(this, "setting button clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.share:
                Toast.makeText(this, "share button clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.add_item:
                Toast.makeText(this, "add button clicked", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void GetData (String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                lsPerson.clear();
                for (int i =0; i < response.length(); i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        lsPerson.add(new Person(
                                object.getInt("ID"),
                                object.getInt("HinhQuan"),
                                object.getString("TenQuan"),
                                object.getString("DiaChi"),
                                object.getString("KhoangCach") + " Km"
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity_Setting.this, error.toString(), Toast.LENGTH_LONG).show();

                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    public void DeleteItemQuan(final int idItemQuan){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlDeleteData_Quan,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")){
                            Toast.makeText(MainActivity_Setting.this, "Xóa trong bảng quán thành công", Toast.LENGTH_LONG).show();
                            GetData(urlGetData);
                        }
                        else {
                            Toast.makeText(MainActivity_Setting.this, "Lỗi xóa", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity_Setting.this, "Xảy ra lỗi", Toast.LENGTH_LONG).show();

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idQuan", String.valueOf(idItemQuan));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void UpdateItemQuan(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")){
                            Toast.makeText(MainActivity_Setting.this, "Cập nhật quán thành công", Toast.LENGTH_LONG).show();
                            GetData(urlGetData);
                        }
                        else {
                            Toast.makeText(MainActivity_Setting.this, "Lỗi cập nhật", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity_Setting.this, "Xảy ra lỗi", Toast.LENGTH_LONG).show();

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idQuan", String.valueOf(idItemQuan));
                params.put("tenQuanItem", edtTenQuan.getText().toString().trim());
                params.put("diaChiItem", edtDiaChi.getText().toString().trim());
                params.put("khoangCachItem", edtKhoangCach.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void InsertItemQuan(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")){
                            Toast.makeText(MainActivity_Setting.this, "Cập nhật quán thành công", Toast.LENGTH_LONG).show();
                            GetData(urlGetData);
                        }
                        else {
                            Toast.makeText(MainActivity_Setting.this, "Lỗi thêm", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity_Setting.this, "Xảy ra lỗi đối với sql", Toast.LENGTH_LONG).show();

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("tenQuanItem", edtTenQuanAdd.getText().toString().trim());
                params.put("diaChiItem", edtDiaChiAdd.getText().toString().trim());
                params.put("khoangCachItem", edtKhoangCachAdd.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }

    public void dialogUpdateQuan(int idQuan, String tenQuan, String diaChi, String khoangCach){
        AlertDialog.Builder dialogUpdateQuan = new AlertDialog.Builder(MainActivity_Setting.this);
        LayoutInflater inflater = MainActivity_Setting.this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_change, null);
        edtTenQuan = view.findViewById(R.id.editTenQuan);
        edtDiaChi = view.findViewById(R.id.editDiaChi);
        edtKhoangCach = view.findViewById(R.id.editKhoangCach);
        edtTenQuan.setText(tenQuan);
        edtDiaChi.setText(diaChi);
        edtKhoangCach.setText(khoangCach);
        idItemQuan = idQuan;

        dialogUpdateQuan.setView(view)
                .setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String textEdtTenQuan = edtTenQuan.getText().toString().trim();
                        String textEdtDiaChi = edtDiaChi.getText().toString().trim();
                        String textEdtKhoangCach = edtKhoangCach.getText().toString().trim();
                        if(textEdtTenQuan.matches("") || textEdtDiaChi.matches("") || textEdtKhoangCach.matches("")){
                            Toast.makeText(MainActivity_Setting.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_LONG).show();
                        }
                        else UpdateItemQuan(urlUpdateData_Quan);

                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        dialogUpdateQuan.show();
    }


    public void dialogAddQuan(){
        AlertDialog.Builder dialogAddQuan = new AlertDialog.Builder(MainActivity_Setting.this);
        LayoutInflater inflater = MainActivity_Setting.this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_add, null);
        edtTenQuanAdd = view.findViewById(R.id.editTenQuan);
        edtDiaChiAdd = view.findViewById(R.id.editDiaChi);
        edtKhoangCachAdd = view.findViewById(R.id.editKhoangCach);
        dialogAddQuan.setView(view)
                .setPositiveButton("Thêm quán", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String textEdtTenQuan = edtTenQuanAdd.getText().toString().trim();
                        String textEdtDiaChi = edtDiaChiAdd.getText().toString().trim();
                        String textEdtKhoangCach = edtKhoangCachAdd.getText().toString().trim();
                        if(textEdtTenQuan.matches("") || textEdtDiaChi.matches("") || textEdtKhoangCach.matches("")){
                            Toast.makeText(MainActivity_Setting.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_LONG).show();
                        }
                        else InsertItemQuan(urlInsertData_Quan);
                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        dialogAddQuan.show();
    }
}
