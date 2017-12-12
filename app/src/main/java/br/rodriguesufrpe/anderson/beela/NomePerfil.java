package br.rodriguesufrpe.anderson.beela;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NomePerfil extends AppCompatActivity {

    private EditText nomePerfilEditText12;
    private TextView nomeTextViewPerfil1;
    private Button nomearButton22;

    private String nomePerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nome_perfil);

        nomeTextViewPerfil1=(TextView)findViewById(R.id.textViewPerfil1);
        nomePerfilEditText12=(EditText)findViewById(R.id.editText12);
        nomearButton22=(Button)findViewById(R.id.button22);

        nomePerfil=nomePerfilEditText12.getText().toString().trim();
        Login.usuario.perfil.setNome(nomePerfil);

        nomearButton22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setarNomePerfil(); }});
    }
    
//TODO      NullPointExcept ao chamar essa função
    public void setarNomePerfil(){
        nomeTextViewPerfil1.setText(Login.usuario.perfil.getNome());
    }


}
