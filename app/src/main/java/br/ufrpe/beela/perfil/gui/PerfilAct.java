package br.ufrpe.beela.perfil.gui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.usuario.gui.LoginAct;
import br.ufrpe.beela.gui.R;

public class PerfilAct extends AppCompatActivity {
    private PerfilUsuario perfilUsuario = LoginAct.getPessoa().getPerfil();
    private  TextView nomeTextView;
    private ImageButton adicionarPerfilTrocarTela;
    private static ArrayList<PerfilUsuario> perfilUsuarioArrayList = new ArrayList<>();
    public static ArrayList<PerfilUsuario> getLista(){return perfilUsuarioArrayList;}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
//-------------------------------------Trocar tela--------------------------------------------
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

        adicionarPerfilTrocarTela.setOnClickListener(new View.OnClickListener() {
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
        MontarPerfis();
    }
    public void verifarNomeTextView(){
        Intent intent =getIntent();
        if (intent!=null){
            Bundle parametros=intent.getExtras();
            if (parametros!=null){
                String nomeTexto = parametros.getString("chave");
                nomeTextView.setText(nomeTexto);
            }
        }
    }
    private void MontarPerfis(){

        PerfilDAO bd = new PerfilDAO();
        bd.getLer(this);
        perfilUsuarioArrayList=bd.sqlGetPerfil(perfilUsuario.getId_Usuario());
        if (perfilUsuarioArrayList.size()==0){nomeTextView.setText("Sem Perfil");}
        else{nomeTextView.setText(perfilUsuarioArrayList.get(0).getNome());}
    }
    private boolean verificarPerfis(){
        if(perfilUsuarioArrayList.size()<=2){return true;}
        else{return false;}
    }
}
