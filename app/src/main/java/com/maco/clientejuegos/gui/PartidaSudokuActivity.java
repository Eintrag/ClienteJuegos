package com.maco.clientejuegos.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maco.clientejuegos.R;
import com.maco.clientejuegos.domain.Store;
import com.maco.clientejuegos.http.MessageRecoverer;
import com.maco.clientejuegos.http.MovementSender;

import java.util.ArrayList;
import java.util.List;

import edu.uclm.esi.common.jsonMessages.JSONMessage;
import edu.uclm.esi.common.jsonMessages.SudokuBoardMessage;
import edu.uclm.esi.common.jsonMessages.SudokuMovementAnnouncementMessage;
import edu.uclm.esi.common.jsonMessages.SudokuWinnerMessage;

public class PartidaSudokuActivity extends AppCompatActivity implements IMessageDealerActivity {
    private List<TextView> casillas = new ArrayList<TextView>();
    private List<TextView> casillasRival = new ArrayList<TextView>();
    private int NUMCASILLAS = 81;
    private int idMatch;
    private String idUser;
    private LinearLayout layout;
    private String lastBoardSent;
    private String initialBoard;
    @Override
    public void showMessage(JSONMessage jsm) {
        //Se ejecuta cada vez que messageRecover mande una petición al servidor. Dependiendo del tipo hará una u otra cosa.
        if (jsm.getType().equals(SudokuMovementAnnouncementMessage.class.getSimpleName())) {
            SudokuMovementAnnouncementMessage sbm = (SudokuMovementAnnouncementMessage) jsm;
            if (sbm.getRow()==0 && sbm.getRow()==0){
                if (initialBoard.charAt(0)==' ' && (sbm.getValue()>0 && sbm.getValue()<10)){
                    casillasRival.get(0).setText((Character.toString('*')));
                }
            }else{
                casillasRival.get(sbm.getRow() * 9 + sbm.getCol()).setText((Character.toString('*')));
            }

        }
        else if (jsm.getType().equals(SudokuWinnerMessage.class.getSimpleName())){
            SudokuWinnerMessage swm = (SudokuWinnerMessage) jsm;
            if(swm.getUser1().equals(idUser)){
                Store.get().toast("Has ganado");
            }
            else {
                Store.get().toast("Has perdido");
            }
            Intent intent=new Intent(this, MainMenuActivity.class);
            startActivity(intent);
        }
    }
    public String getLastBoardSent(){
        return lastBoardSent;
    }
    public void setLastBoardSent(String lastBoardSent){
        this.lastBoardSent = lastBoardSent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida_sudoku);
        this.layout = (LinearLayout) findViewById(R.id.layoutPSudoku);
        Store.get().setCurrentContext(this);
        String jugador1;
        String jugador2;

