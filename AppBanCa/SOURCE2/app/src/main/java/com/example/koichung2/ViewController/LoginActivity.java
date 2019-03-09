package com.example.koichung2.ViewController;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.koichung2.MainActivity;
import com.example.koichung2.Model.LoginRespone;
import com.example.koichung2.Networks.APIServer;
import com.example.koichung2.Networks.RetrofitClient;
import com.example.koichung2.R;
import com.example.koichung2.Until.AppConfig;
import com.example.koichung2.Until.Until;
import com.example.koichung2.ViewController.Base.BaseActivity;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends BaseActivity {

    EditText edtUser, edtPass;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);

        init();
    }

    private void init() {
        edtUser = findViewById(R.id.edt_user);
        edtPass = findViewById(R.id.edt_pass);
        btnLogin = findViewById(R.id.btn_login);
        loginEvents();
    }

    private void loginEvents() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLoginData();
            }
        });
    }

    private void getLoginData() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Đang xử lý");
        dialog.setCancelable(false);
        dialog.show();

// get data
        String user = edtUser.getText().toString();
        String pass = edtPass.getText().toString();

        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("userAPI","madara");
        jsonBody.addProperty("passAPI","madara");
        jsonBody.addProperty("username",user);
        jsonBody.addProperty("password",pass);

        RetrofitClient.getCilent().create(APIServer.class).getUser(jsonBody).enqueue(new Callback<LoginRespone>() {
            @Override
            public void onResponse(Call<LoginRespone> call, Response<LoginRespone> response) {
                dialog.dismiss();
                if (response.body().getStatus()==1){
                    AppConfig.setUserID(LoginActivity.this, response.body().getUser().getUserID());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginRespone> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Vui lòng kiểm tra lại", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });


    }
}
