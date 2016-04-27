package com.maco.clientejuegos.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.maco.clientejuegos.R;
import com.maco.clientejuegos.domain.Store;
import com.maco.clientejuegos.http.MessageRecoverer;

import edu.uclm.esi.common.jsonMessages.JSONMessage;

public class RankingActivity extends AppCompatActivity implements IMessageDealerActivity {
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        this.layout = (LinearLayout) findViewById(R.id.layoutRanking);
        Store.get().setCurrentContext(this);

        ListView lv = (ListView) findViewById(R.id.listViewRanking); //Lista que muestra el ranking
        ListAdapter la = lv.getAdapter(); //Manejador de la ListView

        MessageRecoverer messageRecoverer = MessageRecoverer.get(this);
        messageRecoverer.setActivity(this);
    }

    public void showMessage(JSONMessage jsm){
        if (jsm.getType().equals
    }
}
