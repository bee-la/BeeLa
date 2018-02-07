package br.ufrpe.beela.lugar.gui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.negocio.LugarService;
import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.usuario.gui.LoginAct;

/**
 * Created by max on 23/01/18.
 */

public class teste extends AppCompatActivity {

    Button a ;
    LugarService b;
    Toast Erro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teste);
        gerarLugar();
        b = new LugarService();

        a = findViewById(R.id.button24);

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                try {
//                    startActivity((b.getMapa(0, 1)));
//                }catch (Exception ex){
//                    Erro = Toast.makeText(getApplicationContext(), "CU", Toast.LENGTH_SHORT);
//                    Erro.show();
//
//                }
//
            Erro = Toast.makeText(getApplicationContext(), LoginAct.getPessoa().getPerfilAtual().getNome(), Toast.LENGTH_SHORT);
            Erro.show();
            }
        });
    }

    public void gerarLugar(){
        PerfilDAO bd =  new PerfilDAO();
        bd.getLer(this);
        LoginAct.getPessoa().setPerfilAtual(bd.getFavorito(LoginAct.getPessoa().getId()));
    }


}


