package br.ufrpe.beela.perfil.gui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;

import com.allyants.draggabletreeview.DraggableTreeView;
import com.allyants.draggabletreeview.SimpleTreeViewAdapter;
import com.allyants.draggabletreeview.TreeNode;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.usuario.gui.LoginAct;

public class PerfilPrioridadeAct extends AppCompatActivity {
    DraggableTreeView draggableTreeView;
    private PerfilUsuario usuario = LoginAct.getPessoa().getPerfil();
    private String perfilPrioridade;
    private Context root;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_prioridade);

        draggableTreeView = (DraggableTreeView)findViewById(R.id.draggable);

        TreeNode root = new TreeNode(this);
        TreeNode item1 = new TreeNode("item1");
        TreeNode item2 = new TreeNode("item2");
        TreeNode item3 = new TreeNode("item3");
        TreeNode item4 = new TreeNode("item4");
        TreeNode item5 = new TreeNode("item5");

        root.addChild(item1);
        root.addChild(item2);
        root.addChild(item3);
        root.addChild(item4);
        root.addChild(item5);


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
