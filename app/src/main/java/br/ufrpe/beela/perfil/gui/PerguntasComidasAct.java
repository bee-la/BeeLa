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

import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.usuario.gui.LoginAct;
import br.ufrpe.beela.gui.R;

public class PerguntasComidasAct extends AppCompatActivity {

    private PerfilUsuario usuario = LoginAct.getUsuario().getPerfil();

    private TextView fonteTextView7;
    private Button btconfirmar;

    private List<CheckBox> checkBoxesComidas = new ArrayList<>();


    private static ArrayList<comida> listaComida = new ArrayList();


    public static ArrayList getListaComida() {  return listaComida; }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas_comidas);

        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");

        fonteTextView7 = (TextView) findViewById(R.id.textView7);
        fonteTextView7.setTypeface(fonte);

        CheckBox crepe = findViewById(R.id.checkBoxCreperia);
        CheckBox fastFood = findViewById(R.id.checkBoxFastFood);
        CheckBox lasanha = findViewById(R.id.checkBoxLasanha);
        CheckBox yakisoba = findViewById(R.id.checkBoxYakisoba);
        CheckBox pizza = findViewById(R.id.checkboxPizza);


        checkBoxesComidas.add(crepe);
        checkBoxesComidas.add(fastFood);
        checkBoxesComidas.add(lasanha);
        checkBoxesComidas.add(yakisoba);
        checkBoxesComidas.add(pizza);

        btconfirmar = (Button) findViewById(R.id.buttonConfirmar2);
        btconfirmar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                alterarTelaPerfil();
            }

        });
    }


    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();
    }

    public void adcComida(){

        listaComida = new ArrayList<>();
        for (CheckBox x : checkBoxesComidas) {
            if (x.isChecked()) {
                comida a = new comida(x.getText().toString());
                listaComida.add(a);
            }
        }
    }






    public void alterarTelaPerfil(){
//TODO      Na tela de PerfilAct falta aparecer o perfil que foi adicionado.
        adcComida();
        //usuario.setMusica(musicaSelecionada);
        startActivity(new Intent(PerguntasComidasAct.this, NomePerfilAct.class));


    }

}

