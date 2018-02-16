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
import br.ufrpe.beela.perfil.dominio.PerfilLugar;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;

public class PerguntaLugarVariadoAct extends AppCompatActivity implements Serializable{


    private PerfilUsuario perfilUsuario = new PerfilUsuario();
    private TextView pergunta;
    private Button botaoConfirmar;
    private ArrayList<CheckBox> checkBoxesLugares = new ArrayList<>();
    private ArrayList<PerfilLugar> listaLugar = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta_lugar);

        Bundle bundle = getIntent().getExtras();
        perfilUsuario = (PerfilUsuario) bundle.getSerializable("perfilUsuario");

        alterarFonte();
        adcCheckBoxCom();
        clicarBotaoConfirmar();
    }

    public void alterarFonte() {
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        pergunta = (TextView) findViewById(R.id.textView8);
        pergunta.setTypeface(fonte);

    }

    public void adcCheckBoxCom() {
        checkBoxesLugares.add((CheckBox) findViewById(R.id.checkboxPraia));
        checkBoxesLugares.add((CheckBox) findViewById(R.id.checkboxShopping));
        checkBoxesLugares.add((CheckBox) findViewById(R.id.checkBoxBarzinho));
        checkBoxesLugares.add((CheckBox) findViewById(R.id.checkBoxIgreja));
        checkBoxesLugares.add((CheckBox) findViewById(R.id.checkBoxZoologico));
        checkBoxesLugares.add((CheckBox) findViewById(R.id.checkBoxParqueDiv));
    }

    private void clicarBotaoConfirmar() {
        botaoConfirmar = (Button) findViewById(R.id.buttonConfirmarlugar);
        botaoConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adcListaLugares(checkBoxesLugares, listaLugar, perfilUsuario);
                irTelaNomePerfilAct();

            }
        });
    }

    public void adcListaLugares(ArrayList<CheckBox> checkBoxesLugares, ArrayList<PerfilLugar> listaLugar, PerfilUsuario perfilUsuario) {
        for (CheckBox x : checkBoxesLugares) {
            if (x.isChecked()) {
                PerfilLugar lugar = new PerfilLugar();
                lugar.setNome(x.getText().toString());
                listaLugar.add(lugar);
            }
        }
        perfilUsuario.setLugar(listaLugar);
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
    }


    public void irTelaNomePerfilAct() {
        Intent intent = new Intent(PerguntaLugarVariadoAct.this, NomePerfilAct.class);
        intent.putExtra("perfilUsuario", (Serializable) perfilUsuario);
        startActivity(intent);
         finish();

    }
}