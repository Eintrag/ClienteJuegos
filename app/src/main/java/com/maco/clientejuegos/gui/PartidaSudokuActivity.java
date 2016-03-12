package com.maco.clientejuegos.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.maco.clientejuegos.R;
import com.maco.clientejuegos.domain.Store;

public class PartidaSudokuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida_sudoku);
        Store.get().setCurrentContext(this);
    }
}
