package br.ufrpe.beela.lugar.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.usuario.gui.ContatoAct;

public class EscolhaProgramaAct extends AppCompatActivity {
    private ImageButton botaoSozinho;
    private ImageButton botaoAcompanhado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_programa);
        encontrarLugarSozinho();
        encontratLugarAcompanhado();
    }

    private void encontrarLugarSozinho(){
        botaoSozinho =(ImageButton)findViewById(R.id.imageButton5);
        botaoSozinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EscolhaProgramaAct.this, LugarAct.class));
            }
        });
    }

    private void encontratLugarAcompanhado(){
        botaoAcompanhado=(ImageButton)findViewById(R.id.imageButton7);
        botaoAcompanhado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EscolhaProgramaAct.this, ContatoAct.class));
            }
        });
    }
}
