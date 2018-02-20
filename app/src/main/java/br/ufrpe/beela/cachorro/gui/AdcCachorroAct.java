package br.ufrpe.beela.cachorro.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.ufrpe.beela.cachorro.dominio.Cachorro;
import br.ufrpe.beela.cachorro.negocio.CachorroService;
import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.perfil.gui.PerfilAct;
import br.ufrpe.beela.usuario.gui.HomeAct;

public class AdcCachorroAct extends AppCompatActivity {

    private EditText nome;
    private EditText raca;
    private EditText cor;
    private Button criarCachorro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adc_cachorro);
        nome = (EditText) findViewById(R.id.editTextNomeAnimal);
        raca = (EditText) findViewById(R.id.editRacaAnimal);
        cor = (EditText) findViewById(R.id.editCorAnimal);
        criarCachorro = (Button)findViewById(R.id.buttonCriarAnimal);
        criarCachorro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adcCachorro();

            }
        });
    }

    public void adcCachorro(){
        CachorroService cachorroService = new CachorroService();
        Cachorro cachorro = new Cachorro();
        cachorro.setNome(nome.getText().toString());
        cachorro.setRaca(raca.getText().toString());
        cachorro.setCor(cor.getText().toString());
        cachorroService.adcCachorro(cachorro, this);
        mudarTela();
    }

    public void mudarTela(){
        startActivity(new Intent(AdcCachorroAct.this, HomeAct.class));
        finish();
    }
}
