package br.ufrpe.beela.lugar.gui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.negocio.LugarService;

/**
 * Created by max on 06/01/18.
 */

public class LugaresAct extends AppCompatActivity {

    private Button btIr;
    private LugarService local = new LugarService();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugares_sugestao);

    btIr = findViewById(R.id.buttonIr);

    btIr.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            local.chamarMapa();
        }
    });

    }









}