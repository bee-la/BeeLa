package br.ufrpe.beela.perfil.gui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.beela.perfil.dominio.PerfilMusica;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.usuario.gui.LoginAct;
import br.ufrpe.beela.gui.R;

public class PerguntasMusicaAct extends AppCompatActivity {
    private PerfilUsuario usuario = LoginAct.getPessoa().getPerfil();
    private TextView fonteTextView9;
    private Button btconfirmar;

    private List<CheckBox> checkBoxesMusicas = new ArrayList<>();


    private static ArrayList<PerfilMusica> listaMusica = new ArrayList();


   // public static ArrayList getListaMusica() {  return listaMusica; }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas_musica);

        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");

        fonteTextView9 = (TextView) findViewById(R.id.textView9);
        fonteTextView9.setTypeface(fonte);

        CheckBox rock = findViewById(R.id.checkBoxRock);
        CheckBox reggae = findViewById(R.id.checkboxReggae);
        CheckBox rap = findViewById(R.id.checkBoxRap);
        CheckBox samba = findViewById(R.id.checkBoxSamba);

        checkBoxesMusicas.add(rock);
        checkBoxesMusicas.add(reggae);
        checkBoxesMusicas.add(rap);
        checkBoxesMusicas.add(samba);

        btconfirmar = (Button) findViewById(R.id.buttonConfirmar);
        btconfirmar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                adcmusicas();
                alterarTelaPerfil();
            }

        });
    }




    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();
    }

        public void adcmusicas(){

            ArrayList<PerfilMusica> listaMusica = new ArrayList<PerfilMusica>();
            for (CheckBox x : checkBoxesMusicas) {
                if (x.isChecked()) {
                    PerfilMusica musica = new PerfilMusica();
                    musica.setId_usuario(usuario.getId_Usuario());
                    musica.setNome(x.getText().toString());
        //            musica.setNome_perfil(usuario.getNome());
                    listaMusica.add(musica);
                }
            }
            usuario.setMusica(listaMusica);
        }





    public void alterarTelaPerfil(){
//TODO      Na tela de PerfilAct falta aparecer o perfil que foi adicionado.
        adcmusicas();
        startActivity(new Intent(PerguntasMusicaAct.this, PerguntasComidasAct.class));

    }

}
