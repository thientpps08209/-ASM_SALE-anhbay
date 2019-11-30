package com.example.asm_sale.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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

public class LoginActivity extends AppCompatActivity {
  private    Button bt_login;
  private    EditText ed_email,ed_password;
  private    TextView tv_create,tv_forgot;
    String str_username ,str_password;

    private Socket mSocket;

    {
        try {
            mSocket = IO.socket("http://10.82.147.232:2000");

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    public void toast(final Context context, final String text) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, text, Toast.LENGTH_LONG).show();
            }
        });
    }
    private Emitter.Listener onLogin = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            String data = args[0].toString();
            if (data == "true") {
                toast(LoginActivity.this, "Login Success");
                Intent intent = new Intent(LoginActivity.this,GiaodienActivity.class);
                startActivity(intent);

                finish();
            } else {
            //    toast(LoginActivity.this, "Login Fail");
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSocket.connect();
        mSocket.on("login", onLogin);
        ed_email= findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);
        bt_login = findViewById(R.id.bt_login);
        tv_create = findViewById(R.id.tv_create);
        tv_forgot = findViewById(R.id.tv_forgot);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = ed_email.getText().toString().trim();
                String password = ed_password.getText().toString().trim();

                if (!email.isEmpty() && !password.isEmpty()) {
                    //login user
                    checkLogin(email, password);
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter the credentials!", Toast.LENGTH_LONG).show();
                }


            }
        });

        tv_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i3);

            }
        });

        tv_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i4 = new Intent(LoginActivity.this,GiaodienActivity.class);
                startActivity(i4);
            }
        });
    }

    private void checkLogin(String email, String password) {
        // pDialog.setMessage("Login in:...");
        //  showDialog();
        mSocket.emit("login", email, password);
    }

}

