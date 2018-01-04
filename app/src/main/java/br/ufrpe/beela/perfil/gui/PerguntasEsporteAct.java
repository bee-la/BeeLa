package br.ufrpe.beela.perfil.gui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.perfil.dominio.PerfilEsporte;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.usuario.gui.LoginAct;

public class PerguntasEsporteAct extends AppCompatActivity {

    private PerfilUsuario usuario = LoginAct.getPessoa().getPerfil();
    private List<CheckBox> checkBoxesEsportes = new ArrayList<>();
    private static ArrayList<PerfilEsporte> listaEsporte = new ArrayList<PerfilEsporte>();
    private TextView fonteTextView13;
    private Button button13Confirmar;

    public static ArrayList getListaEsporte() {  return listaEsporte; }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas_esporte);

        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");

        fonteTextView13 = (TextView) findViewById(R.id.textView13);
        fonteTextView13.setTypeface(fonte);

        CheckBox volei = findViewById(R.id.checkBox2);
        CheckBox futebol = findViewById(R.id.checkBox3);
        CheckBox ciclismo = findViewById(R.id.checkBox4);
        CheckBox caminhada = findViewById(R.id.checkBox5);

        checkBoxesEsportes.add(volei);
        checkBoxesEsportes.add(futebol);
        checkBoxesEsportes.add(ciclismo);
        checkBoxesEsportes.add(caminhada);

        button13Confirmar = (Button) findViewById(R.id.button13);
        button13Confirmar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                adcEsporte();
                telaNomePerfil();
            }

        });

    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
    }

    public void adcEsporte(){

//        ArrayList<PerfilEsporte>listaComida = new ArrayList<PerfilEsporte>();
        for (CheckBox x : checkBoxesEsportes) {
            if (x.isChecked()) {
                PerfilEsporte esporte = new PerfilEsporte();
                esporte.setNome(x.getText().toString());
                esporte.setId_usuario(usuario.getId_Usuario());
                listaEsporte.add(esporte);
            }
        }
        usuario.setEsporte(listaEsporte);
    }

    public void telaNomePerfil(){
        adcEsporte();
        startActivity(new Intent(PerguntasEsporteAct.this, NomePerfilAct.class));
        finish();
    }

    public void fecharTela(){
        finish();
    }
}
