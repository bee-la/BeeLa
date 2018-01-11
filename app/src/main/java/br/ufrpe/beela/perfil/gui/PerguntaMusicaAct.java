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
import br.ufrpe.beela.perfil.dominio.PerfilMusica;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.perfil.negocio.PerfilService;
import br.ufrpe.beela.usuario.gui.LoginAct;
import br.ufrpe.beela.gui.R;

public class PerguntaMusicaAct extends AppCompatActivity {
    private PerfilUsuario perfilUsuario = LoginAct.getPessoa().getPerfil();
    private PerfilService perfilService = new PerfilService();
    private TextView pergunta;
    private Button botaoConfirmar;

    private ArrayList<CheckBox> checkBoxesMusicas = new ArrayList<>();
    private static ArrayList<PerfilMusica> listaMusica = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas_musica);

        alterarFonte();
        adcCheckBoxMus();
        clicarBotaoConfirmar();
    }

    private void alterarFonte(){
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        pergunta = (TextView) findViewById(R.id.textView9);
        pergunta.setTypeface(fonte);
    }

    private void adcCheckBoxMus(){
        checkBoxesMusicas.add((CheckBox) findViewById(R.id.checkBoxRock));
        checkBoxesMusicas.add((CheckBox) findViewById(R.id.checkboxSertanejo));
        checkBoxesMusicas.add((CheckBox) findViewById(R.id.checkBoxForro));
        checkBoxesMusicas.add((CheckBox) findViewById(R.id.checkBoxSamba));

    }

    private void clicarBotaoConfirmar(){
        botaoConfirmar = (Button) findViewById(R.id.buttonConfirmar);
        botaoConfirmar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                perfilService.adcListaMusica(checkBoxesMusicas,listaMusica,perfilUsuario);
                irTelaPerguntaComida();
            }
        });
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
    }

    public ArrayList<PerfilMusica> getMusica(){
            return listaMusica;
    }

    private void irTelaPerguntaComida(){
        startActivity(new Intent(PerguntaMusicaAct.this, PerguntaComidaAct.class));
        finish();
    }

}
