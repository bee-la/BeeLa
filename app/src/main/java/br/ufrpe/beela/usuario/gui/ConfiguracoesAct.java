package br.ufrpe.beela.usuario.gui;

import android.content.Intent;
import android.graphics.Typeface;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.pessoa.dominio.Pessoa;
import br.ufrpe.beela.usuario.dominio.Usuario;
import br.ufrpe.beela.usuario.negocio.Criptografia;
import br.ufrpe.beela.usuario.negocio.UsuarioService;

public class ConfiguracoesAct extends AppCompatActivity {
    private TextView alterar, apagarConta, alterarNome, alterarSenha, sair, carregarFoto, nomeUser;
    private Pessoa pessoa = new LoginAct().getPessoa();
    private ArrayList<TextView> textoCampos = new ArrayList<TextView>();
    private static final int RESULT_LOAD_IMAGE = 9002;
    private String senhaAtual, novaSenha, repetirSenha, nome;
    private EditText campoSenha, campoNovaSenha, campoRepetirSenha, campoAlterarNome;
    private Toast senhaAlterada, erro, nomeAlterado;
    private Usuario usuario = LoginAct.getPessoa().getUsuario();
    private UsuarioService usuarioService = new UsuarioService();
    private String nomeUsuario = LoginAct.getPessoa().getNome();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        alterar = (TextView) findViewById(R.id.textView17);
        alterarNome = (TextView) findViewById(R.id.textView15);
        alterarSenha = (TextView) findViewById(R.id.textView16);
        sair = (TextView) findViewById(R.id.textView12);
        apagarConta = (TextView) findViewById(R.id.textView18);
        nomeUser = (TextView) findViewById(R.id.textView3);
        nomeUser.setText(nomeUsuario);

        alterarFonte();
        carregarFotoGaleria();
        setAlterarNome();
        setAlterarSenha();
        irApagarConta();
        sair();
    }

    private void alterarFonte() {
        adcListaTexto();
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        for (TextView txtView : textoCampos) {
            txtView.setTypeface(fonte);
        }
    }


    private void adcListaTexto() {
        textoCampos.add(alterar);
        textoCampos.add(alterarNome);
        textoCampos.add(alterarSenha);
        textoCampos.add(apagarConta);
        textoCampos.add(sair);
    }

    private void carregarFotoGaleria() {
        carregarFoto = findViewById(R.id.btUpload);
        carregarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
            }
        });
    }

    private void setAlterarNome() {
        alterarNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertaNome = new AlertDialog.Builder(ConfiguracoesAct.this);
                View ViewAltrNome = getLayoutInflater().inflate(R.layout.activity_alterar_nome, null);
                campoAlterarNome = (EditText) ViewAltrNome.findViewById(R.id.editText3);
                Button botaoAlterarNome = (Button)ViewAltrNome.findViewById(R.id.button11);

                botaoAlterarNome.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        verificarNome();
                    }
                });

                alertaNome.setView(ViewAltrNome);
                AlertDialog dialog2 = alertaNome.create();
                dialog2.show();
            }
        });
    }


    private void verificarNome() {
        nome = campoAlterarNome.getText().toString().trim();
        if (!nome.isEmpty()) {
            usuarioService.alterarNome(pessoa, nome, this);
            campoAlterarNome.setText("");
            nomeUser.setText(nome);
            nomeAlterado = Toast.makeText(getApplicationContext(), R.string.nomeAlterado, Toast.LENGTH_SHORT);
            nomeAlterado.show();
        }
    }

    private void setAlterarSenha() {
        alterarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertaAltrSenha = new AlertDialog.Builder(ConfiguracoesAct.this);
                View ViewAltrSenha = getLayoutInflater().inflate(R.layout.activity_alterar_senha, null);
                campoSenha = (EditText) ViewAltrSenha.findViewById(R.id.editText7);
                campoNovaSenha = (EditText) ViewAltrSenha.findViewById(R.id.editText8);
                campoRepetirSenha = (EditText) ViewAltrSenha.findViewById(R.id.editText9);
                Button botaoAltrSenha = (Button) ViewAltrSenha.findViewById(R.id.button10);

                botaoAltrSenha.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        verificarSenhaAtual();
                    }
                });

                alertaAltrSenha.setView(ViewAltrSenha);
                AlertDialog dialog = alertaAltrSenha.create();
                dialog.show();
            }
        });
    }

    public void verificarSenhaAtual() {
        senhaAtual = campoSenha.getText().toString().trim();
        if (Criptografia.criptografar(senhaAtual).equals(usuario.getSenha())) {
            novaSenha = campoNovaSenha.getText().toString().trim();
            repetirSenha = campoRepetirSenha.getText().toString().trim();

            if (validarCampos()) {
                alterarSenha();
            }
        } else {
            erro = Toast.makeText(getApplicationContext(), R.string.senhaAtualDiferente, Toast.LENGTH_SHORT);
            erro.show();
        }
    }

    public void alterarSenha() {
        usuarioService.alterarSenha(usuario,Criptografia.criptografar(novaSenha),this);
        usuario.setSenha(Criptografia.criptografar(novaSenha));
        limparCampos();
        senhaAlterada = Toast.makeText(getApplicationContext(), R.string.senhaAlterada, Toast.LENGTH_SHORT);
        senhaAlterada.show();
    }

    public boolean validarCampos() {
        if (senhaAtual.isEmpty()) {
            campoSenha.setError(getString(R.string.campoVazio));
            return false;
        }

        if (novaSenha.length() < 6 || novaSenha.isEmpty()) {
            campoNovaSenha.setError(getString(R.string.senhaInvalida));
            return false;
        }

        if (!repetirSenha.equals(novaSenha)) {
            campoRepetirSenha.setError(getString(R.string.senhasDiferentes));
            return false;
        } else {
            return true;
        }
    }

    private void limparCampos() {
        campoSenha.setText("");
        campoNovaSenha.setText("");
        campoRepetirSenha.setText("");
    }

    private void irApagarConta() {
        apagarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConfiguracoesAct.this, ApagarContaAct.class));
                finish();
            }
        });
    }

    private void sair() {
        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConfiguracoesAct.this, LoginAct.class));
                finish();
            }
        });
    }



}



