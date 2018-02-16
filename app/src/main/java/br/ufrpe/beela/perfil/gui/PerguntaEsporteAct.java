package br.ufrpe.beela.perfil.gui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import java.io.Serializable;
import java.util.ArrayList;
import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.perfil.dominio.PerfilEsporte;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;

public class PerguntaEsporteAct extends AppCompatActivity implements Serializable{

    private PerfilUsuario perfilUsuario = new PerfilUsuario();
    private ArrayList<CheckBox> checkBoxesEsportes = new ArrayList<>();
    private ArrayList<PerfilEsporte> listaEsporte = new ArrayList<PerfilEsporte>();

    private TextView pergunta;
    private Button botaoConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas_esporte);
        Bundle bundle = getIntent().getExtras();
        perfilUsuario = (PerfilUsuario) bundle.getSerializable("perfilUsuario");

        alterarFonte();
        adcCheckBoxEsp();
        clicarBotaoConfirmar();

    }

    private void alterarFonte() {
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        pergunta = (TextView) findViewById(R.id.textView13);
        pergunta.setTypeface(fonte);
    }

    private void adcCheckBoxEsp() {
        checkBoxesEsportes.add((CheckBox) findViewById(R.id.checkBox2));
        checkBoxesEsportes.add((CheckBox) findViewById(R.id.checkBox3));
        checkBoxesEsportes.add((CheckBox) findViewById(R.id.checkBox4));
        checkBoxesEsportes.add((CheckBox) findViewById(R.id.checkBox5));
    }

    private void clicarBotaoConfirmar() {

        botaoConfirmar = (Button) findViewById(R.id.button13);
        botaoConfirmar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                adcListaEsporte(checkBoxesEsportes, listaEsporte, perfilUsuario);
                irTelaPerguntaLugarAct();

            }
        });
    }

    public void adcListaEsporte(ArrayList<CheckBox> checkBoxesEsportes, ArrayList<PerfilEsporte> listaEsporte, PerfilUsuario perfilUsuario) {
        for (CheckBox x : checkBoxesEsportes) {
            if (x.isChecked()) {
                PerfilEsporte esporte = new PerfilEsporte();
                esporte.setNome((String) x.getText());
                listaEsporte.add(esporte);
            }
        }
        perfilUsuario.setEsporte(listaEsporte);
    }


    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
    }

    public void irTelaPerguntaLugarAct() {
        Intent intent = new Intent(PerguntaEsporteAct.this, PerguntaLugarVariadoAct.class);
        intent.putExtra("perfilUsuario", (Serializable) perfilUsuario);
        startActivity(intent);
        finish();
    }
}
