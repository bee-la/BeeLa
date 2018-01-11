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
import br.ufrpe.beela.perfil.dominio.PerfilMusica;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.usuario.gui.LoginAct;
import br.ufrpe.beela.gui.R;

public class PerguntasMusicaAct extends AppCompatActivity {
    private PerfilUsuario usuario = LoginAct.getPessoa().getPerfil();
    private TextView fontePergunta;
    private Button botaoConfirmar;
    private CheckBox rock, sernanejo, forro, samba;

    private List<CheckBox> checkBoxesMusicas = new ArrayList<>();
    private static ArrayList<PerfilMusica> listaMusica = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas_musica);

        alterarFonte();
        adcCheckBox();
        setBotaoconfirmar();
    }

    private void alterarFonte(){
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        fontePergunta = (TextView) findViewById(R.id.textView9);
        fontePergunta.setTypeface(fonte);
    }

    private void adcCheckBox(){
        rock = findViewById(R.id.checkBoxRock);
        sernanejo = findViewById(R.id.checkboxSertanejo);
        forro = findViewById(R.id.checkBoxForro);
        samba = findViewById(R.id.checkBoxSamba);

        checkBoxesMusicas.add(rock);
        checkBoxesMusicas.add(sernanejo);
        checkBoxesMusicas.add(forro);
        checkBoxesMusicas.add(samba);
    }

    private void setBotaoconfirmar(){
        botaoConfirmar = (Button) findViewById(R.id.buttonConfirmar);
        botaoConfirmar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                adcMusica();
                alterarTelaPerfil();
            }
        });
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
    }

        public void adcMusica(){
            for (CheckBox x : checkBoxesMusicas) {
                if (x.isChecked()) {
                    PerfilMusica musica = new PerfilMusica();
                    musica.setId_usuario(usuario.getId_Usuario());
                    musica.setNome(x.getText().toString());
                    listaMusica.add(musica);
                }
            }
            usuario.setMusica(listaMusica);
        }

    public ArrayList<PerfilMusica> getMusica(){
            return listaMusica;
    }

    private void alterarTelaPerfil(){
        startActivity(new Intent(PerguntasMusicaAct.this, PerguntasComidasAct.class));
        finish();
    }

}
