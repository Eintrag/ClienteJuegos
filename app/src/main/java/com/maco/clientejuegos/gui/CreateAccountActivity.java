package com.maco.clientejuegos.gui;

import android.content.Context;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.maco.clientejuegos.R;
import com.maco.clientejuegos.domain.Store;
import com.maco.clientejuegos.domain.User;
import com.maco.clientejuegos.http.Proxy;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;

import edu.uclm.esi.common.jsonMessages.ErrorMessage;
import edu.uclm.esi.common.jsonMessages.JSONMessage;
import edu.uclm.esi.common.jsonMessages.LoginMessage;
import edu.uclm.esi.common.jsonMessages.OKMessage;
import edu.uclm.esi.common.jsonMessages.RegisterMessage;

/**
 * Created by David on 25/02/2016.
 */
public class CreateAccountActivity extends AppCompatActivity{
    private RegisterTask registerTask;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Store.get().setCurrentContext(this);
    }

    public void createAccount(View view) {
        if (registerTask!=null)
            return;
        EditText etEmail= (EditText) this.findViewById(R.id.etEmail);
        EditText etPwd1= (EditText) this.findViewById(R.id.etPwd1);
        EditText etPwd2=(EditText) this.findViewById(R.id.etPwd2);
        String email=etEmail.getText().toString();
        String pwd1=etEmail.getText().toString();
        String pwd2=etEmail.getText().toString();
        RegisterTask rt=new RegisterTask(this, etEmail.getText().toString(), etPwd1.getText().toString(), etPwd2.getText().toString());
        rt.execute();
        JSONMessage resultado= null;
        try {
            resultado = rt.get();
            if (resultado.getType().equals(ErrorMessage.class.getSimpleName())) {
                ErrorMessage em=(ErrorMessage) resultado;
                Store.get().toast(em.getText());
            } else if (resultado.getType().equals(OKMessage.class.getSimpleName())) {
                OKMessage okM = (OKMessage) resultado;
                Store.get().toast("Cuenta creada, " + email);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Store.get().toast("Tarea interrumpida: " + e.getMessage());
        } catch (ExecutionException e) {
            e.printStackTrace();
            Store.get().toast("Error en ejecución: " + e.getMessage());
        }
        registerTask=null;
    }

    class RegisterTask extends AsyncTask<Void, Void, JSONMessage> {
        private final Context ctx;
        private String email;
        private String pwd1;
        private String pwd2;

        RegisterTask(Context ctx, String email, String pwd1, String pwd2) {
            this.ctx=ctx;
            this.email=email;
            this.pwd1=pwd1;
            this.pwd2=pwd2;
        }

        @Override
        protected JSONMessage doInBackground(Void... voids) {
            RegisterMessage rm = new RegisterMessage(email, pwd1, pwd2);
            Proxy proxy = Proxy.get();
            try {
                JSONMessage resultadoRegister = proxy.doPost("Register.action", rm);
                return resultadoRegister;
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
