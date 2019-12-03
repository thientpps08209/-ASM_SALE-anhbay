package com.example.asm_sale.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.asm_sale.R;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

public class ResetpassActivity extends AppCompatActivity {
    private EditText ed_oldpassword,ed_newpassword,ed_cobfirmpass;
    private Button bt_changerpass;
    private TextView tv_id;
    Socket msocKet;
    {
        try {
            msocKet = IO.socket("http://10.82.147.243:2000");
        } catch (URISyntaxException e) {
            throw new RuntimeException();
        }
    }
    private Emitter.Listener onUpuser = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            String data = args[0].toString();
            if (data == "true"){
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }else {
                Log.d("error","cant Update");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpass);
        msocKet.connect();
         msocKet.on("updateUser",onUpuser);
        ed_oldpassword = findViewById(R.id.et_matkhau_cu);
        ed_newpassword = findViewById(R.id.et_matkhau_moi);
        ed_cobfirmpass = findViewById(R.id.et_matkhau_capnhat);
        bt_changerpass = findViewById(R.id.bt_chpassword);
        tv_id=findViewById(R.id.tv_id);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        final String _id = b.getString("_id");
        final String password = b.getString("password");
        final String newpassword = b.getString("newpassword");
        final String confirmpass = b.getString("confirmpass");

        ed_newpassword.setText(password);
        ed_oldpassword.setText(confirmpass);
        ed_cobfirmpass.setText(newpassword);

        bt_changerpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ednewpassword = ed_newpassword.getText().toString();
                String edoldpassword = ed_oldpassword.getText().toString();
                String edconfirmpass = ed_cobfirmpass.getText().toString();
                User(_id,password,newpassword,confirmpass);
                startActivity(new Intent(ResetpassActivity.this,LoginActivity.class));
                finish();

            }
        });
    }
    private void User(final String _id, final String password, final String confirmpass, final String newpassword ){

        msocKet.emit("updateUser", _id,password,confirmpass,newpassword);
    }

}








