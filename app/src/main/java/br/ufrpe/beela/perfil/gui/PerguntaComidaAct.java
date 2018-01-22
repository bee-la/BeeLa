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

import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.perfil.dominio.PerfilComida;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.perfil.negocio.PerfilService;
import br.ufrpe.beela.usuario.gui.LoginAct;
import br.ufrpe.beela.gui.R;

public class PerguntaComidaAct extends AppCompatActivity {

    private PerfilUsuario perfilUsuario = LoginAct.getPessoa().getPerfil();
    private PerfilService perfilService = new PerfilService();
    private TextView pergunta;
    private Button botaoConfirmar;
    private ArrayList<CheckBox> checkBoxesComidas = new ArrayList<>();
    private static ArrayList<PerfilComida> listaComida = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas_comidas);

        alterarFonte();
        adcCheckBoxCom();
        clicarBotaoConfirmar();

    }

    public void alterarFonte() {
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        pergunta = (TextView) findViewById(R.id.textView7);
        pergunta.setTypeface(fonte);

    }

    public void adcCheckBoxCom() {
        checkBoxesComidas.add((CheckBox) findViewById(R.id.checkboxMassas));
        checkBoxesComidas.add((CheckBox) findViewById(R.id.checkboxCarnes));
        checkBoxesComidas.add((CheckBox) findViewById(R.id.checkBoxFastFood));
        checkBoxesComidas.add((CheckBox) findViewById(R.id.checkBoxPizza));
        checkBoxesComidas.add((CheckBox) findViewById(R.id.checkBoxOrientais));
    }

    private void clicarBotaoConfirmar() {
        botaoConfirmar = (Button) findViewById(R.id.buttonConfirmar2);
        botaoConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perfilService.adcListaComida(checkBoxesComidas, listaComida, perfilUsuario);
                LoginAct.getPessoa().setPerfil(perfilUsuario);
                irTelaPerguntaEsporte();

            }
        });
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
    }


    public void irTelaPerguntaEsporte() {
        startActivityForResult(new Intent(PerguntaComidaAct.this, PerguntaEsporteAct.class), 1);
        finish();

    }

    public void addTesteNome() {
        if (perfilUsuario.getNome() != null) {
            PerfilDAO bd = new PerfilDAO();
            bd.getLer(this);
            perfilService.adcComida(perfilUsuario, this);
        }
        else{
            Toast.makeText(getApplicationContext(), "NÂO TEM NOME", Toast.LENGTH_SHORT).show();}
    }

    protected void onActivityResult(int codigoDaTela, int quemInviou, Intent intent) {
        if (codigoDaTela == 1) {
            try{
            Bundle valor = intent.getExtras();
            if (valor != null) {
                perfilUsuario.setNome(valor.getString("nomePerfil"));
                addTesteNome();
                setResult(1,intent);
//                finish();
            }
        }catch(Exception e){
            e.printStackTrace();
        }


        }
    }
}