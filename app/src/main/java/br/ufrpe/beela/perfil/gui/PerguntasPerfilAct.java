package br.ufrpe.beela.perfil.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.perfil.dominio.PerfilComida;
import br.ufrpe.beela.perfil.negocio.PerfilService;
import br.ufrpe.beela.usuario.gui.LoginAct;

public class PerguntasPerfilAct extends AppCompatActivity {
    private PerfilService perfilService = new PerfilService();
    private ExpandableListView listView;
    private ExpandableListAdapterAct listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHash;
    private Button button23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas_perfil);

        button23 = (Button) findViewById(R.id.button23);
        listView = (ExpandableListView) findViewById(R.id.lvExp);
        perfilService.initData(listDataHeader, listHash);
        listAdapter = new ExpandableListAdapterAct(this, listDataHeader, listHash);
        listView.setAdapter(listAdapter);

        button23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button23();
            }

        });
    }

    private void Button23() {
        startActivity(new Intent(PerguntasPerfilAct.this, NomePerfilAct.class));
    }
}

