package com.maco.clientejuegos.gui;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.maco.clientejuegos.R;
import com.maco.clientejuegos.domain.RankingEntry;
import com.maco.clientejuegos.domain.Store;
import com.maco.clientejuegos.domain.User;
import com.maco.clientejuegos.http.MessageRecoverer;
import com.maco.clientejuegos.http.Proxy;

import org.json.JSONException;

import java.util.List;
import java.util.concurrent.ExecutionException;

import edu.uclm.esi.common.jsonMessages.ErrorMessage;
import edu.uclm.esi.common.jsonMessages.JSONMessage;
import edu.uclm.esi.common.jsonMessages.OKMessage;
import edu.uclm.esi.common.jsonMessages.RankingMessage;

public class RankingActivity extends AppCompatActivity {
    private RankingTask rankingTask;
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

        showRanking();
    }

    public void showRanking() {
        if (rankingTask!=null)
            return;
        this.rankingTask=new RankingTask(this);
        this.rankingTask.execute();
        JSONMessage resultadoShowRanking = null;
        try {
            resultadoShowRanking = rankingTask.get();
            if (resultadoShowRanking.getType().equals(ErrorMessage.class.getSimpleName())) {
                ErrorMessage em=(ErrorMessage) resultadoShowRanking;
                Store.get().toast(em.getText());
            } else if (resultadoShowRanking.getType().equals(RankingMessage.class.getSimpleName())) {
                RankingMessage rm = (RankingMessage) resultadoShowRanking;
                rellenarRanking(rm.getRankingEntries());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Store.get().toast("Tarea interrumpida: " + e.getMessage());
        } catch (ExecutionException e) {
            e.printStackTrace();
            Store.get().toast("Error en ejecución: " + e.getMessage());
        }
        rankingTask=null;
    }

    /*
    public void showMessage(JSONMessage jsm){

        if (jsm.getType().equals(RankingMessage.class.getSimpleName())){
            RankingMessage rm = (RankingMessage) jsm;
            List<RankingEntry> rankingEntries = rm.getRankingEntries();
            rellenarRanking(rankingEntries);
        }
    }
    */

    private void rellenarRanking(List<RankingEntry> re){

    }

    class RankingTask extends AsyncTask<Void, Void, JSONMessage> {
        private final Context ctx;

        RankingTask(Context ctx) {
            this.ctx=ctx;
        }

        @Override
        protected JSONMessage doInBackground(Void... voids) {
            Proxy proxy= Proxy.get();
            try {
                JSONMessage resultadoRanking=proxy.doPost("ShowRanking.action");
                return resultadoRanking;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            } catch (ExecutionException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(JSONMessage jsonMessage) {
            super.onPostExecute(jsonMessage);
        }
    }
}
