package com.example.appdatvexemphim.Fragment;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.appdatvexemphim.Activity.LoginActivity;
import com.example.appdatvexemphim.R;

public class FragmentProfile extends Fragment {

    TextView txtTennguoidung, txtdangxuatdangnhap;
    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        addControls(view);
        loadUser();
        addEvents();
        return view;
    }

    private void addEvents() {
        txtdangxuatdangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) v;
                if(textView.getText().toString().equals("Đăng nhập")){
                    Intent i = new Intent(getActivity(), LoginActivity.class);
                    startActivity(i);
                }else{
                    sharedPreferences.edit().clear();
                    sharedPreferences.edit().commit();
                    txtTennguoidung.setText("Chưa đăng nhập");
                    txtdangxuatdangnhap.setText("Đăng nhập");
                    Toast.makeText(getActivity(), "Đã đăng xuất", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void loadUser() {
        String hoten=sharedPreferences.getString("hoten", "Chưa đăng nhập");
        if(hoten.equals("Chưa đăng nhập")){
            txtdangxuatdangnhap.setText("Đăng nhập");
        }else{
            txtdangxuatdangnhap.setText("Đăng xuất");
            txtTennguoidung.setText(hoten);
        }

    }

    private void addControls(View view) {
        txtTennguoidung = view.findViewById(R.id.txtTennguoidung);
        txtdangxuatdangnhap = view.findViewById(R.id.txtdangxuatdangnhap);
        sharedPreferences = getActivity().getSharedPreferences("datalogin", Context.MODE_PRIVATE);
    }



}
