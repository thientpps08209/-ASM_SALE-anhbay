package com.example.asm_sale.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
    private Button btn_create_account;
    private EditText ed_name, ed_mail, ed_pass,ed_cofirmpass;
    private TextView tv_resetpassword;

    private Socket mSocket;

    {
        try {
            mSocket = IO.socket("http://10.82.147.243:2000");

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private void registerUser(String name, String email, String confrimpass ,String password) {
        mSocket.emit("register", name, email, confrimpass,password);
    }

    private Emitter.Listener onRegister = new Emitter.Listener() {
        @Override
        public void call(Object... args) {


            String data = args[0].toString();

            NoUI noui = new NoUI(RegisterActivity.this);
            if (data == "true") {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            } else {
                Log.d("error", "cant register");
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mSocket.connect();
        mSocket.on("register",onRegister);
        ed_name = findViewById(R.id.ed_username_02);
        ed_mail = findViewById(R.id.ed_Email_01);
        ed_pass = findViewById(R.id.ed_password_02);
        ed_cofirmpass =findViewById(R.id.ed_confim_password);
        tv_resetpassword =findViewById(R.id.tv_doimatkhau);

        btn_create_account =findViewById(R.id.bt_create_account);

        btn_create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ed_name.getText().toString().trim();
                String email = ed_mail.getText().toString().trim();
                String confirmpass = ed_cofirmpass.getText().toString().trim();
                String password = ed_pass.getText().toString().trim();
                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty() &&!confirmpass.isEmpty() ) {
                    registerUser(name,email,confirmpass,password);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your form", Toast.LENGTH_LONG).show();
                }



            }
        });

        tv_resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i2 = new Intent(RegisterActivity.this,ResetpassActivity.class);
               startActivity(i2);
            }
        });


    }
}