package br.ufrpe.beela.perfil.gui;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.perfil.negocio.ListViewNomePerfil;
//import br.ufrpe.beela.perfil.negocio.TextoListView;
import br.ufrpe.beela.usuario.gui.LoginAct;
import br.ufrpe.beela.gui.R;

public class PerfilAct extends AppCompatActivity {
    private ImageButton adicionarPerfil;
    private ListView listViewPerfis;
    private TextView TextoButton14;
    private ArrayList<String> perfilExcluir=new ArrayList<String>();

    private PerfilUsuario perfilUsuario = LoginAct.getPessoa().getPerfil();
    private ImageButton adicionarPerfilTrocarTela;
    private ImageButton apagarImageButton6;
    public static ArrayList<PerfilUsuario> perfilUsuarioArrayList = new ArrayList<>();
    public static ArrayList<PerfilUsuario> getLista(){return perfilUsuarioArrayList;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        MontarPerfis();
        listViewPerfis = (ListView) findViewById(R.id.listView);
        setListView();
//        valorSelecionado();


//        listaNomes=(ListViewNomePerfil)findViewById(R.id.listView);
//        TextoListView mudancaCor=new TextoListView(this, perfis());
//        listaNomes.setAdapter(mudancaCor);
//        clickLista();

        adicionarPerfil = (ImageButton) findViewById(R.id.imageButton4);
        apagarImageButton6 = (ImageButton) findViewById(R.id.imageButton6);

        fonteBotaoComecar();
        checkBoxSelecionado();
//        setListView();
//        finish();

        adicionarPerfilTrocarTela = (ImageButton) findViewById(R.id.imageButton4);
        adicionarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verificarPerfis()) {
                    adicionarPerfilTrocarTela();
                } else {
                    Toast Erro;
                    Erro = Toast.makeText(getApplicationContext(), "Maximo é 3 Perfis", Toast.LENGTH_SHORT);
                    Erro.show();    }   }   });
    }

    private void checkBoxSelecionado() {
        apagarImageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(listViewPerfis!=null){
                        Adapter adapter= (Adapter) listViewPerfis.getAdapter();
                        for(int i=0; i<adapter.getCount(); i++){
                            PerfilUsuario perfil=(PerfilUsuario) adapter.getItem(i);
                            if(perfil.isSelecionado()) {
                                excluirPerfil(perfil.getNome());    }   }   }   }
                catch (Exception e){
                    e.printStackTrace();    }   }   });
    }

    private void adicionarPerfilTrocarTela() {
        startActivity(new Intent(PerfilAct.this, PerguntasMusicaAct.class));
        finish();
        MontarPerfis();}

    private void MontarPerfis(){

        PerfilDAO bd = new PerfilDAO();
        bd.getLer(this);
        perfilUsuarioArrayList=bd.sqlGetPerfil(perfilUsuario.getId_Usuario());
    }

    private ArrayList<String> perfis(){
        ArrayList<String> arrayPerfis=new ArrayList<String>();
        for(PerfilUsuario i: perfilUsuarioArrayList) {
            arrayPerfis.add(i.getNome());
        }
        return arrayPerfis;
    }

    public void fonteBotaoComecar(){
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        TextoButton14 =(Button) findViewById(R.id.button14);
        TextoButton14.setTypeface(fonte);
    }

    private boolean verificarPerfis(){
        if(perfilUsuarioArrayList.size()<=2){return true;}
        else{return false;}
    }

    private void setListView(){
        MontarPerfis();
        ListViewNomePerfil lista=new ListViewNomePerfil(PerfilAct.this);
        listViewPerfis.setAdapter(lista);

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
                    Toast.makeText(getApplicationContext(), "Teste Exclusão", Toast.LENGTH_LONG).show();
//                    excluirDoBanco(nomePerfil);
                }
                else if(opcao.equals(getString(R.string.nao))){
                    dialogInterface.cancel();   }   }   });
        alerta.setTitle("Excluir perfil "+ nomePerfil+ "?");
        AlertDialog aviso=alerta.create();
        aviso.show();
    }

    private void excluirDoBanco(String nomePerfil){
        PerfilDAO bd = new PerfilDAO();
        bd.getLer(this);
        bd.deletePerfilUsuario(perfilUsuario, nomePerfil);
    }
}
