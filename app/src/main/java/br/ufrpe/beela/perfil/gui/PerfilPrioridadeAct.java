package br.ufrpe.beela.perfil.gui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;

import com.allyants.draggabletreeview.DraggableTreeView;
import com.allyants.draggabletreeview.SimpleTreeViewAdapter;
import com.allyants.draggabletreeview.TreeNode;

import java.util.ArrayList;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.perfil.dominio.PerfilComida;
import br.ufrpe.beela.perfil.dominio.PerfilEsporte;
import br.ufrpe.beela.perfil.dominio.PerfilMusica;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.usuario.gui.LoginAct;
import br.ufrpe.beela.perfil.dao.PerfilDAO;

public class PerfilPrioridadeAct extends AppCompatActivity {
    DraggableTreeView draggableTreeView;
    private PerfilUsuario usuario = LoginAct.getPessoa().getPerfil();

    private ArrayList<String> prioridade = new ArrayList<String>();
    private ArrayList<PerfilComida> listaComidas=LoginAct.getPessoa().getPerfil().getComida();
    private ArrayList<PerfilMusica> listaMusicas=LoginAct.getPessoa().getPerfil().getMusica();
    private ArrayList<PerfilEsporte> listaEsporte=LoginAct.getPessoa().getPerfil().getEsporte();

    private Context root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_prioridade);

        draggableTreeView = (DraggableTreeView)findViewById(R.id.draggable);

        for(int i=0; i<listaMusicas.size(); i++) {
            prioridade.add(listaMusicas.get(i).getNome());
        }

        for(int i=0; i<listaComidas.size(); i++) {
            prioridade.add(listaComidas.get(i).getNome());
        }

        for(int i=0; i<listaEsporte.size(); i++) {
            prioridade.add(listaEsporte.get(i).getNome());
        }

        TreeNode root = new TreeNode(this);
        for(int i=0; i<prioridade.size(); i++){
            TreeNode item=new TreeNode(prioridade.get(i));
            root.addChild(item);
        }

        SimpleTreeViewAdapter adapter = new SimpleTreeViewAdapter(this,root);
        draggableTreeView.setAdapter(adapter);
        draggableTreeView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                return false;
            }
        });

    }
}
