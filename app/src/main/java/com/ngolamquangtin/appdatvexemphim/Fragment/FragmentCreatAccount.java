package com.ngolamquangtin.appdatvexemphim.Fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

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
import com.google.android.material.textfield.TextInputLayout;
import com.ngolamquangtin.appdatvexemphim.Activity.LoginActivity;
import com.ngolamquangtin.appdatvexemphim.R;
import com.ngolamquangtin.appdatvexemphim.Util.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FragmentCreatAccount extends Fragment {

    TextInputLayout edlayoutname,edlayouttk,edlayoutpass,edlayoutconfirmpass, edlayoutemail, edlayoutbirthday, edlayoutphone;
    EditText txtname, txtaccount, txtpass, txtconfimpass, txtemail, txtdate, txtphone;
    Button btnCreate, btnHuy;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_createaccount, container, false);
        addControls(view);
        addEvents();
        return view;

    }

    private void addEvents() {
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        txtname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    edlayoutname.setError("Khong được bỏ trống");
                }else{
                    edlayoutname.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txtaccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    edlayouttk.setError("Khong được để trống");
                }else{
                    edlayouttk.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txtpass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    edlayoutpass.setError("Khong được bỏ trống !");
                }else{
                    edlayoutpass.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtconfimpass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    edlayoutconfirmpass.setError("Không được để trống");
                }else{
                    edlayoutconfirmpass.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txtemail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    edlayoutemail.setError("Không được để trống !");
                }else{
                    edlayoutemail.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtdate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    edlayoutbirthday.setError("Không được để trông !");
                }else{
                    edlayoutbirthday.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    edlayoutphone.setError("Không được để trống !");
                }else{
                    edlayoutphone.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });






        txtdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar temp = calendar;
                        temp.set(year, month, dayOfMonth);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        txtdate.setText(simpleDateFormat.format(temp.getTime()));

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Kiểm tra
                String name = txtname.getText().toString().trim();
                String account = txtaccount.getText().toString().trim();
                String pass = txtpass.getText().toString().trim();
                String confimpass = txtconfimpass.getText().toString().trim();
                String email = txtemail.getText().toString().trim();
                String date = txtdate.getText().toString().trim();
                String phone = txtphone.getText().toString().trim();

                SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date date1 = simpleDateFormat.parse(date);
                    date = output.format(date1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                if (name.equals("") && account.equals("") && pass.equals("")&& confimpass.equals("") && email.equals("") && date.equals("") && phone.equals("")) {
                    Toast.makeText(getActivity(), "Không được để trống thông tin", Toast.LENGTH_LONG).show();
                    return;
                }

                if (confimpass.equals(pass) == false) {
                    Toast.makeText(getActivity(), "Xác nhận pass ko với pass không giống nhau", Toast.LENGTH_LONG).show();
                    return;
                }


                xulyCreateAccount(name, account, Util.getMd5(pass), email, date, phone);


            }
        });
    }

    private void xulyCreateAccount(final String name, final String account, final String pass, final String email, final String date, final String phone) {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        String url = Util.LINK_REGISTER;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("/////", response);
                Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> maps = new HashMap<>();
                maps.put("hoten", name);
                maps.put("email", email);
                maps.put("ngaysinh", date);
                maps.put("sdt", phone);
                maps.put("account", account);
                maps.put("password", pass);
                return maps;
            }
        };

        requestQueue.add(stringRequest);

    }

    private void addControls(View view) {
        btnHuy = view.findViewById(R.id.btnhuy);
        edlayoutphone  = view.findViewById(R.id.edlayoutphone);
        edlayoutbirthday = view.findViewById(R.id.edlayoutbirthday);
        edlayoutemail = view.findViewById(R.id.edlayoutemail);
        edlayoutconfirmpass = view.findViewById(R.id.edlayoutconfirmpass);
        edlayoutpass = view.findViewById(R.id.edlayoutpass);
        edlayouttk = view.findViewById(R.id.edlayouttk);
        edlayoutname = view.findViewById(R.id.edlayoutname);
        txtname = view.findViewById(R.id.txtname);
        txtaccount = view.findViewById(R.id.txtaccount);
        txtpass = view.findViewById(R.id.txtpass);
        txtconfimpass = view.findViewById(R.id.txtconfimpass);
        txtemail = view.findViewById(R.id.txtemail);
        txtdate = view.findViewById(R.id.txtdate);
        txtphone = view.findViewById(R.id.txtsdt);
        btnCreate = view.findViewById(R.id.btncreate);
    }


}
