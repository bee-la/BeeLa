package br.ufrpe.beela.perfil.gui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;

import com.allyants.draggabletreeview.DraggableTreeView;
import com.allyants.draggabletreeview.SimpleTreeViewAdapter;
import com.allyants.draggabletreeview.TreeNode;

import java.util.ArrayList;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.perfil.dominio.PerfilComida;
import br.ufrpe.beela.perfil.dominio.PerfilEsporte;
import br.ufrpe.beela.perfil.dominio.PerfilMusica;
import br.ufrpe.beela.usuario.gui.LoginAct;

public class PerfilPrioridadeAct extends AppCompatActivity {
    private DraggableTreeView draggableTreeView;
    private Button confirmarButton16;

    private ArrayList<String> prioridade = new ArrayList<String>();
    private ArrayList<PerfilComida> listaComidas = LoginAct.getPessoa().getPerfilAtual().getComida();
    private ArrayList<PerfilMusica> listaMusicas = LoginAct.getPessoa().getPerfilAtual().getMusica();
    private ArrayList<PerfilEsporte> listaEsporte = LoginAct.getPessoa().getPerfilAtual().getEsporte();

    private Context root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_prioridade);

        preencherListaPrioridade();
        montarPrioridade();
        clicarBotaoConfirmar();

    }

    private void preencherListaPrioridade() {
        for (int i = 0; i < listaMusicas.size(); i++) {
            prioridade.add(listaMusicas.get(i).getNome());
        }
        listaMusicas.clear();

        for (int i = 0; i < listaComidas.size(); i++) {
            prioridade.add(listaComidas.get(i).getNome());
        }
        listaComidas.clear();

        for (int i = 0; i < listaEsporte.size(); i++) {
            prioridade.add(listaEsporte.get(i).getNome());
        }
        listaEsporte.clear();
    }

    private void montarPrioridade() {
        TreeNode root = new TreeNode(this);
        for (int i = 0; i < prioridade.size(); i++) {
            TreeNode item = new TreeNode(prioridade.get(i));
            root.addChild(item);
        }

        SimpleTreeViewAdapter adapter = new SimpleTreeViewAdapter(this, root);
        draggableTreeView = (DraggableTreeView) findViewById(R.id.draggable);
        draggableTreeView.setAdapter(adapter);
        draggableTreeView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                return false;
            }
        });
    }

    private void clicarBotaoConfirmar() {
        confirmarButton16 = (Button) findViewById(R.id.button16);
        confirmarButton16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irTelaNomePerfil();
            }
        });
    }

    public void irTelaNomePerfil() {
        startActivity(new Intent(PerfilPrioridadeAct.this, NomePerfilAct.class));
        finish();
    }
}
