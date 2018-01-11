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
import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.perfil.negocio.ListViewNomePerfil;
import br.ufrpe.beela.perfil.negocio.PerfilService;
import br.ufrpe.beela.usuario.dominio.Usuario;
import br.ufrpe.beela.usuario.gui.LoginAct;
import br.ufrpe.beela.gui.R;

public class PerfilAct extends AppCompatActivity {
    private ImageButton botaoAdicionarPerfil, botaoApagar;
    private ListView listViewPerfis;
    private TextView fonteBotaoComecar;
    private Toast erro;

    private PerfilUsuario perfilUsuario = LoginAct.getPessoa().getPerfil();
    private Usuario usuario=LoginAct.getUsuario();
    private PerfilService perfilService = new PerfilService();
    public static ArrayList<PerfilUsuario> perfilUsuarioArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        MontarPerfil();

        alterarFonte();
        setListView();
        alterarFonte();
        adicionarPerfil();
        irExcluirPerfil();
    }

    public static ArrayList<PerfilUsuario> getLista(){
        return perfilUsuarioArrayList;
    }

    private void alterarFonte(){
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        fonteBotaoComecar =(Button) findViewById(R.id.button14);
        fonteBotaoComecar.setTypeface(fonte);
    }

    private void adicionarPerfil(){
        botaoAdicionarPerfil = (ImageButton) findViewById(R.id.imageButton4);
        botaoAdicionarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (perfilUsuarioArrayList.size()<=2) {
                    irTelaPerguntasMusica();
                }
                else{
                    erro = Toast.makeText(getApplicationContext(), R.string.maximoPerfis, Toast.LENGTH_SHORT);
                    erro.show();
                }
            }
        });
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
    private void verificarListViewExcluir(){
        try{
            if(listViewPerfis!=null){
                Adapter adapter= (Adapter) listViewPerfis.getAdapter();
                for(int i=0; i<adapter.getCount(); i++){
                    PerfilUsuario perfil=(PerfilUsuario) adapter.getItem(i);
                    if(perfil.isSelecionado()) {
                        excluirPerfil(perfil.getNome());    }   }   }   }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setListView(){
        MontarPerfil();
        listViewPerfis = (ListView) findViewById(R.id.listView);
        ListViewNomePerfil lista = new ListViewNomePerfil(PerfilAct.this);
        listViewPerfis.setAdapter(lista);
    }
    private void MontarPerfil(){

        perfilUsuarioArrayList = perfilService.MontarPerfis(perfilUsuario,PerfilAct.this);
    }

    public ArrayList<PerfilUsuario> getPerfis(){
        return perfilUsuarioArrayList;
    }

    private void excluirPerfil(final String nomePerfil){
        final CharSequence[] escolha={getString(R.string.sim), getString(R.string.nao)};
        AlertDialog.Builder alerta= new AlertDialog.Builder(PerfilAct.this);
        alerta.setItems(escolha, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String opcao=(String)escolha[i];
                if (opcao.equals(getString(R.string.sim))){
                    perfilService.excluirDoBanco(usuario.getId(),nomePerfil,PerfilAct.this);
                    Toast.makeText(getApplicationContext(), R.string.excluidoSucesso, Toast.LENGTH_LONG).show();
                    setListView();
                }
                else if(opcao.equals(getString(R.string.nao))){
                    dialogInterface.cancel();   }   }   });
        alerta.setTitle(getString(R.string.verificarExcluirPerfil)+" "+ nomePerfil+ getString(R.string.sinalInterrogacao));
        AlertDialog aviso=alerta.create();
        aviso.show();
    }
}
