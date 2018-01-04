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
<<<<<<< HEAD
import br.ufrpe.beela.perfil.negocio.TextoListView;
=======
>>>>>>> desenvolvedor2
import br.ufrpe.beela.usuario.gui.LoginAct;
import br.ufrpe.beela.gui.R;

public class PerfilAct extends AppCompatActivity {
<<<<<<< HEAD
    private PerfilUsuario usuario = LoginAct.getPessoa().getPerfil();
    private ImageButton adicionarPerfil;
    private ListView listaNomes;
    private TextView TextoButton14;

=======
    private PerfilUsuario perfilUsuario = LoginAct.getPessoa().getPerfil();
    private  TextView nomeTextView;
    private ImageButton adicionarPerfilTrocarTela;
    private static ArrayList<PerfilUsuario> perfilUsuarioArrayList = new ArrayList<>();
    public static ArrayList<PerfilUsuario> getLista(){return perfilUsuarioArrayList;}
>>>>>>> desenvolvedor2
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        listaNomes=(ListView)findViewById(R.id.listView);
        TextoListView mudancaCor=new TextoListView(this, perfis());
        listaNomes.setAdapter(mudancaCor);

//-------------------------------------Trocar tela--------------------------------------------
<<<<<<< HEAD
        adicionarPerfil = (ImageButton) findViewById(R.id.imageButton4);
        fonteBotaoComecar();

//        if(usuario.getNome()==null){
//            MontarPerfis();}//TODO botei agora para setar os perfis do usuario
            //nomeTextView.setText("Sem Perfil");}
//        else{
//            verifarNomeTextView();}
=======
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        nomeTextView = findViewById(R.id.textViewPerfil1);
        MontarPerfis();
        nomeTextView.setTypeface(fonte);
        adicionarPerfilTrocarTela = (ImageButton) findViewById(R.id.imageButton4);
        //if(perfilUsuario.getNome()==null){
         //}//TODO botei agora para setar os perfis do perfilUsuario
            //nomeTextView.setText("Sem Perfil");}
        //else{
        //    verifarNomeTextView();}
>>>>>>> desenvolvedor2

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
<<<<<<< HEAD
        finish();
=======
        MontarPerfis();
>>>>>>> desenvolvedor2
    }

<<<<<<< HEAD

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
//    private void MontarPerfis(){
//
//        ArrayList<PerfilUsuario> perfilUsuarioArrayList = new ArrayList<PerfilUsuario>();
//        PerfilDAO bd = new PerfilDAO(this,"R");
//        perfilUsuarioArrayList=bd.sqlGetPerfil(usuario.getId_Usuario());
//        if (perfilUsuarioArrayList.size()==0){
//        nomeTextView.setText("Sem Perfil");}
//        else{
//            for(PerfilUsuario i: perfilUsuarioArrayList){
//                nomeTextView.setText(i.getNome());}
//        }
//    }

    private ArrayList<String> perfis(){
        PerfilDAO bd = new PerfilDAO(this,"R");
        ArrayList<String> arrayPerfis=new ArrayList<String>();
        ArrayList<PerfilUsuario> perfilUsuarioArrayList = new ArrayList<PerfilUsuario>();
        perfilUsuarioArrayList=bd.sqlGetPerfil(usuario.getId_Usuario());

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

    public void fecharTela(){
        finish();
=======
        PerfilDAO bd = new PerfilDAO();
        bd.getLer(this);
        perfilUsuarioArrayList=bd.sqlGetPerfil(perfilUsuario.getId_Usuario());
        if (perfilUsuarioArrayList.size()==0){nomeTextView.setText("Sem Perfil");}
        else{nomeTextView.setText(perfilUsuarioArrayList.get(0).getNome());}
    }
    private boolean verificarPerfis(){
        if(perfilUsuarioArrayList.size()<=2){return true;}
        else{return false;}
>>>>>>> desenvolvedor2
    }
}
