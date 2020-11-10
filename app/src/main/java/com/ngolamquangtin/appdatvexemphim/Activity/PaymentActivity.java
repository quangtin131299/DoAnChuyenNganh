package com.ngolamquangtin.appdatvexemphim.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ngolamquangtin.appdatvexemphim.DTO.TickerBook;
import com.ngolamquangtin.appdatvexemphim.R;
import com.ngolamquangtin.appdatvexemphim.Util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import vn.momo.momo_partner.AppMoMoLib;

public class PaymentActivity extends AppCompatActivity {

    TickerBook tickerBook;
    EditText edttenkhachhang, edtsodienthoai;
    Button btnthanhtoan;
    ImageView imgback;
    private String amount = "45000";
    private String fee = "0";
    int environment = 0;//developer default
    private String merchantName = "Cinema Plust +";
    private String merchantCode = "MOMO34SR20201026";
    private String merchantNameLabel = "Cinema Plust +";
    private String description = "Thanh toán mua ve online";


    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        AppMoMoLib.getInstance().setEnvironment(AppMoMoLib.ENVIRONMENT.DEVELOPMENT);
        addControls();
        updateUI();
        addEvents();

    }

    private void addControls() {
        btnthanhtoan = findViewById(R.id.btnthanhtoan);
        edttenkhachhang = findViewById(R.id.edttenkhachhang);
        imgback = findViewById(R.id.imgback);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(PaymentActivity.this)
                        .setTitle("Thông báo")
                        .setMessage("Vé của bạn đã bị hủy!");
                builder.show();
                Intent i = new Intent(PaymentActivity.this, HomeActivity.class);
                startActivity(i);
            }
        }, 5000);
    }

    private void addEvents() {
        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPayment();
            }
        });
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void updateUI() {
        sharedPreferences = getSharedPreferences("datalogin", Context.MODE_PRIVATE);
        edttenkhachhang.setText(sharedPreferences.getString("hoten", ""));
    }

    private void xulyDatVe() {
        Intent intent = getIntent();
        if (intent.hasExtra("TICKERBOOK")) {
            tickerBook = (TickerBook) intent.getSerializableExtra("TICKERBOOK");
            if (tickerBook.getIdghe() != 0 && tickerBook.getIdkhachhang() != 0 && tickerBook.getIdphong() != 0 && tickerBook.getNgaydat() != "") {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Calendar calendar = Calendar.getInstance();
                String url = String.format(Util.LINK_DATVE, simpleDateFormat.format(calendar.getTime())
                        , tickerBook.getIdsuat()
                        , tickerBook.getIdghe()
                        , tickerBook.getIdphim()
                        , tickerBook.getIdkhachhang()
                        , tickerBook.getIdrap()
                        , "Đã đặt", tickerBook.getIdphong());
                RequestQueue requestQueue = Volley.newRequestQueue(PaymentActivity.this);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(PaymentActivity.this).setMessage(response);
                            builder.show();
                            Intent i = new Intent(PaymentActivity.this, HomeActivity.class);
                            startActivity(i);
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                requestQueue.add(stringRequest);
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(PaymentActivity.this).setTitle("Thông báo !").setMessage("Thông tin đặt vé không hợp lệ");
                builder.show();
            }

        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppMoMoLib.getInstance().REQUEST_CODE_MOMO && resultCode == -1) {
            if (data != null) {
                if (data.getIntExtra("status", -1) == 0) {
                    //TOKEN IS AVAILABLE
                    xulyDatVe();
                    Toast.makeText(PaymentActivity.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                    String token = data.getStringExtra("data"); //Token response
                    String phoneNumber = data.getStringExtra("phonenumber");
                    String env = data.getStringExtra("env");
                    if (env == null) {
                        env = "app";
                    }

                    if (token != null && !token.equals("")) {
                        // TODO: send phoneNumber & token to your server side to process payment with MoMo server
                        // IF Momo topup success, continue to process your order
                    } else {
//                        tvMessage.setText("message: " + this.getString(R.string.not_receive_info));
                    }
                } else if (data.getIntExtra("status", -1) == 1) {
                    //TOKEN FAIL
                    String message = data.getStringExtra("message") != null ? data.getStringExtra("message") : "Thất bại";
//                    tvMessage.setText("message: " + message);
                } else if (data.getIntExtra("status", -1) == 2) {
                    //TOKEN FAIL
//                    tvMessage.setText("message: " + this.getString(R.string.not_receive_info));
                } else {
                    //TOKEN FAIL
//                    tvMessage.setText("message: " + this.getString(R.string.not_receive_info));
                }
            } else {
//                tvMessage.setText("message: " + this.getString(R.string.not_receive_info));
            }
        } else {
//            tvMessage.setText("message: " + this.getString(R.string.not_receive_info_err));
        }
    }

    //Get token through MoMo app
    private void requestPayment() {
        Intent i = getIntent();
        AppMoMoLib.getInstance().setAction(AppMoMoLib.ACTION.PAYMENT);
        AppMoMoLib.getInstance().setActionType(AppMoMoLib.ACTION_TYPE.GET_TOKEN);
//        if (edAmount.getText().toString() != null && edAmount.getText().toString().trim().length() != 0)
//            amount = edAmount.getText().toString().trim();

        Map<String, Object> eventValue = new HashMap<>();
        //client Required
        eventValue.put("merchantname", merchantName); //Tên đối tác. được đăng ký tại https://business.momo.vn. VD: Google, Apple, Tiki , CGV Cinemas
        eventValue.put("merchantcode", merchantCode); //Mã đối tác, được cung cấp bởi MoMo tại https://business.momo.vn
        eventValue.put("amount", amount); //Kiểu integer
        eventValue.put("orderId", "orderId123456789"); //uniqueue id cho BillId, giá trị duy nhất cho mỗi BILL
        eventValue.put("orderLabel", "Mã đơn hàng"); //gán nhãn

//        client Optional - bill info
        eventValue.put("merchantnamelabel", "Mua bán");//gán nhãn
        eventValue.put("fee", fee); //Kiểu integer
        eventValue.put("description", description); //mô tả đơn hàng - short description

//        client extra data
        eventValue.put("requestId", merchantCode + "merchant_billId_" + System.currentTimeMillis());
        eventValue.put("partnerCode", merchantCode);
        //Example extra data
        JSONObject objExtraData = new JSONObject();
        try {
            objExtraData.put("site_code", "1");
            objExtraData.put("site_name", i.getStringExtra("TEN_RAP"));
            objExtraData.put("screen_code", 0);
            objExtraData.put("screen_name", "Special");
            objExtraData.put("movie_name", i.getStringExtra("TEN_PHIM"));
            objExtraData.put("movie_format", "HD");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        eventValue.put("extraData", objExtraData.toString());

        eventValue.put("extra", "");
        AppMoMoLib.getInstance().requestMoMoCallBack(this, eventValue);


    }
}