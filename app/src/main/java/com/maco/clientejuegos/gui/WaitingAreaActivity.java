package com.maco.clientejuegos.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.maco.clientejuegos.R;
import com.maco.clientejuegos.domain.Store;

public class WaitingAreaActivity extends AppCompatActivity implements IMessageDealerActivity {
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_area);
        this.layout=(LinearLayout) findViewById(R.id.layoutWA);
        Store.get().setCurrentContext(this);
        Store.get().lanzarRecuperadorDeMensajes(this);
    }

    @Override
    public void showMessage(String msg) {
        TextView tv=new TextView(this);
        tv.setText(msg);
        this.layout.addView(tv);
    }
}
