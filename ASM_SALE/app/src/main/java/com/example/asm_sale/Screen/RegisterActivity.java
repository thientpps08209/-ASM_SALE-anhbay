package com.example.asm_sale.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_sale.R;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

public class RegisterActivity extends AppCompatActivity {
    private Button btn_create_account,bt_back;
    private EditText ed_name, ed_mail, ed_pass, ed_cofirmpass;

    NoUI noUI = new NoUI(RegisterActivity.this);

    private Socket mSocket;

    {
        try {
            mSocket = IO.socket("http://192.168.1.3:2000");

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private void registerUser(String name, String email, String confrimpass, String password) {
        mSocket.emit("register", name, email, confrimpass, password);
    }

    private Emitter.Listener onRegister = new Emitter.Listener() {
        @Override
        public void call(Object... args) {


            String data = args[0].toString();

            NoUI noui = new NoUI(RegisterActivity.this);
            if (data.equals("true")) {
                noui.toast("Đang đăng ký...");

                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        setResult(RESULT_CANCELED);
                        noUI.toast("Đăng ký thành công");
                        finish();
                    }
                }, 1500);
            } else {
                Log.d("Error", "Đăng ký không thành công");
            }
        }
    };

    public boolean validateform(String name, String email, String password, String confirmpass) {


        try {
            if (name.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền tên", Toast.LENGTH_SHORT).show();
                ed_name.requestFocus();
                return false;
            } else if (email.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền email", Toast.LENGTH_SHORT).show();
                ed_mail.requestFocus();
                return false;
            } else if (password.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền mật khẩu", Toast.LENGTH_SHORT).show();
                ed_cofirmpass.requestFocus();
                return false;

            } else if (confirmpass.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền lại mật khẩu", Toast.LENGTH_SHORT).show();
                ed_cofirmpass.requestFocus();
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mSocket.connect();
        mSocket.on("register", onRegister);
        ed_name = findViewById(R.id.ed_username_02);
        ed_mail = findViewById(R.id.ed_Email_01);
        ed_pass = findViewById(R.id.ed_password_02);
        ed_cofirmpass = findViewById(R.id.ed_confim_password);


        btn_create_account = findViewById(R.id.bt_create_account);
        bt_back =findViewById(R.id.bt_back);

        btn_create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ed_name.getText().toString().trim();
                String email = ed_mail.getText().toString().trim();
                String confirmpass = ed_cofirmpass.getText().toString().trim();
                String password = ed_pass.getText().toString().trim();
                if (validateform(name, email, password, confirmpass)) {
                    mSocket.emit("register", name, email, password, confirmpass);
                }
            }
        });

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });


    }
}






