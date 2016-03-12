package edu.uclm.esi.common.jsonMessages;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by David on 03/03/2016.
 */
public class SudokuBoardMessage extends JSONMessage{
    @JSONable
    private String board;
    @JSONable
    private String user1;
    @JSONable
    private String user2;
    @JSONable
    private int idMatch;

    public SudokuBoardMessage (String board, String user1, String user2, int idMatch){
        super(false);
        this.board=board;
        this.user1=user1;
        this.user2=user2;
        this.idMatch=idMatch;
    }

    public SudokuBoardMessage (JSONObject jso) throws JSONException {
        super(false);
        if (jso.has("board")) {
            this.board=jso.getString("board");
        }
        if (jso.has("user1")) {
            this.user1=jso.getString("user1");
        }
        if (jso.has("user2")) {
            this.user2=jso.getString("user2");
        }
        if (jso.has("idMatch")) {
            this.idMatch=jso.getInt("idMatch");
        }
    }

    public int getIdMatch(){
        return idMatch;
    }

    public String getBoard(){
        return board;
    }

    public String getUser1(){
        return user1;
    }

    public String getUser2(){
        return user2;
    }
}
