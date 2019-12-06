package com.example.asm_sale.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.asm_sale.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Product01sActivity extends AppCompatActivity {

    FloatingActionButton fl_themsp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products01);
        fl_themsp=(FloatingActionButton)findViewById(R.id.fl_btsanpham);

    }
}
