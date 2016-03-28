package com.maco.clientejuegos.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maco.clientejuegos.R;
import com.maco.clientejuegos.domain.Store;
import com.maco.clientejuegos.http.MessageRecoverer;
import com.maco.clientejuegos.http.NetTask;

import edu.uclm.esi.common.jsonMessages.JSONMessage;
import edu.uclm.esi.common.jsonMessages.JoinGameMessage;
import edu.uclm.esi.common.jsonMessages.LoginMessageAnnouncement;
import edu.uclm.esi.common.jsonMessages.OKMessage;
import edu.uclm.esi.common.jsonMessages.SudokuBoardMessage;
import edu.uclm.esi.common.jsonMessages.SudokuWaitingMessage;

public class WaitingAreaActivity extends AppCompatActivity implements IMessageDealerActivity {
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_area);
        this.layout=(LinearLayout) findViewById(R.id.layoutWA);
        Store store = Store.get();
        store.setCurrentContext(this);
        store.lanzarRecuperadorDeMensajes(this);

        //Creamos recuperador de mensajes.
        MessageRecoverer messageRecoverer= MessageRecoverer.get(this);
        messageRecoverer.setActivity(this);

        Thread t=new Thread(messageRecoverer);
        t.start();

        JoinGameMessage jgm = new JoinGameMessage(store.getUser().getIdUser(), store.getIdGame());
        NetTask task=new NetTask ("JoinGame.action", jgm);
        task.execute();
    }

    @Override
    public void showMessage(JSONMessage jsm) {
        //Se ejecuta cada vez que messageRecover mande una petición al servidor. Dependiendo del tipo hará una u otra cosa.
        if(jsm.getType().equals(LoginMessageAnnouncement.class.getSimpleName())){
            TextView tv=new TextView(this);
            tv.setText("Ha llegado "+((LoginMessageAnnouncement) jsm).getEmail());
            this.layout.addView(tv);
        }
        else if (jsm.getType().equals(SudokuBoardMessage.class.getSimpleName())){
            TextView tv=new TextView(this);
            tv.setText("Sudoku comienza!");
            this.layout.addView(tv);
            SudokuBoardMessage sbm=(SudokuBoardMessage) jsm;
            String casillas = sbm.getBoard();
            Store.get().setMatch(sbm.getIdMatch());
            Intent intent = new Intent(this, PartidaSudokuActivity.class);
            intent.putExtra("board", casillas);
            intent.putExtra("jugador1", sbm.getUser1());
            intent.putExtra("jugador2", sbm.getUser2());
            startActivity(intent);
        }
        else if(jsm.getType().equals(OKMessage.class.getSimpleName())){
            //TODO delete this, only exists for testing
            TextView tv=new TextView(this);
            tv.setText("Sudoku comienza!");
            this.layout.addView(tv);
        }
    }
}
