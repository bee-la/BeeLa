package br.rodriguesufrpe.anderson.beela;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Perfil extends AppCompatActivity {
    private ImageButton adicionarPerfilTrocarTela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

//-------------------------------------Trocar tela--------------------------------------------
        adicionarPerfilTrocarTela = (ImageButton) findViewById(R.id.imageButton4);
        adicionarPerfilTrocarTela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarPerfilTrocarTela();}
        });
    }

    private void adicionarPerfilTrocarTela() {
        startActivity(new Intent(Perfil.this, PerguntasComidas.class));
    }

}
