package com.maco.clientejuegos.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.maco.clientejuegos.R;
import com.maco.clientejuegos.domain.Store;

public class PartidaSudokuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida_sudoku);
        Store.get().setCurrentContext(this);
        String board;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                board= null;
            } else {
                board= extras.getString("board");
            }
        } else {
            board = (String) savedInstanceState.getSerializable("board");
        }
        //char[][] squares = new char[9][9];
        //for (int row = 0; row < 8; row++)
        //    for (int col = 0; col < 8; col++)
        //        squares[row][col] = board.charAt(row * 9 + col);
        //TODO debe haber una forma mejor de recorrer los textviews
        //TODO ids más significativos para los textview
        TextView casilla = (TextView) findViewById(R.id.textView83);
        casilla.setText("9");
    }
}
