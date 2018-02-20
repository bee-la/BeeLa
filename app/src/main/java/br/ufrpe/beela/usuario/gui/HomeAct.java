package br.ufrpe.beela.usuario.gui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import br.ufrpe.beela.database.negocio.AlimentarBancoDeDados;
import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.lugar.gui.EscolhaProgramaAct;
import br.ufrpe.beela.lugar.gui.LugarDetalhesAct;
import br.ufrpe.beela.perfil.gui.PerfilAct;
import br.ufrpe.beela.usuario.negocio.UsuarioService;

public class HomeAct extends AppCompatActivity {
    private TextView eAi;
    private TextView oQueTuQuer;
    private ImageButton botaoPerfil;
    private ImageButton botaoLugares;
    private ImageButton botaoConfiguracoes;
    private AlimentarBancoDeDados alimentarBancoDeDados = new AlimentarBancoDeDados();
    private UsuarioService usuarioService = new UsuarioService();
    private Button oqTuQuer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        alimentarBancoDeDados.gerarLugares(this);
        alterarFonte();
        irPerfil();
        irConfiguracoes();
        irLugares();
        irOqTuQuer();
    }

    private void alterarFonte() {
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        eAi = findViewById(R.id.textView4);
        eAi.setTypeface(fonte);

        oQueTuQuer = findViewById(R.id.button4);
        oQueTuQuer.setTypeface(fonte);
    }

    private void irPerfil() {
        botaoPerfil = findViewById(R.id.imageButton);
        botaoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeAct.this, PerfilAct.class));
            }
        });
    }

    private void irLugares() {
        botaoLugares = findViewById(R.id.imageButton2);
        botaoLugares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!usuarioService.verificarPerfilAtual(LoginAct.getPessoa().getId(),HomeAct.this)){
                    startActivity(new Intent(HomeAct.this, EscolhaProgramaAct.class));
                }
                else{
                    Toast.makeText(getApplicationContext(), "Você ainda não tem Perfil", Toast.LENGTH_SHORT).show();}
            }
        });
    }

    private void irConfiguracoes() {
        botaoConfiguracoes = findViewById(R.id.imageButton3);
        botaoConfiguracoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeAct.this, ConfiguracoesAct.class));
            }
        });
    }

    private void irOqTuQuer() {
        oqTuQuer = findViewById(R.id.button4);
        oqTuQuer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oQueTuQuerEuQuero();
            }
        });
    }

    private void oQueTuQuerEuQuero() {
        Lugar lugarzinho;
        lugarzinho = usuarioService.getLugar(this);
        Intent intent = new Intent(HomeAct.this, LugarDetalhesAct.class);
        intent.putExtra("lugar", (Serializable) lugarzinho);
        startActivity(intent);


    }

}


