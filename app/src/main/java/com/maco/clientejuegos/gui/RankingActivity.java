package com.maco.clientejuegos.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.maco.clientejuegos.R;
import com.maco.clientejuegos.domain.RankingEntry;
import com.maco.clientejuegos.domain.Store;
import com.maco.clientejuegos.http.MessageRecoverer;

import java.util.List;

import edu.uclm.esi.common.jsonMessages.JSONMessage;
import edu.uclm.esi.common.jsonMessages.RankingMessage;

public class RankingActivity extends AppCompatActivity implements IMessageDealerActivity {
    private LinearLayout layout;
    private ListView lv;
    private ListAdapter la;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        this.layout = (LinearLayout) findViewById(R.id.layoutRanking);
        Store.get().setCurrentContext(this);

        lv = (ListView) findViewById(R.id.listViewRanking); //Lista que muestra el ranking
        la = lv.getAdapter(); //Manejador de la ListView

        MessageRecoverer messageRecoverer = MessageRecoverer.get(this);
        messageRecoverer.setActivity(this);
    }

    public void showMessage(JSONMessage jsm){

        if (jsm.getType().equals(RankingMessage.class.getSimpleName())){
            RankingMessage rm = (RankingMessage) jsm;
            List<RankingEntry> rankingEntries = rm.getRankingEntries();
            rellenarRanking(rankingEntries);
        }
    }

    private void rellenarRanking(List<RankingEntry> re){

    }
}
