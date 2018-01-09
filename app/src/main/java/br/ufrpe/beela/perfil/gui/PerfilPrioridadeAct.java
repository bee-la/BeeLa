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
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.usuario.gui.LoginAct;
import br.ufrpe.beela.perfil.dao.PerfilDAO;

public class PerfilPrioridadeAct extends AppCompatActivity {
    private DraggableTreeView draggableTreeView;
    private PerfilUsuario usuario = LoginAct.getPessoa().getPerfil();
    private Button confirmarButton16;

    private PerguntasComidasAct comida=new PerguntasComidasAct();
    private PerguntasMusicaAct musica=new PerguntasMusicaAct();
    private PerguntasEsporteAct esporte=new PerguntasEsporteAct();

    private ArrayList<String> prioridade = new ArrayList<String>();
    private ArrayList<PerfilComida> listaComidas=LoginAct.getPessoa().getPerfil().getComida();
    private ArrayList<PerfilMusica> listaMusicas=LoginAct.getPessoa().getPerfil().getMusica();
    private ArrayList<PerfilEsporte> listaEsporte=LoginAct.getPessoa().getPerfil().getEsporte();

    private Context root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_prioridade);

        confirmarButton16=(Button)findViewById(R.id.button16);
        confirmarButton16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {telaNomePerfil();}});

        draggableTreeView = (DraggableTreeView)findViewById(R.id.draggable);

        for(int i=0; i<listaMusicas.size(); i++) {
            prioridade.add(listaMusicas.get(i).getNome());
        }
        listaMusicas.clear();
//        musica.getMusica().clear();

        for(int i=0; i<listaComidas.size(); i++) {
            prioridade.add(listaComidas.get(i).getNome());
        }
        listaComidas.clear();
//        comida.getComida().clear();

        for(int i=0; i<listaEsporte.size(); i++) {
            prioridade.add(listaEsporte.get(i).getNome());
        }
        listaEsporte.clear();
//        esporte.getEsporte().clear();

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

    public void telaNomePerfil() {
//        adcEsporte();
//        ArrayList<String> prioridade=new ArrayList<String>();
        startActivity(new Intent(PerfilPrioridadeAct.this, NomePerfilAct.class));
        finish();
    }
}
