package br.ufrpe.beela.perfil.negocio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.perfil.gui.PerfilAct;
import br.ufrpe.beela.usuario.gui.LoginAct;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

/**
 * Created by Anderson on 06/01/2018.
 */

public class ListViewNomePerfil extends BaseAdapter {
    private Context ctx;
    private PerfilAct perfil=new PerfilAct();
    private ArrayList<PerfilUsuario> listaPerfil=perfil.getPerfis();
    private ArrayList<PerfilUsuario> listaExclusao;

    public ListViewNomePerfil(Context ctx){
        this.ctx=ctx;
    }

    @Override
    public int getCount(){
        return listaPerfil.size();
    }

    @Override
    public Object getItem(int posicao){
        return listaPerfil.get(posicao);
    }

    @Override
    public long getItemId(int posicao){
        return listaPerfil.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View convertView, ViewGroup parent){
        final PerfilUsuario perfil=listaPerfil.get(posicao);

        View view= LayoutInflater.from(ctx).inflate(R.layout.activity_cor_list_view, null);
        TextView texto=view.findViewById(R.id.textView2);
        CheckBox listCheckBox6 = (CheckBox) view.findViewById(R.id.checkBox6);

        listCheckBox6.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            perfil.setSelecionado(isChecked);
//                Toast.makeText(ctx, perfil.getNome()+" "+isChecked,Toast.LENGTH_LONG).show();
            }
        });

        texto.setText(perfil.getNome());
        return view;
    }
}
