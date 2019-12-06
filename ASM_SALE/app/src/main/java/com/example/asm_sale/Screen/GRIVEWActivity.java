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
import android.widget.ImageView;

import com.example.asm_sale.R;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

public class GRIVEWActivity extends AppCompatActivity {

    EditText ed_tensp, ed_giaban, ed_nsx;
    Button bt_add, bt_canel;
    ImageView iv_addImg;
    String hinhsp;

    private Socket msocket;

    {
        try {
           msocket = IO.socket("10.82.148.192:2000");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private Emitter.Listener onAddProduct= new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            String data= args[0].toString();

            if (data.equals("true")) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(GRIVEWActivity.this, Product01sActivity.class));
                        setResult(RESULT_OK);
                        finish();
                    }
                }, 1000);
            } else {
                Log.d("Error", "Thêm không thành công");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grivew);
        msocket.connect();
        msocket.on("addProduct",onAddProduct);

        //anh xa
        init();


      //  iv_addImg.setOnClickListener();

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String tensp =ed_tensp.getText().toString().trim();
               String nsx =ed_nsx.getText().toString().trim();
               String giaban =ed_giaban.getText().toString().trim();

            }
        });
    }
    private void init(){
      ed_tensp =findViewById(R.id.ed_tenSp);
      ed_nsx=findViewById(R.id.ed_nsx);
      ed_giaban=findViewById(R.id.ed_gianBan);
      bt_add=findViewById(R.id.btn_add);
      bt_canel=findViewById(R.id.btn_cancel);
      iv_addImg=findViewById(R.id.iv_addImg);
    }
    //Load IMG
    View.OnClickListener uploadImg =new View.OnClickListener(){
        @Override
        public void onClick(View view) {

        }
    };

}
