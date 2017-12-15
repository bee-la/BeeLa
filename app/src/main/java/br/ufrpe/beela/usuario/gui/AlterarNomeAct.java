package br.ufrpe.beela.usuario.gui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.usuario.dao.UsuarioDAO;
import br.ufrpe.beela.usuario.dominio.Usuario;
import br.ufrpe.beela.usuario.negocio.UsuarioService;

public class AlterarNomeAct extends AppCompatActivity {
    private Usuario usuario = LoginAct.getUsuario();
    private UsuarioService usuarioValido= new UsuarioService();

    private TextView alterarNomeText3;
    private Button alterarNomeButton11;

    private String alterarNome;
    private EditText alterarNomeEditText3;
    private UsuarioService usuarioService = new UsuarioService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_nome);

        alterarNomeEditText3=(EditText)findViewById(R.id.editText3);

//------------------------------------------Mudar Fonte-------------------------------------------
        alterarNomeText3 = (TextView) findViewById(R.id.editText3);
        Typeface fonte1 = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        alterarNomeText3.setTypeface(fonte1);
        alterarNome=alterarNomeEditText3.getText().toString().trim();

//-----------------------------------Validação clique botão Alterar Nome--------------------------------
        alterarNomeButton11 = (Button) findViewById(R.id.button11);
        alterarNomeButton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCliqueAlterarNome();
            }
        });
    }

    public void validarCliqueAlterarNome(){
        if(ehValidoAlterarNome()) {
            usuarioService.alterarSucessoNome(usuario,alterarNome,this);
            startActivity(new Intent(AlterarNomeAct.this, HomeAct.class));
            Toast Sucesso;
            Sucesso = Toast.makeText(getApplicationContext(), "Nome Alterado", Toast.LENGTH_SHORT);
            Sucesso.show();
        }}
//-------------------------------------------------------------------------------------------------------

    //-----------------------------------Validação do campo Nome-----------------------------------------------
    public boolean ehValidoAlterarNome() {
        boolean valido = true;
        if (usuarioService.validarCamposVazio(alterarNome)) {
            alterarNomeEditText3.setError(getString(R.string.campoVazio));
            valido = false;}
        else if(usuarioService.validarNomeIgual(alterarNome,usuario)){
            alterarNomeEditText3.setError("Nome Iguais");
            valido = false;}
        return valido;
    }
}
