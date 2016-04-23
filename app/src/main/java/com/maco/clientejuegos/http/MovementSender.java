package com.maco.clientejuegos.http;

import android.os.Handler;
import android.os.Looper;

import com.maco.clientejuegos.gui.PartidaSudokuActivity;

import edu.uclm.esi.common.jsonMessages.SudokuBoardMessage;

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
                SudokuBoardMessage sbm = new SudokuBoardMessage(activity.getBoard(), activity.getIdUser(), activity.getIdUser(), activity.getIdMatch());
                NetTask task = new NetTask("SendMovement.action", sbm);
                task.execute();
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void detener() {
        this.detenido = true;
    }


}

