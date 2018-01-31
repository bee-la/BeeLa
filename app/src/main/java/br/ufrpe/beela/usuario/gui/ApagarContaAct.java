package br.ufrpe.beela.usuario.gui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.usuario.dao.UsuarioDAO;
import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.usuario.dominio.Usuario;

public class ApagarContaAct extends AppCompatActivity implements View.OnClickListener {
    private Button botaoApagar;
    private TextView aviso1, aviso2;
    private Toast mensagemApagada;
    private Usuario usuario= LoginAct.getUsuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apagar_conta);

        criarBotoesTela();
        alterarFonte();

    }

    private void criarBotoesTela() {
        botaoApagar = (Button) findViewById(R.id.button9);
        botaoApagar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){

        switch (view.getId()){

            case R.id.button9:
                deletarConta();

        }




    }

    private void alterarFonte(){
        aviso1 = (TextView) findViewById(R.id.textView5);
        aviso2 = (TextView) findViewById(R.id.textView6);
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        botaoApagar.setTypeface(fonte);
        aviso1.setTypeface(fonte);
        aviso2.setTypeface(fonte);
    }
// SwitchCase
//    private void clicarBotaoApagar(){
//        botaoApagar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                deletarConta();
//            }
//        });
//    }

    public void deletarConta() {
        UsuarioDAO bd = new UsuarioDAO();
        bd.getEscrever(this);
        bd.delete(usuario);
        PerfilDAO bd1 = new PerfilDAO();
        bd1.getEscrever(this);
        bd1.deletePerfisUsuario(usuario.getId());
        mensagemApagada = Toast.makeText(getApplicationContext(), R.string.contaDeletada, Toast.LENGTH_SHORT);
        mensagemApagada.show();
        startActivity(new Intent(ApagarContaAct.this, LoginAct.class));
    }


}
