package br.ufrpe.beela.usuario.gui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import br.ufrpe.beela.gui.R;

/**
 * Created by max on 06/01/18.
 */

public class SplashAct extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handle = new Handler();

        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarLogin();
            }
        }, 2500);

    }

    private void mostrarLogin() {
        Intent intent = new Intent(SplashAct.this, LoginAct.class);
        startActivity(intent);
        finish();
    }

}