        MessageRecoverer messageRecoverer = MessageRecoverer.get(this);
        messageRecoverer.setActivity(this);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                initialBoard = null;
                jugador1 = null;
                jugador2 = null;
                this.idMatch = 0;
            } else {
                initialBoard = extras.getString("board");
                jugador1 = extras.getString("jugador1");
                jugador2 = extras.getString("jugador2");
                this.idMatch = extras.getInt("idMatch");
            }
        } else {
            initialBoard = (String) savedInstanceState.getSerializable("board");
            jugador1 = (String) savedInstanceState.getSerializable("jugador1");
            jugador2 = (String) savedInstanceState.getSerializable("jugador2");
            this.idMatch = (int) savedInstanceState.getSerializable("idMatch");
        }
        Store store = Store.get();
        this.idUser = String.valueOf(store.getUser().getIdUser());
        //char[][] squares = new char[9][9];
        //for (int row = 0; row < 8; row++)
        //    for (int col = 0; col < 8; col++)
        //        squares[row][col] = board.charAt(row * 9 + col);
        //TODO debe haber una forma mejor de recorrer los textviews
        //TODO ids más significativos para los textview


        casillas.add((TextView) findViewById(R.id.tv0));
        casillas.add((TextView) findViewById(R.id.tv1));
        casillas.add((TextView) findViewById(R.id.tv2));
        casillas.add((TextView) findViewById(R.id.tv3));
        casillas.add((TextView) findViewById(R.id.tv4));
        casillas.add((TextView) findViewById(R.id.tv5));
        casillas.add((TextView) findViewById(R.id.tv6));
        casillas.add((TextView) findViewById(R.id.tv7));
        casillas.add((TextView) findViewById(R.id.tv8));
        casillas.add((TextView) findViewById(R.id.tv9));
        casillas.add((TextView) findViewById(R.id.tv10));
        casillas.add((TextView) findViewById(R.id.tv11));
        casillas.add((TextView) findViewById(R.id.tv12));
        casillas.add((TextView) findViewById(R.id.tv13));
        casillas.add((TextView) findViewById(R.id.tv14));
        casillas.add((TextView) findViewById(R.id.tv15));
        casillas.add((TextView) findViewById(R.id.tv16));
        casillas.add((TextView) findViewById(R.id.tv17));
        casillas.add((TextView) findViewById(R.id.tv18));
        casillas.add((TextView) findViewById(R.id.tv19));
        casillas.add((TextView) findViewById(R.id.tv20));
        casillas.add((TextView) findViewById(R.id.tv21));
        casillas.add((TextView) findViewById(R.id.tv22));
        casillas.add((TextView) findViewById(R.id.tv23));
        casillas.add((TextView) findViewById(R.id.tv24));
        casillas.add((TextView) findViewById(R.id.tv25));
        casillas.add((TextView) findViewById(R.id.tv26));
        casillas.add((TextView) findViewById(R.id.tv27));
        casillas.add((TextView) findViewById(R.id.tv28));
        casillas.add((TextView) findViewById(R.id.tv29));
        casillas.add((TextView) findViewById(R.id.tv30));
        casillas.add((TextView) findViewById(R.id.tv31));
        casillas.add((TextView) findViewById(R.id.tv32));
        casillas.add((TextView) findViewById(R.id.tv33));
        casillas.add((TextView) findViewById(R.id.tv34));
        casillas.add((TextView) findViewById(R.id.tv35));
        casillas.add((TextView) findViewById(R.id.tv36));
        casillas.add((TextView) findViewById(R.id.tv37));
        casillas.add((TextView) findViewById(R.id.tv38));
        casillas.add((TextView) findViewById(R.id.tv39));
        casillas.add((TextView) findViewById(R.id.tv40));
        casillas.add((TextView) findViewById(R.id.tv41));
        casillas.add((TextView) findViewById(R.id.tv42));
        casillas.add((TextView) findViewById(R.id.tv43));
        casillas.add((TextView) findViewById(R.id.tv44));
        casillas.add((TextView) findViewById(R.id.tv45));
        casillas.add((TextView) findViewById(R.id.tv46));
        casillas.add((TextView) findViewById(R.id.tv47));
        casillas.add((TextView) findViewById(R.id.tv48));
        casillas.add((TextView) findViewById(R.id.tv49));
        casillas.add((TextView) findViewById(R.id.tv50));
        casillas.add((TextView) findViewById(R.id.tv51));
        casillas.add((TextView) findViewById(R.id.tv52));
        casillas.add((TextView) findViewById(R.id.tv53));
        casillas.add((TextView) findViewById(R.id.tv54));
        casillas.add((TextView) findViewById(R.id.tv55));
        casillas.add((TextView) findViewById(R.id.tv56));
        casillas.add((TextView) findViewById(R.id.tv57));
        casillas.add((TextView) findViewById(R.id.tv58));
        casillas.add((TextView) findViewById(R.id.tv59));
        casillas.add((TextView) findViewById(R.id.tv60));
        casillas.add((TextView) findViewById(R.id.tv61));
        casillas.add((TextView) findViewById(R.id.tv62));
        casillas.add((TextView) findViewById(R.id.tv63));
        casillas.add((TextView) findViewById(R.id.tv64));
        casillas.add((TextView) findViewById(R.id.tv65));
        casillas.add((TextView) findViewById(R.id.tv66));
        casillas.add((TextView) findViewById(R.id.tv67));
        casillas.add((TextView) findViewById(R.id.tv68));
        casillas.add((TextView) findViewById(R.id.tv69));
        casillas.add((TextView) findViewById(R.id.tv70));
        casillas.add((TextView) findViewById(R.id.tv71));
        casillas.add((TextView) findViewById(R.id.tv72));
        casillas.add((TextView) findViewById(R.id.tv73));
        casillas.add((TextView) findViewById(R.id.tv74));
        casillas.add((TextView) findViewById(R.id.tv75));
        casillas.add((TextView) findViewById(R.id.tv76));
        casillas.add((TextView) findViewById(R.id.tv77));
        casillas.add((TextView) findViewById(R.id.tv78));
        casillas.add((TextView) findViewById(R.id.tv79));
        casillas.add((TextView) findViewById(R.id.tv80));


        casillasRival.add((TextView) findViewById(R.id.tvr0));
        casillasRival.add((TextView) findViewById(R.id.tvr1));
        casillasRival.add((TextView) findViewById(R.id.tvr2));
        casillasRival.add((TextView) findViewById(R.id.tvr3));
        casillasRival.add((TextView) findViewById(R.id.tvr4));
        casillasRival.add((TextView) findViewById(R.id.tvr5));
        casillasRival.add((TextView) findViewById(R.id.tvr6));
        casillasRival.add((TextView) findViewById(R.id.tvr7));
        casillasRival.add((TextView) findViewById(R.id.tvr8));
        casillasRival.add((TextView) findViewById(R.id.tvr9));
        casillasRival.add((TextView) findViewById(R.id.tvr10));
        casillasRival.add((TextView) findViewById(R.id.tvr11));
        casillasRival.add((TextView) findViewById(R.id.tvr12));
        casillasRival.add((TextView) findViewById(R.id.tvr13));
        casillasRival.add((TextView) findViewById(R.id.tvr14));
        casillasRival.add((TextView) findViewById(R.id.tvr15));
        casillasRival.add((TextView) findViewById(R.id.tvr16));
        casillasRival.add((TextView) findViewById(R.id.tvr17));
        casillasRival.add((TextView) findViewById(R.id.tvr18));
        casillasRival.add((TextView) findViewById(R.id.tvr19));
        casillasRival.add((TextView) findViewById(R.id.tvr20));
        casillasRival.add((TextView) findViewById(R.id.tvr21));
        casillasRival.add((TextView) findViewById(R.id.tvr22));
        casillasRival.add((TextView) findViewById(R.id.tvr23));
        casillasRival.add((TextView) findViewById(R.id.tvr24));
        casillasRival.add((TextView) findViewById(R.id.tvr25));
        casillasRival.add((TextView) findViewById(R.id.tvr26));
        casillasRival.add((TextView) findViewById(R.id.tvr27));
        casillasRival.add((TextView) findViewById(R.id.tvr28));
        casillasRival.add((TextView) findViewById(R.id.tvr29));
        casillasRival.add((TextView) findViewById(R.id.tvr30));
        casillasRival.add((TextView) findViewById(R.id.tvr31));
        casillasRival.add((TextView) findViewById(R.id.tvr32));
        casillasRival.add((TextView) findViewById(R.id.tvr33));
        casillasRival.add((TextView) findViewById(R.id.tvr34));
        casillasRival.add((TextView) findViewById(R.id.tvr35));
        casillasRival.add((TextView) findViewById(R.id.tvr36));
        casillasRival.add((TextView) findViewById(R.id.tvr37));
        casillasRival.add((TextView) findViewById(R.id.tvr38));
        casillasRival.add((TextView) findViewById(R.id.tvr39));
        casillasRival.add((TextView) findViewById(R.id.tvr40));
        casillasRival.add((TextView) findViewById(R.id.tvr41));
        casillasRival.add((TextView) findViewById(R.id.tvr42));
        casillasRival.add((TextView) findViewById(R.id.tvr43));
        casillasRival.add((TextView) findViewById(R.id.tvr44));
        casillasRival.add((TextView) findViewById(R.id.tvr45));
        casillasRival.add((TextView) findViewById(R.id.tvr46));
        casillasRival.add((TextView) findViewById(R.id.tvr47));
        casillasRival.add((TextView) findViewById(R.id.tvr48));
        casillasRival.add((TextView) findViewById(R.id.tvr49));
        casillasRival.add((TextView) findViewById(R.id.tvr50));
        casillasRival.add((TextView) findViewById(R.id.tvr51));
        casillasRival.add((TextView) findViewById(R.id.tvr52));
        casillasRival.add((TextView) findViewById(R.id.tvr53));
        casillasRival.add((TextView) findViewById(R.id.tvr54));
        casillasRival.add((TextView) findViewById(R.id.tvr55));
        casillasRival.add((TextView) findViewById(R.id.tvr56));
        casillasRival.add((TextView) findViewById(R.id.tvr57));
        casillasRival.add((TextView) findViewById(R.id.tvr58));
        casillasRival.add((TextView) findViewById(R.id.tvr59));
        casillasRival.add((TextView) findViewById(R.id.tvr60));
        casillasRival.add((TextView) findViewById(R.id.tvr61));
        casillasRival.add((TextView) findViewById(R.id.tvr62));
        casillasRival.add((TextView) findViewById(R.id.tvr63));
        casillasRival.add((TextView) findViewById(R.id.tvr64));
        casillasRival.add((TextView) findViewById(R.id.tvr65));
        casillasRival.add((TextView) findViewById(R.id.tvr66));
        casillasRival.add((TextView) findViewById(R.id.tvr67));
        casillasRival.add((TextView) findViewById(R.id.tvr68));
        casillasRival.add((TextView) findViewById(R.id.tvr69));
        casillasRival.add((TextView) findViewById(R.id.tvr70));
        casillasRival.add((TextView) findViewById(R.id.tvr71));
        casillasRival.add((TextView) findViewById(R.id.tvr72));
        casillasRival.add((TextView) findViewById(R.id.tvr73));
        casillasRival.add((TextView) findViewById(R.id.tvr74));
        casillasRival.add((TextView) findViewById(R.id.tvr75));
        casillasRival.add((TextView) findViewById(R.id.tvr76));
        casillasRival.add((TextView) findViewById(R.id.tvr77));
        casillasRival.add((TextView) findViewById(R.id.tvr78));
        casillasRival.add((TextView) findViewById(R.id.tvr79));
        casillasRival.add((TextView) findViewById(R.id.tvr80));


        //Bucle de relleno de los sudoku iniciales:
        poblarMiTablero(initialBoard);
        poblarTableroRival(initialBoard);
        lastBoardSent = getBoard();
        MovementSender movementSender = MovementSender.get(this);
        movementSender.setActivity(this);

        Thread t = new Thread(movementSender);
        t.start();

    }

    public String getBoard() {
        String board = "";
        for (int i = 0; i < this.NUMCASILLAS; i++) {
            String casilla = this.casillas.get(i).getText().toString();
            if (casilla.equals("") || Integer.parseInt(casilla) > 9) {
                board += " ";
            } else {
                board += this.casillas.get(i).getText();
            }
        }
        return board;
    }
    public int getCellValue(int i){
        int value =  Integer.parseInt(casillas.get(i).getText().toString());
        return value;
    }
    public String getIdUser() {
        return this.idUser;
    }

    public int getIdMatch() {
        return this.idMatch;
    }

    private void poblarMiTablero(String board) {
        for (int i = 0; i < board.length(); i++) {
            if (Character.isDigit(board.charAt(i))) {
                this.casillas.get(i).setText(Character.toString(board.charAt(i)));
                this.casillas.get(i).setEnabled(false);
            }
        }
    }

    private void poblarTableroRival(String board) {
        for (int i = 0; i < board.length(); i++) {
            //if (Character.isDigit(board.charAt(i))) {
            this.casillasRival.get(i).setText(Character.toString(board.charAt(i)));
            //}
        }

    }
}
