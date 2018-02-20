    package br.ufrpe.beela.usuario.gui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.pessoa.dominio.Pessoa;
import br.ufrpe.beela.usuario.dominio.Usuario;
import br.ufrpe.beela.usuario.negocio.UsuarioService;

public class ApagarContaAct extends AppCompatActivity {
    private Button botaoApagar;
    private TextView aviso1;
    private TextView aviso2;
    private Toast mensagemApagada;
    private UsuarioService usuarioService = new UsuarioService();
    private Pessoa pessoa = LoginAct.getPessoa();
    private Usuario usuario = LoginAct.getPessoa().getUsuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apagar_conta);

        botaoApagar = findViewById(R.id.button9);
        alterarFonte();
        clicarBotaoApagar();

    }

    private void alterarFonte() {
        aviso1 = findViewById(R.id.textView5);
        aviso2 = findViewById(R.id.textView6);
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        botaoApagar.setTypeface(fonte);
        aviso1.setTypeface(fonte);
        aviso2.setTypeface(fonte);
    }

    private void clicarBotaoApagar() {
        botaoApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletarConta();
            }
        });
    }

    public void deletarConta() {
        usuarioService.deletarUsuario(pessoa,usuario, this);
        mensagemApagada = Toast.makeText(getApplicationContext(), R.string.contaDeletada, Toast.LENGTH_SHORT);
        mensagemApagada.show();
        startActivity(new Intent(ApagarContaAct.this, LoginAct.class));
    }
}
