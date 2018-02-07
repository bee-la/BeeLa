package br.ufrpe.beela.lugar.gui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.negocio.LugarService;
import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.usuario.gui.LoginAct;

/**
 * Created by max on 23/01/18.
 */


public class LugarAcompanhadoAct extends AppCompatActivity {
    private LugarService lugarService = new LugarService();
    Button a;
    LugarService b;
    Toast Erro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teste);
        LoginAct.getPessoa().setPerfilAtual(lugarService.gerarLugar(this));
        b = new LugarService();
        a = findViewById(R.id.button24);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Erro = Toast.makeText(getApplicationContext(), LoginAct.getPessoa().getPerfilAtual().getNome(), Toast.LENGTH_SHORT);
                Erro.show();
            }
        });
    }

}


