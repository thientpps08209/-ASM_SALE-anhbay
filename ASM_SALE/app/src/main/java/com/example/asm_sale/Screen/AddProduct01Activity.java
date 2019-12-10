package com.example.asm_sale.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.asm_sale.R;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

public class AddProduct01Activity extends AppCompatActivity {
    private ImageView Img_hinh;
    private Button bt_add,bt_exit;
    private EditText ed_masp,ed_tensp,ed_nhasx,ed_giasp;
    Uri uri_img;
    String hinhSp;

    private Socket mSocket;

    {
        try {
//
            mSocket = IO.socket("http://");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product01);
        Img_hinh = findViewById(R.id.img_hinhSp);
        bt_add =findViewById(R.id.bt_ADD_01);
        bt_exit =findViewById(R.id.bt_EXIT_01);
        ed_masp =findViewById(R.id.ed_masanpham01);
        ed_tensp =findViewById(R.id.ed_tensanpham01);
        ed_nhasx =findViewById(R.id.ed_nhasanxuat01);
        ed_giasp=findViewById(R.id.ed_giasanpham01);
    }
}
