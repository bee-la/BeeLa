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
import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.perfil.dominio.PerfilEsporte;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.perfil.negocio.PerfilService;
import br.ufrpe.beela.usuario.gui.LoginAct;

public class PerguntaEsporteAct extends AppCompatActivity {

    private PerfilUsuario perfilUsuario = LoginAct.getPessoa().getPerfil();
    private PerfilService perfilService = new PerfilService();
    private ArrayList<CheckBox> checkBoxesEsportes = new ArrayList<>();
    private static ArrayList<PerfilEsporte> listaEsporte = new ArrayList<PerfilEsporte>();

    private TextView pergunta;
    private Button botaoConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas_esporte);

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
    private void clicarBotaoConfirmar(){

        botaoConfirmar = (Button) findViewById(R.id.button13);
        botaoConfirmar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                perfilService.adcListaEsporte(checkBoxesEsportes,listaEsporte,perfilUsuario);
                LoginAct.getPessoa().setPerfil(perfilUsuario);
                irTelaPerfilPrioridade();

            }
        });
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
    }

    public void irTelaPerfilPrioridade(){
        startActivityForResult(new Intent(PerguntaEsporteAct.this, NomePerfilAct.class),1);
        //finish();

    }
    public void addTesteNome(){
        if (perfilUsuario.getNome()!=null) {
            PerfilDAO bd = new PerfilDAO();
            bd.getLer(this);
            perfilService.adcEsporte(perfilUsuario, this);
        }
        else{
            Toast.makeText(getApplicationContext(), "NÂO TEM NOME", Toast.LENGTH_SHORT).show();}
    }
    protected void onActivityResult(int codigoDaTela, int quemInviou, Intent intent ){
        if(codigoDaTela == 1 ){
            try {
            Bundle valor = intent.getExtras();
                if (valor != null) {
                    perfilUsuario.setNome(valor.getString("nomePerfil"));
                    addTesteNome();
                    setResult(1, intent);
                    finish();
                }
            }
            catch(Exception e){
                finish();
                e.printStackTrace();
            }
                //intent.putExtra("nomePerfil",perfilUsuario.getNome());
                //    startActivity(new Intent(this, PerfilAct.class));
        }
    }
}
