package com.example.appkoichung.ViewController.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appkoichung.ViewController.MainActivity;
import com.example.appkoichung.Model.login.LoginRespone;
import com.example.appkoichung.Network.APIServer;
import com.example.appkoichung.Network.RetrofitClient;
import com.example.appkoichung.R;
import com.example.appkoichung.Util.AppConfig;
import com.example.appkoichung.Util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText edtUser, edtPass;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        edtPass = findViewById(R.id.edt_pass);
        edtUser = findViewById(R.id.edt_user);
        btnLogin = findViewById(R.id.btn_login);
        loginEvents();
    }

    private void loginEvents() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataUser();
            }
        });
    }

    private void getDataUser() {
        final ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
        dialog.setMessage("Đang xử lý");
        dialog.setCancelable(false);
        dialog.show();
        String user = edtUser.getText().toString();
        String pass = edtPass.getText().toString();
        Util.baseJsonBody();
        Util.jsonObject.addProperty("username", user);
        Util.jsonObject.addProperty("password", pass);
        RetrofitClient.getClient().create(APIServer.class).getUser(Util.jsonObject).enqueue(new Callback<LoginRespone>() {
            @Override
            public void onResponse(Call<LoginRespone> call, Response<LoginRespone> response) {
                dialog.dismiss();
                if (response.body().getStatus() == 1) {
                    int UserID = response.body().getResult().getUserID();
                    AppConfig.setUserID(LoginActivity.this, response.body().getResult().getUserID());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginRespone> call, Throwable t) {

            }
        });
    }
}
