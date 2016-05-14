package com.maco.clientejuegos.http;

import android.os.Handler;
import android.os.Looper;

import com.maco.clientejuegos.gui.PartidaSudokuActivity;

import edu.uclm.esi.common.jsonMessages.SudokuBoardMessage;
import edu.uclm.esi.common.jsonMessages.SudokuMovementMessage;

public class MovementSender implements Runnable {
    private final Proxy proxy;
    private boolean detenido;
    private Handler handler;
    private PartidaSudokuActivity activity;
    private static MovementSender yo;

    private MovementSender(final PartidaSudokuActivity activity) {
        this.proxy = Proxy.get();
        this.activity = activity;

    }

    public static MovementSender get(PartidaSudokuActivity activity) {
        if (yo == null) {
            yo = new MovementSender(activity);
        }
        return yo;
    }

    public void setActivity(PartidaSudokuActivity activity) {
        this.activity = activity;
    }

    @Override
    public void run() {
        Looper.prepare();

        while (!detenido) {
            try {
                String lastBoardSent = activity.getLastBoardSent();
                String newBoard = "";
                String currentBoard = activity.getBoard();
                //Se manda siempre el primer número del Sudoku (independientemente de que éste no varíe con respecto a la última vez que se mandó el D), para poder controlar la desconexión
                /*
                if (Character.isDigit(currentBoard.charAt(0))){
                    newBoard += currentBoard.charAt(0);
                    sendBoard(0, 0, activity.getCellValue(0));
                }
                */
                for (int i = 0; i < lastBoardSent.length(); i++) {
                    if (Character.isDigit(currentBoard.charAt(i)) && lastBoardSent.charAt(i) != (currentBoard.charAt(i))){
                        newBoard += currentBoard.charAt(i);
                        int row = i / 9;
                        int col = i % 9;
                        int value = activity.getCellValue(i);
                        sendBoard(row, col, value);
                    }
                    else{
                        if(i==0)
                            sendBoard(0, 0, lastBoardSent.charAt(0));
                        newBoard += lastBoardSent.charAt(i);
                    }
                }
                activity.setLastBoardSent(newBoard);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void sendBoard(int row, int col, int value){
        SudokuMovementMessage smm = new SudokuMovementMessage(row, col, value, Integer.parseInt(activity.getIdUser()), activity.getIdMatch());
        NetTask task = new NetTask("SendMovement.action", smm);
        task.execute();
    }

    public void detener() {
        this.detenido = true;
    }


}

