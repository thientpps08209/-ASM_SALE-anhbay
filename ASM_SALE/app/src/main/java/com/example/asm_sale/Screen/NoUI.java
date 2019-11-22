package com.example.asm_sale.Screen;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class NoUI {
    public Context context;

    public NoUI(Context context) {
        this.context = context;
    }

    public void toast(final String text) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, text, Toast.LENGTH_LONG).show();
            }
        });
    }

}
