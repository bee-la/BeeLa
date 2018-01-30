package br.ufrpe.beela.perfil.gui;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.gui.EscolhaProgramaAct;
import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.perfil.negocio.ListViewNomePerfil;
import br.ufrpe.beela.perfil.negocio.PerfilService;
import br.ufrpe.beela.usuario.dominio.Pessoa;
import br.ufrpe.beela.usuario.gui.LoginAct;


public class PerfilAct extends AppCompatActivity {
    private ImageButton botaoAdicionarPerfil, botaoApagar, botaoSalvar;
    private ListView listViewPerfis;
    private TextView fonteBotaoComecar;
    private Toast erro;
    private Button botaoComecar;
    private int contadorPerfil;
    private boolean verificador = false;

    private Pessoa pessoa = LoginAct.getPessoa();
    private PerfilUsuario perfilUsuario = LoginAct.getPessoa().getPerfilAtual();
    private PerfilService perfilService = new PerfilService();
    public static ArrayList<PerfilUsuario> perfilUsuarioArrayList = LoginAct.getPessoa().getPerfilUsuarioArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        montarPerfil();

        alterarFonte();
        setListView();
        alterarFonte();
        adicionarPerfil();
        irExcluirPerfil();
        irTelaEscolhaPrograma();
        salvarPerfilAtual();
    }

    public static ArrayList<PerfilUsuario> getListaPerfis() {
        return perfilUsuarioArrayList;
    }

    private void alterarFonte() {
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        fonteBotaoComecar = (Button) findViewById(R.id.button14);
        fonteBotaoComecar.setTypeface(fonte);
    }

    private void adicionarPerfil() {
        botaoAdicionarPerfil = (ImageButton) findViewById(R.id.imageButton4);
        botaoAdicionarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {irTelaPerguntasMusica();}});
    }

    private void irTelaPerguntasMusica() {
        startActivity(new Intent(PerfilAct.this, PerguntaMusicaAct.class));
        finish();
    }

    private void irExcluirPerfil() {
        botaoApagar = (ImageButton) findViewById(R.id.imageButton6);
        botaoApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarListViewExcluir();
            }
        });
    }

    private void verificarListViewExcluir() {
        try {
            if (listViewPerfis != null) {
                Adapter adapter = (Adapter) listViewPerfis.getAdapter();
                for (int i = 0; i < adapter.getCount(); i++) {
                    PerfilUsuario perfil = (PerfilUsuario) adapter.getItem(i);
                    if (perfil.isSelecionado()) {
                        excluirPerfil(perfil.getNome());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setListView(){
        montarPerfil();
        listViewPerfis = (ListView) findViewById(R.id.listView);
        ListViewNomePerfil lista = new ListViewNomePerfil(PerfilAct.this);
        listViewPerfis.setAdapter(lista);
    }

    private void montarPerfil() {
        pessoa.setPerfilUsuarioArrayList(perfilService.montarPerfis(pessoa, PerfilAct.this));
        perfilUsuarioArrayList = pessoa.getPerfilUsuarioArrayList();
        pessoa.setPerfilAtual(perfilService.montarPerfilFavorito(pessoa,this));
    }

    private void excluirPerfil(final String nomePerfil) {
        final CharSequence[] escolha = {getString(R.string.sim), getString(R.string.nao)};
        AlertDialog.Builder alerta = new AlertDialog.Builder(PerfilAct.this);
        alerta.setItems(escolha, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String opcao = (String) escolha[i];
                if (opcao.equals(getString(R.string.sim))) {
                    perfilService.excluirDoBanco(pessoa.getId(), nomePerfil, PerfilAct.this);
                    Toast.makeText(getApplicationContext(), R.string.excluidoSucesso, Toast.LENGTH_LONG).show();
                    setListView();
                } else if (opcao.equals(getString(R.string.nao))) {
                    dialogInterface.cancel();
                }
            }
        });
        alerta.setTitle(getString(R.string.verificarExcluirPerfil) + " " + nomePerfil + getString(R.string.sinalInterrogacao));
        AlertDialog aviso = alerta.create();
        aviso.show();
    }

    public void irTelaEscolhaPrograma() {
        botaoComecar = (Button) findViewById(R.id.button14);
        botaoComecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificador = false;
                setPerfilAtual();
                if (verificador) {
                    startActivity(new Intent(PerfilAct.this, EscolhaProgramaAct.class));
                }
            }
        });
    }

    public void salvarPerfilAtual() {
        botaoSalvar = (ImageButton) findViewById(R.id.imageButton9);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPerfilAtual();
            }
        });
    }

    public boolean verificaSelecionados() {
        Adapter adapter = (Adapter) listViewPerfis.getAdapter();
        PerfilUsuario perfilAtual = new PerfilUsuario();
        for (int i = 0; i < adapter.getCount(); i++) {
            PerfilUsuario perfil = (PerfilUsuario) adapter.getItem(i);
            if (perfil.isSelecionado()) {
                contadorPerfil += 1;
                perfilAtual = perfil;
            }
        }
        if (contadorPerfil == 1) {

            PerfilDAO bdc = new PerfilDAO();
            bdc.getLer(this);
            perfilAtual.setComida(bdc.getPerfilComida(perfilAtual,LoginAct.getPessoa().getId()));
            //
            PerfilDAO bde = new PerfilDAO();
            bde.getLer(this);
            perfilAtual.setEsporte(bde.getPerfilEsporte(perfilAtual,LoginAct.getPessoa().getId()));
            //
            PerfilDAO bdm = new PerfilDAO();
            bdm.getLer(this);
            perfilAtual.setMusica(bdm.getPerfilMusica(perfilAtual,LoginAct.getPessoa().getId()));
            //
            PerfilDAO bdl = new PerfilDAO();
            bdl.getLer(this);
            perfilAtual.setLugar(bdl.getPerfilParaLugar(perfilAtual,LoginAct.getPessoa().getId()));
            //
            LoginAct.getPessoa().setPerfilAtual(perfilAtual);
            perfilUsuario = LoginAct.getPessoa().getPerfilAtual();
//
            return true;
        } else {
            return false;
        }
    }

    public void setPerfilAtual() {
        try {
            if (listViewPerfis != null) {
                Adapter adapter = (Adapter) listViewPerfis.getAdapter();
                contadorPerfil = 0;
                if (!verificaSelecionados()) {
                    Toast.makeText(getApplicationContext(), R.string.selecionarPerfil, Toast.LENGTH_SHORT).show();
                } else {
                    verificador = true;
                    perfilService.adcPerfilFavoritoAtual(LoginAct.getPessoa().getId(), pessoa.getPerfilAtual().getNome(), this);
                    Toast.makeText(getApplicationContext(),getString(R.string.perfilAtual)+" "+pessoa.getPerfilAtual().getNome(), Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
