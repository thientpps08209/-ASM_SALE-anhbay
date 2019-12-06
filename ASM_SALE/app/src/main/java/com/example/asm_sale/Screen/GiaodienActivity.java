package com.example.asm_sale.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.asm_sale.R;

public class GiaodienActivity extends AppCompatActivity {
    private ImageView product1,product2,product3,product4,product5,product6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaodien);
        product1 =findViewById(R.id.iv_p1);
        product2 =findViewById(R.id.iv_p2);
        product3 =findViewById(R.id.iv_p3);
        product4 =findViewById(R.id.iv_p4);
        product5 =findViewById(R.id.iv_p5);
        product6 =findViewById(R.id.iv_p6);

        product1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p01 = new Intent(GiaodienActivity.this,Product01sActivity.class);
                startActivity(p01);
            }
        });

        product2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p02 = new Intent(GiaodienActivity.this,Product02Activity.class);
                startActivity(p02);
            }
        });

        product3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p03 = new Intent(GiaodienActivity.this,Product03Activity.class);
                startActivity(p03);
            }
        });

        product4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p04 = new Intent(GiaodienActivity.this,Product04Activity.class);
                startActivity(p04);
            }
        });

        product5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p05 = new Intent(GiaodienActivity.this,Product05Activity.class);
                startActivity(p05);
            }
        });

        product6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p06 = new Intent(GiaodienActivity.this,Product06Activity.class);
                startActivity(p06);
            }
        });

    }
}
