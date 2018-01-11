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

import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.perfil.dominio.PerfilComida;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.usuario.gui.LoginAct;
import br.ufrpe.beela.gui.R;

public class PerguntasComidasAct extends AppCompatActivity {

    private PerfilUsuario usuario = LoginAct.getPessoa().getPerfil();

    private TextView legendaPrincipal;
    private Button botaoConfirmar;
    private List<CheckBox> checkBoxesComidas = new ArrayList<>();
    private static ArrayList<PerfilComida> listaComida = new ArrayList();
    public static ArrayList getListaComida() {  return listaComida; }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas_comidas);

        alterarFonte();
        adcCheckBoxCom();
        botaoConfirmar();
    }

        public void alterarFonte() {
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        legendaPrincipal = (TextView) findViewById(R.id.textView7);
        legendaPrincipal.setTypeface(fonte);

        }

        public void adcCheckBoxCom() {
            checkBoxesComidas.add((CheckBox) findViewById(R.id.checkboxMassas));
            checkBoxesComidas.add((CheckBox) findViewById(R.id.checkboxCarnes));
            checkBoxesComidas.add((CheckBox) findViewById(R.id.checkBoxFastFood));
            checkBoxesComidas.add((CheckBox) findViewById(R.id.checkBoxPizza));
            checkBoxesComidas.add((CheckBox) findViewById(R.id.checkBoxOrientais));
        }


        private void botaoConfirmar(){
        botaoConfirmar = (Button) findViewById(R.id.buttonConfirmar2);
        botaoConfirmar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                adcComida();
                telaPerguntasEsporte();
            }
        });
    }


    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
    }

    public void adcComida(){
//        ArrayList<PerfilComida>listaComida = new ArrayList<PerfilComida>();
        for (CheckBox x : checkBoxesComidas) {
            if (x.isChecked()) {
                PerfilComida comida = new PerfilComida();
                comida.setNome((String) x.getText());
                comida.setId_usuario(usuario.getId_Usuario());
                listaComida.add(comida);
            }
        }
        usuario.setComida(listaComida);
    }

    public ArrayList<PerfilComida> getComida(){return listaComida; }

    public void telaPerguntasEsporte(){
        startActivity(new Intent(PerguntasComidasAct.this, PerguntasEsporteAct.class));
        finish();
    }

}

