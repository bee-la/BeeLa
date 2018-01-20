package br.ufrpe.beela.lugar.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import br.ufrpe.beela.gui.R;

public class EscolhaProgramaAct extends AppCompatActivity {
    private ImageButton butaoSozinho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_programa);
        butaoSozinho =(ImageButton)findViewById(R.id.imageButton5);
        butaoSozinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EscolhaProgramaAct.this, LugarAct.class));
            }
        });
    }
}
