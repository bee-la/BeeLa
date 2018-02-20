package br.ufrpe.beela.cachorro.gui;

import android.content.DialogInterface;
import android.content.Intent;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import br.ufrpe.beela.cachorro.dominio.Cachorro;
import br.ufrpe.beela.cachorro.negocio.CachorroService;
import br.ufrpe.beela.cachorro.negocio.ListViewNomeCachorro;
import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.pessoa.dominio.Pessoa;
import br.ufrpe.beela.usuario.gui.LoginAct;

public class MeusCachorrosAct extends AppCompatActivity {
    private ListView listaCachorros;
    private ImageButton temCachorro;
    private Button editarCachorro;
    private ImageButton apagarCachorro;
    private EditText campoAlterarNome;
    private String Novonome;
    private Toast nomeAlterado;

    private CachorroService cachorroService = new CachorroService();
    private static ArrayList<Cachorro> listaTodosCachorros = new ArrayList<Cachorro>();
    private Pessoa pessoa = LoginAct.getPessoa();


   /** public ArrayList<Cachorro> getListaCachorros2 (){
        return listaCachorros2;

    }**/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_cachorros);
        botaoAdcCachorro();
        botaoApagarCachorro();
        botaoEditarCachorro();
        setListViewCachorro();

        setListaTodosCachorro(cachorroService.getCachorro(MeusCachorrosAct.this, pessoa.getId()));
    }

    public void botaoEditarCachorro(){
        editarCachorro = (Button) findViewById(R.id.buttonEditarAnimal);
        editarCachorro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAlterarNome();

            }
        });
    }
    private void verificarListViewAlterar() {
        try {
            if (listaCachorros != null) {
                Adapter adapter = (Adapter) listaCachorros.getAdapter();
                for (int i = 0; i < adapter.getCount(); i++) {
                    Cachorro cachorro = (Cachorro) adapter.getItem(i);
                    if (cachorro.isSelecionado()) {
                        verificarNome(cachorro.getNome());

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setAlterarNome() {
                AlertDialog.Builder alertaNome = new AlertDialog.Builder(MeusCachorrosAct.this);
                View ViewAltrNome = getLayoutInflater().inflate(R.layout.activity_alterar_nome_cachorro, null);
                campoAlterarNome = (EditText) ViewAltrNome.findViewById(R.id.editTextAlterarCachorro);
                Button botaoAlterarNome = (Button)ViewAltrNome.findViewById(R.id.buttonAlterarCachorro);

                botaoAlterarNome.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        verificarListViewAlterar();
                    }
                });

                alertaNome.setView(ViewAltrNome);
                AlertDialog dialog2 = alertaNome.create();
                dialog2.show();

    }

    private void verificarNome(String nomeAntigo) {
        Novonome = campoAlterarNome.getText().toString().trim();
        if (!Novonome.isEmpty()) {
            cachorroService.mudarNome(pessoa.getId(), Novonome,nomeAntigo, this);
            campoAlterarNome.setText("");
            nomeAlterado = Toast.makeText(getApplicationContext(), R.string.nomeAlterado, Toast.LENGTH_SHORT);
            nomeAlterado.show();
        }
    }

    public void botaoApagarCachorro(){
        apagarCachorro = (ImageButton) findViewById(R.id.buttonApagarAnimal);
        apagarCachorro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarListViewExcluir();
            }
        });
    }

    public void botaoAdcCachorro(){
        temCachorro = (ImageButton) findViewById(R.id.buttonInserirAnimal);
        temCachorro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MeusCachorrosAct.this, AdcCachorroAct.class));

            }
        });
    }
    public void setListViewCachorro(){
        listaCachorros = (ListView) findViewById(R.id.listViewAnimais);
        ListViewNomeCachorro lista = new ListViewNomeCachorro(MeusCachorrosAct.this);
        listaCachorros.setAdapter(lista);

    }


    private void verificarListViewExcluir() {
        try {
            if (listaCachorros != null) {
                Adapter adapter = (Adapter) listaCachorros.getAdapter();
                for (int i = 0; i < adapter.getCount(); i++) {
                    Cachorro cachorro = (Cachorro) adapter.getItem(i);
                    if (cachorro.isSelecionado()) {
                        excluirCahorro(cachorro.getNome());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void excluirCahorro(final String nomeCachorro) {
        final CharSequence[] escolha = {getString(R.string.sim), getString(R.string.nao)};
        AlertDialog.Builder alerta = new AlertDialog.Builder(MeusCachorrosAct.this);
        alerta.setItems(escolha, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String opcao = (String) escolha[i];
                if (opcao.equals(getString(R.string.sim))) {
                    cachorroService.excluir(pessoa.getId(), nomeCachorro, MeusCachorrosAct.this);
                    Toast.makeText(getApplicationContext(), R.string.excluidoSucesso, Toast.LENGTH_LONG).show();
                    setListViewCachorro();
                } else if (opcao.equals(getString(R.string.nao))) {
                    dialogInterface.cancel();
                }
            }
        });
        alerta.setTitle(getString(R.string.verificarExcluirPerfil) + " " + nomeCachorro + getString(R.string.sinalInterrogacao));
        AlertDialog aviso = alerta.create();
        aviso.show();

    }

    public static ArrayList<Cachorro> getListaTodosCachorro() {
        return listaTodosCachorros;
    }

    public static void setListaTodosCachorro(ArrayList<Cachorro> lista) {
        listaTodosCachorros = lista;
    }




}
