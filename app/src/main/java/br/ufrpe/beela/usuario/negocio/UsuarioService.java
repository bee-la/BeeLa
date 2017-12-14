package br.ufrpe.beela.usuario.negocio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import br.ufrpe.beela.gui.R;

/**
 * Created by vidal on 14/12/2017.
 */

public class UsuarioService extends AppCompatActivity {

    private String nomeValidar, celularValidar, emailValidar, senhaValidar, repetirSenhaValidar;
    private EditText editText4Nome, editText5Celular, editText6Email, editText10Senha, editText11RepetirSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);

        nomeValidar = editText4Nome.getText().toString().trim();
        celularValidar = editText5Celular.getText().toString().trim();
        emailValidar = editText6Email.getText().toString().trim();
        senhaValidar = editText10Senha.getText().toString().trim();
        repetirSenhaValidar = editText11RepetirSenha.getText().toString().trim();

    }

    public boolean validarCampos(){
        boolean valido=true;

        return valido;
    }

}
