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
    private TextView legendaPrincipal;
    private Button botaoConfirmar;

    public static ArrayList getListaEsporte() {  return listaEsporte; }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas_esporte);

        alterarFonte();
        adcCheckBoxEsp();
        setBotaoConfirmar();

    }
    private void alterarFonte() {

        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        legendaPrincipal = (TextView) findViewById(R.id.textView13);
        legendaPrincipal.setTypeface(fonte);
    }
    private void adcCheckBoxEsp() {

        checkBoxesEsportes.add((CheckBox) findViewById(R.id.checkBox2));
        checkBoxesEsportes.add((CheckBox) findViewById(R.id.checkBox3));
        checkBoxesEsportes.add((CheckBox) findViewById(R.id.checkBox4));
        checkBoxesEsportes.add((CheckBox) findViewById(R.id.checkBox5));
    }
    private void setBotaoConfirmar(){

        botaoConfirmar = (Button) findViewById(R.id.button13);
        botaoConfirmar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                adcEsporte();
                telaPerfilPrioridade();
            }
        });
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
    }

    public void adcEsporte(){
        for (CheckBox x : checkBoxesEsportes) {
            if (x.isChecked()) {
                PerfilEsporte esporte = new PerfilEsporte();
                esporte.setNome((String) x.getText());
                esporte.setId_usuario(usuario.getId_Usuario());
                listaEsporte.add(esporte);
            }
        }
        usuario.setEsporte(listaEsporte);
    }

    public ArrayList<PerfilEsporte> getEsporte(){
        return listaEsporte;
    }

    public void telaPerfilPrioridade(){
        startActivity(new Intent(PerguntasEsporteAct.this, PerfilPrioridadeAct.class));
        finish();
    }

}
