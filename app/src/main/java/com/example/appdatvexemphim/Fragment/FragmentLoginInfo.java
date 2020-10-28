package com.example.appdatvexemphim.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appdatvexemphim.Activity.CreateAccountActivity;
import com.example.appdatvexemphim.Activity.HomeActivity;
import com.example.appdatvexemphim.R;
import com.example.appdatvexemphim.Util.Util;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FragmentLoginInfo extends Fragment {

    TextInputLayout editlayouttk, editlayoutmk;
    TextView txttendangnhap, txtmakhau, txttaotk;
    Button btnlogin, btnhuy;

    SharedPreferences sharedPreferences;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_logininfo, container, false);
        addControls(view);
        addEvents();
        return view;
    }

    private void addEvents() {
        txttendangnhap.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    editlayouttk.setError("Bắt buộc phải nhập");
                }else{
                    editlayouttk.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txtmakhau.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    editlayoutmk.setError("Bắt buộc phải nhập pass");
                }else{
                    editlayoutmk.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulyLogin();
            }
        });
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

    }

    private void xulyLogin() {
        final String tendangnhap = txttendangnhap.getText().toString().trim();
        String password = txtmakhau.getText().toString().trim();
        final String md5pass = Util.getMd5(password);

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        String url = Util.LINK_LOGIN;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        if (response.equals("fail") == false) {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            Editor editor = sharedPreferences.edit();
                            editor.putString("id", jsonObject.getString("ID"));
                            editor.putString("hoten", jsonObject.getString("HoTen"));
                            editor.putString("email", jsonObject.getString("Email"));
                            editor.putString("sdt", jsonObject.getString("SDT"));
                            editor.putString("ngaysinh", jsonObject.getString("NgaySinh"));
                            editor.putString("taikhoan", jsonObject.getString("Account"));
                            editor.putString("pass", jsonObject.getString("Password"));
                            editor.putString("trangthai", "1");
                            editor.apply();
                            Intent i = new Intent(getActivity(), HomeActivity.class);
                            startActivity(i);
                        } else {
                            Log.d("/////", "Thất bại");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Loi", error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> maps = new HashMap<>();
                maps.put("account", tendangnhap);
                maps.put("password", md5pass);
                return maps;
            }
        };

        requestQueue.add(stringRequest);
    }

    private void addControls(View view) {
        editlayouttk = view.findViewById(R.id.edlayouttk);
        editlayoutmk = view.findViewById(R.id.edlayoupass);
        txttendangnhap = view.findViewById(R.id.txttendangnhap);
        txtmakhau = view.findViewById(R.id.txtmatkhau);
        txttaotk = view.findViewById(R.id.txttaotk);
        btnlogin = view.findViewById(R.id.btnlogin);
        txttaotk = view.findViewById(R.id.txttaotk);
        txttaotk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), CreateAccountActivity.class);
                startActivity(i);
            }
        });
        btnhuy = view.findViewById(R.id.btnhuy);

        sharedPreferences = getActivity().getSharedPreferences("datalogin", Context.MODE_PRIVATE);

    }


}
