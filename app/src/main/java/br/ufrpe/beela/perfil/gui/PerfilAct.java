package br.ufrpe.beela.perfil.gui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.perfil.negocio.TextoListView;
import br.ufrpe.beela.usuario.gui.LoginAct;
import br.ufrpe.beela.gui.R;

public class PerfilAct extends AppCompatActivity {
    private ImageButton adicionarPerfil;
    private ListView listaNomes;
    private TextView TextoButton14;

    private PerfilUsuario perfilUsuario = LoginAct.getPessoa().getPerfil();
    private ImageButton adicionarPerfilTrocarTela;
    private static ArrayList<PerfilUsuario> perfilUsuarioArrayList = new ArrayList<>();
    public static ArrayList<PerfilUsuario> getLista(){return perfilUsuarioArrayList;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        MontarPerfis();
        listaNomes=(ListView)findViewById(R.id.listView);
        TextoListView mudancaCor=new TextoListView(this, perfis());
        listaNomes.setAdapter(mudancaCor);

//-------------------------------------Trocar tela--------------------------------------------
        adicionarPerfil = (ImageButton) findViewById(R.id.imageButton4);
        fonteBotaoComecar();
//        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
//        nomeTextView = findViewById(R.id.textViewPerfil1);
//        nomeTextView.setTypeface(fonte);
        adicionarPerfilTrocarTela = (ImageButton) findViewById(R.id.imageButton4);
        adicionarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verificarPerfis()){
                adicionarPerfilTrocarTela();}
                else{
                    Toast Erro;
                    Erro =Toast.makeText(getApplicationContext(), "Maximo é 3 Perfis", Toast.LENGTH_SHORT);
                    Erro.show(); }
            }});
    }
    private void adicionarPerfilTrocarTela() {
        startActivity(new Intent(PerfilAct.this, PerguntasMusicaAct.class));
        finish();
        MontarPerfis();
        TextoListView mudancaCor=new TextoListView(this, perfis());
    }


//    public void verifarNomeTextView(){
//        Intent intent =getIntent();
//        if (intent!=null){
//            Bundle parametros=intent.getExtras();
//            if (parametros!=null){
//                String nomeTexto = parametros.getString("chave");
//                nomeTextView.setText(nomeTexto);
//            }
//        }
//    }
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

//    public void fecharTela(){
//        finish();
//        PerfilDAO bd = new PerfilDAO();
//        bd.getLer(this);
//        perfilUsuarioArrayList=bd.sqlGetPerfil(perfilUsuario.getId_Usuario());
//        if (perfilUsuarioArrayList.size()==0){nomeTextView.setText("Sem Perfil");}
//        else{nomeTextView.setText(perfilUsuarioArrayList.get(0).getNome());}
//    }
    private boolean verificarPerfis(){
        if(perfilUsuarioArrayList.size()<=2){return true;}
        else{return false;}
    }
}
